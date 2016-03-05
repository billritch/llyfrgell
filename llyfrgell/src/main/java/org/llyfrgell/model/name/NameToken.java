/**
 * NameToken.java Feb 11, 2014
 */
package org.llyfrgell.model.name;


/**
 * The use of Name Tokens when parsing.
 *
 * @author William Alan Ritch 2014/02/11.
 * @revised William Alan Ritch 2014/07/18.
 *  -   Added an error message improvement that lets a human track down a
 *      parsing problem. Each token keeps a reference to the original
 *      string that was parsed and the character number of where this
 *      token started.
 * @revised William Alan Ritch 2014/07/21.
 *  -   Added {@link Iterable} interface
 */
public interface NameToken extends Iterable<NameToken> {


    // classification of elements
    enum TokenCode {
        Empty,
        Word,
        List,
        Group,
        Punctuation,
        WhiteSpace
    };

    /**
     * Where is this token located in the original parsing?
     *
     * We keep the original name string that was parsed and
     * the character index into that string so that we can create
     * a detailed error message for the human user.
     *
     * @author William Alan Ritch 2014/07/18.
     */
    public class Location {
        private String strName;
        private int index;

        /***
         * Create a new Location object.
         *
         * @param str_original Original string being parsed.
         * @param ndx Index within the original string where the token starts.
         * @return Created Location object.
         */
        public static Location make(String str_original, int ndx) {
            Location loc = new Location();
            loc.strName = str_original;
            loc.index = ndx;
            return loc;
        } // make()

        /***
         * Return the name string.
         * @return Name.
         */
        public String getName() {
            return this.strName;
        } // getName()

        /***
         * Return the index.
         *
         * @return Index.
         */
        public int geIndex() {
            return this.index;
        } // geIndex()
}; // class Location

    /**
     * An exception thrown while parsing the name string into tokens.
     *
     * @author William Alan Ritch 2014/02/11.
     */
    class NameTokenException extends Exception {
        /**
         *
         */
        private static final long serialVersionUID = -3265145837402233341L;

        /**
         * What has gone wrong.
         */
        public enum ExceptionCode {
            None,
            ExceptionCannotBeNull,
            ExceptionFatalInternalValidation,
            ExceptionTokenInvalidCode,
            ExceptionProperAncestorExpected,
            ExceptionCannotAddThisType,
            ExceptionCannotAddToClosed,
            ExceptionNoChildToRemove,

            ExceptionCannotAddClosedGroup,
            ExceptionCannotAddToAtomic,
            ExceptionCannotReplaceChild,

            ExceptionCannotAddWord,
            ExceptionCannotAddThisNameType,
            ExceptionMustAddToGroupOrList,
            ExceptionMustAddGroupOrListToThis,
            ExceptionMustAddWordOrGroupToList0,
            ExceptionCannotHandleThisType,
            ExceptionMustAddListToList,
            ExceptionParentCannotBeNull,
            ExceptionChildrenInvalid,
            ExceptionMustBeChildless,
            ExceptionChildOfListMustBeList,
            ExceptionImproperChildOfList
        }; // enum ExceptionCode

        /**
         * General classification of the exception thrown.
         */
        public enum ExceptionType {
            Unknown,
            EmptyNameNotAllowed,
            InternalValidationException,
            ExceptionInvalidParameter,
            ExceptionInvalidOperation
        }; // enum ExceptionType


        // The kind of problem encountered.
        private ExceptionType typeException;

        // The specific problem encountered.
        private ExceptionCode codeException;

        // The token where the problem was encountered.
        private NameToken token;

        /**
         * Default constructor.
         */
        public NameTokenException() {
            this.token = null;
            this.codeException = ExceptionCode.None;
            this.typeException = ExceptionType.Unknown;
        } // NameTokenException()

        /**
         * Constructor with token.
         *
         * @param p_token Token where error occurs.
         */
        public NameTokenException(NameToken p_token) {
            this.token = p_token;
            this.codeException = ExceptionCode.None;
            this.typeException = ExceptionType.Unknown;
        } // NameTokenException()

        /**
         * Build the exception with the type and reason for the exception.
         *
         * @param type_exception Exception type.
         * @param code_exception Exception specifics.
         * @param p_token Token where error occurs.
         */
        private NameTokenException(
                NameToken p_token,
                ExceptionType type_exception, ExceptionCode code_exception) {
            this.codeException = code_exception;
            this.typeException = type_exception;
        } // NameTokenException()

        public static NameTokenException makeEmptyNameNotAllowed(
                NameToken p_token) {
            return new NameTokenException(p_token,
                    ExceptionType.EmptyNameNotAllowed, ExceptionCode.None);
        }

        public static NameTokenException makeInvalidParamterException(
                NameToken p_token, ExceptionCode p_code) {
                    return new NameTokenException(p_token,
                            ExceptionType.ExceptionInvalidParameter, p_code);

        } // makeInvalidParamterException()

        public static NameTokenException makeInvalidOperationException(
                NameToken p_token, ExceptionCode p_code) {
                    return new NameTokenException(p_token,
                            ExceptionType.ExceptionInvalidOperation, p_code);

        } // makeInvalidParamterException()

        public static NameTokenException makeInternalValidationException(
                NameToken p_token, ExceptionCode p_code) {
            return new NameTokenException(p_token,
                    ExceptionType.InternalValidationException, p_code);
        }

        public ExceptionType getType() {
            return this.typeException;
        }

        public ExceptionCode getCode() {
            return this.codeException;
        }
    }; // class NameTokenException


    /***
     * Add a token to this element of the word list.
     *
     * @param p_token Token to be added.
     */
    NameToken add(NameToken p_token) throws NameTokenException;

    /**
     * Predicate: Is this token active.
     * Tokens are active by default. When the token has been consumed
     * by a parser it may be set to false so that it will not be consumed
     * by additional parsers.
     */
    boolean isActive();

    /**
     * Activate the token so that it can be used by a parser.
     */
    void activate();

    /**
     * Deactivate the token so that it cannot be used by a parser.
     *
     * Only Atomic tokens can be directly deactivated.
     * Non-atomic tokens are conditionally deactivated - they are only
     * deactivated if all of their children have been deactivated.
     */
    void deactivate();

    /**
     * Predicate: Is this token open or closed?
     * Open tokens may have items added to them.
     * A closed token is finished, and only be read and moved.
     */
    boolean isOpen();

    /**
     * Predicate: Is this Name Token a Name Atom?
     */
    boolean isAtomic();

    /**
     * Predicate: Is this Name Token a Name Molecule?
     * A Name Molecule is a Name Group or List.
     */
    boolean isMolecular();

    /**
     * Predicate: Is this Name Token a Name Group?
     */
    boolean isGroup();

    /**
     * Predicate: Is this Name Token a Name List?
     */
    boolean isList();

    /**
     * Predicate: Is this Name Token a Name List of level 0?
     */
    boolean isList0();

    /**
     * Predicate: Is this Name Token a Name List of level N (N > 0)?
     */
    boolean isListN();

    /**
     * Predicate: Is this Name Token a Name List of level N - specified?
     */
    boolean isListN(int level);

    /**
     * Property: The level of the list.
     * If this is not a list the level is -1.
     */
    int getLevel();

    /**
     * Predicate: Is this Name Token a child? Does it have a parent?
     */
    boolean isChild();

    /**
     * Predicate: Is this Name Token a parent? Does it have a child?
     */
    boolean isParent();

    /**
     * Count the number of children possessed by this parent.
     */
    int countChildren();

    /**
     * Get a specific child.
     *
     * @param index Index of the child.
     * @return The specified child token.
     */
    NameToken getChild(int index);

    /***
     * Get the code of this token.
     *
     * @return The token code.
     */
    TokenCode getCode();

    /**
     * Return the text value of the token.
     *
     * Not all tokens will have a text value.
     *
     * @return Value of a token, like for instance, the name.
     */
    CharSequence getValue();

    Iterable<NameToken> reverse();
} // interface NameToken
