//package org.llyfrgell.model.name.impl;
//
//import java.util.LinkedList;
//import java.util.List;
//
///***
// *
// *
// *
// * @author William Alan Ritch Feb 4, 2014
// *
// */
//public class NameListParser {
//
//    private NameElement nelRoot = null;
//    private NameElement nelTop = null;
//
//
//    public NameListParser() {
//        // TODO Auto-generated constructor stub
//    }
//
//
//
//    /***
//     * Discuss types of elements
//     *
//     * A "Word" element is a name or word. These are the fundamental elements
//     * of a NameListImpl.
//     *
//     * A List is a collection of elements sorted in order. The elements of a
//     * list can be Words or other NameElements. A list can have a level which
//     * is determined the punctuation that separates the elements of a list.
//     *
//     * A Group is a list that contains grouping.  A group is defined by having
//     * a matched pair of open and close group characters, like parentheses,
//     * brackets, or braces.  There is no assumed hierarchy of these groups
//     * based on which grouping punctuation characters are used. Rather the
//     * hierarchy comes from the "nesting" of the punctuation inside the
//     * original text string. The level of a group list is how far down it
//     * is in the nesting hierarchy. Any group is treated like a level 0 list.
//     *
//     * @author William Alan Ritch Feb 4, 2014
//     *
//     */
//    class NameElement {
//
//        /** Which kind of element. */
//        private TokenCode code;
//        /** Code level (used for groups and separators). */
//        private int level;
//        /** Differs by which element:
//         *  - Word, it is the actual word
//         *  - List (not used)
//         *  - Group, the open and close characters of the group.
//         */
//        private CharSequence val;
//
//        private NameElement elParent;
//
//        public NameElement make() {
//            NameElement el = new NameElement();
//
//            return el;
//        }
//
//
//        private NameElement() {
//            this.code = TokenCode.Empty;
//            this.level = 0;
//            this.val = null;
//            this.elParent = null;
//        }
//
//
//        public NameElement(TokenCode p_code) {
//            this.code = p_code;
//            this.level = 0;
//            this.val = null;
//        }
//
//        public NameElement(TokenCode p_code, CharSequence p_val) {
//            this.code = p_code;
//            this.val = p_val;
//            this.level = 0;
//        }
//
//        public NameElement(TokenCode p_code, int p_level) {
//            this.code = p_code;
//            this.level = p_level;
//            this.val = null;
//        }
//
//
//
//
//
//
//
//        /***
//         * Add a word this NameElement.
//         *
//         * Words may only be added to Level 0 lists.
//         *
//         * If this is a Level N list (N > 0) then we must create
//         * a level 0 sublist under this list.
//         *
//         * If this is a Group, a Group is always considered a Level 0 list.
//         *
//         *
//         * @param p_str
//         * @return
//         */
//        public NameElement add(CharSequence p_str) {
//            NameElement el = this;
//
//            if (!isList()) {
//                throw Exception();
//            }
//
//            if (!isListLevel0()) {
//                // create a level 0 for this one
//                 el = add(makeListElement(0));
//            }
//
//            el.add(makeWordElement(p_str));
//
//        } // add()
//
//
//        protected void setParent(NameElement p_parent) {
//            elParent = p_parent;
//        }
//    } // class NameElement
//
//    class WordNameElement extends NameElement {
//
//        public WordNameElement() {
//            super(TokenCode.Word);
//        }
//
//    }  // class WordNameElement()
//
//    /**
//     * A element that holds a list of elements.
//     *
//     * @author William Alan Ritch Feb 5, 2014
//     *
//     */
//    class ListNameElement extends NameElement {
//        private List<NameElement> lstElements;
//
//        public ListNameElement(int p_level) {
//            super(TokenCode.List, p_level);
//        }
//
//        protected ListNameElement(TokenCode code) {
//            super(code, 0);
//        }
//
//        /**
//         * Add a word element to this list.
//         * Word elements may only be added to Level 0 lists.
//         * If this is a higher level list then we must
//         * construct a level 0 list underneath this list.
//         *
//         * @param el Word element to be added.
//         * @return
//         */
//        protected NameElement add(WordNameElement el) {
//            ListNameElement lst = this;
//
//            // is the current list a 0-level list
//            if (!isList0()) {
//                for (int nCurrentLevel = getLevel(); nCurrentLevel > 0;
//                        nCurrentLevel--) {
//                    ListNameElement lstNew = new ListNameElement(nCurrentLevel);
//                    lst.add(lstNew);
//                    lst = lstNew;
//                } // for nCurrentLevel
//            }
//
//            lst.addtoList(el);
//            return el;
//        }
//
//        protected NameElement add(ListNameElement el) {
//            // link into the this list
//            // set parent of list
//            el.setParent(this);
//            lstElements.add(el);
//            return this;
//        } // add()
//
//    }  // class ListNameElement()
//
//    class GroupNameElement extends ListNameElement {
//
//        private int nGrouping;
//
//        public GroupNameElement(int grouping_code) {
//            super(TokenCode.Group);
//            setGrouping(grouping_code);
//        }
//
//        private void setGrouping(int grouping_code) {
//            nGrouping = grouping_code;
//        }
//
//}  // class GroupNameElement()
//
//
//    // mode:
//    //  Building words
//    //  Skipping spaces
//    //  Collection building
//    //      In a list (list pointer)
//    //      In a group
//
//
//    static NameElement parse(CharSequence str) {
//        return NameElement.make();
//    }
//
//    List<NameElement> parse0(CharSequence str) {
//
//        List<NameElement> lstElements = new LinkedList<NameElement>();
//
//        // let's create a group at the top of the stack
//        nelRoot = makeGroupElement();
//        nelTop = nelRoot;
//
//        // now let's start parsing
//        int ndxChar = 0;
//        while ((ndxChar >= 0) && (ndxChar < str.length())) {
//
//            int ch = Character.codePointAt(str, ndxChar);
//
//            if (isWordChar(ch)) {
//                // parse out a "word"
//            } else if (isWhitespaceChar(ch)) {
//                // skip any whitespace
//            } else {
//                int chEval = evalSeparatorChar(ch);
//                if (0 != chEval) {
//                // do something with the separator
//                } else {
//                    chEval = evalGroupChar(ch);
//                    if (0 != chEval) {
//                        // open or close a group
//                    } else {
//                        // just punctuation
//                    }
//                }
//
//            }
//        } // while ndxChar
//
//        return lstElements;
//
//    } // parse(()
//
//
//    private NameElement makeGroupElement() {
//        NameElement el = new NameElement(TokenCode.Group, null);
//        return el;
//    } // makeGroupElement()
//
//    private NameElement makeListElement(int level) {
//        NameElement el = new NameElement(TokenCode.List, null);
//        return el;
//    } // makeListElement()
//
//
//    /**
//     * Evaluate if the specified character is a grouping character or not.
//     * If it is a grouping character we need to know:
//     *  - It's type (for instance, parenthesis, bracket, brace)
//     *  - It's closure (open or close)
//     *
//     * To do this we encode the grouping character as an integer and its
//     * closure status as the sign of the integer.
//     *
//     * @param ch Character to be tested.
//     * @return 0 if not a grouping character.
//     * < 0 for close group
//     * > 0 for open group.
//     */
//    private int evalGroupChar(int ch) {
//
//    } // evalGroupChar()
//} // class NameListParser
