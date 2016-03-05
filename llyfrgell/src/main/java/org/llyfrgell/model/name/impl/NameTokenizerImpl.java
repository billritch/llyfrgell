/**
 * NameTokenizerImpl.java Feb 6, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;
import org.llyfrgell.model.name.NameTokenizer;

/**
 * Standard implementation of a parsed name.
 *
 * @author William Alan Ritch Feb 6, 2014
 */
public class NameTokenizerImpl implements
        NameTokenizer {

    protected final String _c = this.getClass().getName();
    protected final Logger _l = Logger.getLogger(_c);

    private String separators = ",;";
    private String groupOpeners = "([{";
    private String groupClosers = ")]}";


    /** Created parsed items */
    private NameToken root = null;
    private NameToken topOfStack = null;

    // Original string that we are parsing
    private CharSequence strOriginal;

    /**
     * Constructor.
     */
    public NameTokenizerImpl() {
        // TODO Auto-generated constructor stub
    } // NameTokenizerImpl()


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameTokenizer#parse(java.lang.CharSequence)
     */
    public NameToken parse( CharSequence str)
            throws NameTokenException {
        final String _f = "parse";

        int ndxChar = 0;
        strOriginal = correct(str);

        // the root is an empty group
        root = NameGroupImpl.make(0).setLocation(strOriginal, ndxChar);
        setStack(root);

        try {

            // an empty string means no parsing
            if (!((null == str) || (str.length() <= 0))) {
                topOfStack = root.add(NameListImpl.make(0)
                        .setLocation(strOriginal, ndxChar));

                // lets look at each character
                ndxChar = 0;
                while ((ndxChar >= 0) && (ndxChar < str.length())) {
                    int ch = Character.codePointAt(str, ndxChar);

                    // test group character
                    int level = evalGroupCharacter(ch);
                    if (0 != level) {
                        // process the group
                        ndxChar++;
                        NameToken groupToken = NameGroupImpl.make(level)
                                .setLocation(strOriginal, ndxChar);
                        setStack(topOfStack.add(groupToken));

                    } else {
                        // test separator character
                        level = evalSeparatorChar(ch);
                        if (0 != level) {
                            // process the separator
                            ndxChar++;

                            // create a parent list of the
                            // correct separator level
                            NameToken listToken = NameListImpl.make(level)
                                    .setLocation(strOriginal, ndxChar);
                            setStack(topOfStack.add(listToken));

                            // do something with the separator
                            // WAR System.out.println("Separator: " + level);

                        } else if (isWordChar(ch)) {
                            // process a word
                            int ndxFirst = ndxChar;
                            ndxChar = gatherWord(str, ndxChar+1);
                            NameToken word = NameAtomImpl.make(
                                str.subSequence(ndxFirst, ndxChar))
                                .setLocation(strOriginal, ndxFirst + ndxChar);
                            setStack(topOfStack.add(word));

                        } else if (isWhitespace(ch)) {

                            // process whitespace
                            ndxChar = skipWhitespace(str, ndxChar+1);
                        }
                    }
                } // while ndxChar
            }

            // close everything
            topOfStack.add(NameGroupImpl.make(false, 0)
                    .setLocation(strOriginal, ndxChar));
        } catch (NameTokenException ex) {
            _l.logp(Level.SEVERE, _c, _f,
                    "Parsing string \"{0}\" [{1}]='{2}'",
                    new Object[] { str, new Integer(ndxChar),
                        str.subSequence(ndxChar, ndxChar+1)});
            throw ex;
        }

        return this.root;
    } // parse()



    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameTokenizer#isEmpty()
     */
    public final boolean isEmpty() {
        if (null == root) {
            return true;
        }

        return (0 == root.countChildren());
    } // isEmpty()


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameTokenizer#getFirstToken()
     */
    public final NameToken getFirstToken() {
        return this.root;
    } // getFirstToken()


    /***
     * Correct input string.
     *
     * Override this function to correct data-entry errors that may occur
     * in the names being parsed.
     *
     * @param str Input string.
     * @return Corrected string.
     */
    protected CharSequence correct(CharSequence str) {
        return str;
    } // correct()



    protected NameToken getTOS() {
        return this.topOfStack;
    }


    /**
     * While we have a word character iterate.
     *
     * @param str String containing the word.
     * @param nFirstChar First character of the word.
     * @return
     */
    private int gatherWord(CharSequence str, int ndxChar) {
        // we know where we are

        // keep skipping characters until we run out
        while ((ndxChar < str.length()) && (isWordChar(Character.codePointAt(str, ndxChar)))) {
            ndxChar ++;
        }

        return ndxChar;
    } // gatherWord()


    /**
     * Is this character a whitespace character.
     * A whitespace character is one that evaluates naturally as one of the
     * Character.isWhitespace().
     * This method can be overridden to test for more elaborate definitions
     * of a whitespace character.
     *
     * @param ch Character to be tested.
     * @return \c true if this is a Whitespace character.
     */
    protected boolean isWhitespace(int ch) {
        return Character.isWhitespace(ch);
    } // isWhitespace()


    /**
     * Skip all the whitespace in the given string, starting at the
     * @param p_str
     * @param ndx_str
     * @return
     */
    protected int skipWhitespace(CharSequence p_str, int ndx_str) {
        while ((ndx_str >= 0) && (ndx_str < p_str.length())) {
            int ch = Character.codePointAt(p_str, ndx_str);

            if (!isWhitespace(ch)) {
                break;
            }

            ndx_str++;
        } // while ndx_str

        return ndx_str;
    } // skipWhitespace()

    /**
     * Is this character a word character.
     * Since human names (and especially band names) can have all kinds
     * of characters in them a "word" character is anything that is not
     * a whitespace nor a separator.
     *
     * This method can be overridden to test for more elaborate definitions
     * of a word character. For example, we might want to add digits,
     * underscores, hyphens, etc. to our definition of a "word" character.
     *
     * @param ch Character to be tested.
     * @return \c true if this is a Word character.
     */
    protected boolean isWordChar(int ch) {
        return  (!isWhitespace(ch) &&
                    (0 == evalCharacter(this.separators, ch)) &&
                    (0 == evalCharacter(this.groupOpeners, ch)) &&
                    (0 == evalCharacter(this.groupClosers, ch)));
    } // isWordChar()

    /**
     * Evaluate if the specified character is a separator or not.
     * If this is not a separator a 0 is returned. If
     * this is a separator the separator level is returned.
     * Please note that a separator level of 0 is always a whitespace
     * separator, which is NOT tested here, so we can use 0 as the
     * indication for "not-a-separator".
     *
     * @param ch Character to be tested.
     * @return Separator level or 0 to indicate that this character
     * was not a valid separator.
     */
    private int evalSeparatorChar(int ch) {
        return evalCharacter(this.separators, ch);
    } // evalSeparatorChar()


    /**
     * Evaluate if the specified character is a group definer or not.
     * If it is not a group definer then 0 is returned. Unlike separators
     * group definers come in matched pairs: open and close characters.
     * The non-zero returned value tell us which group definer is used.
     * Its sign tells us whether it is an open or closed definer.
     *
     * @param ch Character to be tested.
     * @return 0 means no match. A non-zero number indicates the level
     * of the group definer. Positive numbers are open characters.
     * Negative numbers are closed characters.
     */
    private int evalGroupCharacter(int ch) {
        int nLevel = evalCharacter(this.groupOpeners, ch);
        if (0 == nLevel) {
            nLevel = - evalCharacter(this.groupClosers, ch);
        }
        return nLevel;
    } // evalGroupCharacter()

    /**
     * Find a matching character within a string.
     *
     * This is a convenience function that wraps the indexOf() function.
     * It differs in that
     *  - If there are no matching characters the number 0 is returned.
     *  - If there is a match then 1-origin index within the supplied
     *  string is returned.
     *
     * @param str_characters Characters that may match the specified
     * character.
     * @param ch The specified character
     * @return the 1-origin index of the matching character within the
     * supplied string. Or 0.
     */
    private int evalCharacter(String str_characters, int ch) {
        if ((null == str_characters) || (str_characters.length() <= 0)) {
            return 0;
        }

        int ndxSeparator = str_characters.indexOf(ch);
        if (ndxSeparator < 0) {
            return 0;
        }

        return ndxSeparator + 1;
    } // evalCharacter()

    private NameToken setStack(NameToken new_tos) {
        if (null != new_tos) {
            this.topOfStack = new_tos;
        } else {
            System.err.println("Setting top of stack to null!");
        }

        return this.topOfStack;
    } // setStack()

} // class NameTokenizerImpl
