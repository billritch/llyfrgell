/**
 * NameFieldParserImpl.java Jan 27, 2014
 */
package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.name.NameFieldParser;
import org.llyfrgell.model.name.NameToken;

/**
 * Abstract implementation of  the NameFieldParser.
 * Provides support for the Priority property.
 *
 * @author William Alan Ritch Jan 27, 2014
 *
 */
public abstract class NameFieldParserImpl implements
        NameFieldParser {

    /**
     *
     */
    public NameFieldParserImpl() {
    }

    /**
     * Build a Name part from a the current token and its children.
     *
     * A name can be either an Atom or a Level 0 or 1 List. If it is an Atom
     * the string value is returned. If it is a Level 0 List then the
     * individual Atoms of the List are appended to the a string and
     * returned. Any Atoms are consumed (deactivated as they are used)
     * Non-atomic elements of the List 0 are ignored.
     *
     * Atoms contain a single word of the name.
     * Lists should be iterated through
     * Groups should be ignored.
     *
     * @param bld StringBuilder to which we append.
     * @param tok Token that is the root of the name part.
     * This must be an Atom or a List 0. Any other kind of token will
     * throw an exception.
     * @return The builder that was passed in.
     * @throws NameTokenException
     */
    protected boolean buildName(StringBuilder bld, NameToken tok)
            throws NameToken.NameTokenException {
        CharSequence str;
        boolean bConsumed = false;

        // validate the token
        if (tok.isAtomic()) {
            str = tok.getValue();
            if (str.length() > 0) {
                // add to name
                if (bld.length() > 0) {
                    bld.append(' ');
                }
                bld.append(str);
            }
            tok.deactivate();
            bConsumed = true;

        } else if (tok.isList()) {
            for (NameToken tokChild : tok) {
                if (tokChild.isList() || tokChild.isAtomic()) {
                    if (buildName(bld, tokChild)) {
                        tokChild.deactivate();
                        bConsumed = true;
                    }
                }
            } // for tokChild
        }

        return bConsumed;
    } // buildName()

} // class NameFieldParserImpl
