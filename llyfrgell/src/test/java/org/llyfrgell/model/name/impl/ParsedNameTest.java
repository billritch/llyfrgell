package org.llyfrgell.model.name.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;
import org.llyfrgell.model.name.NameToken.NameTokenException.ExceptionCode;
import org.llyfrgell.model.name.NameToken.NameTokenException.ExceptionType;
import org.llyfrgell.model.name.NameToken.TokenCode;

public class ParsedNameTest {

    private final String _c = this.getClass().getName();
    private final Logger _l = Logger.getLogger(_c);

    private NameTokenizerImpl parser;
    private NameTokenImpl tokRoot;
    private NameToken tos;


    private static abstract class MockToken {

        private String strId = "";
        private int idIndex = -1;
        private TokenCode elCode;
        private boolean bOpen = false;
        private ArrayList<MockToken> children;
        private MockToken parent;
        private static boolean bTrace = false;


        protected MockToken(TokenCode p_code) {
            elCode = p_code;
            strId = "";
            children = new ArrayList<MockToken>();
        } // MockToken(TokenCode)

        protected void setOpen(boolean p_open) {
            bOpen = p_open;
        }

        public MockToken addChild(MockToken tok) {
            tok.setIdIndex(children.size());
            tok.setParent(this);
            children.add(tok);
            return this;
        }

        protected void setIdIndex(int index) {
            idIndex = index;
        }

        /**
         * Set the parent of this token.  It will also rebuild the
         * full ID.
         *
         * @param tok Token
         */
        private void setParent(MockToken tok) {
            this.parent = tok;
        }


        private boolean isTracing() { return bTrace; }

        public void trace() { bTrace = true; }

        protected StringBuilder createId(StringBuilder bldr) {
            if (null != this.parent) {
                bldr = this.parent.createId(bldr).append(".");
            }

            // now fill-in "me"
            // add the token type
            bldr = bldr.append(this.elCode.toString().substring(0, 1));

            // what kind of token is this?
            bldr = bldr.append(this.getUniqueText());

            if (this.idIndex >= 0) {
                bldr = bldr.append("[").append(this.idIndex).append("]");
            }

            return bldr;
        } // createId()



        /**
         * Used by groups and lists.
         *
         * @return Level or bracket code. Plus child index, if there.
         */
        protected String getUniqueText() {
            return "";
        }


        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder bldr = new StringBuilder();
            bldr.append('{');
            bldr = buildString(bldr);
            bldr.append('}');
            return bldr.toString();
        }

        protected StringBuilder buildString(StringBuilder bldr) {
            bldr = bldr.append(" ID=");
            bldr = this.createId(bldr);
            bldr.append(" Code=").append(getCode().toString());
            bldr.append(" Open=").append(bOpen);
            bldr.append(" #Child=").append(this.children.size());
            return bldr;
        } // buildString()


        public StringBuilder dump() {
            StringBuilder bldr = new StringBuilder("root:");
            return this.dump(bldr, "  ");
        } // dump()

        protected StringBuilder dump(StringBuilder bldr, String indent) {
            bldr = buildString(bldr).append("\n");

            // do the children
            String indentNext = indent + "  ";
            for (MockToken tok : this.children) {
                bldr.append(indent);
                bldr = tok.dump(bldr, indentNext);
            }

            return bldr;
        } // dump(StringBuilder, String)

        public TokenCode getCode() { return elCode; }

        public void validate(String str_base, NameToken tok_received) {

            StringBuilder buff = new StringBuilder(str_base);
            buff = buff.append(": Node=");
            buff = this.createId(buff);
            buff = buff.append(strId).append(" - ");
            String newBase = buff.toString();

            if (isTracing()) {
                System.out.println("Testing node: " + newBase);
            }


            assertEquals(newBase + "Token Code",
                    this.getCode(), tok_received.getCode());
            assertEquals(newBase + "Open",
                    this.bOpen, tok_received.isOpen());

            // validate the current node
            validateCurrent(newBase, tok_received);

            // validate child count
            if (this.children.size() != tok_received.countChildren()) {
                int k = 1;
            }
            assertEquals(newBase + "# Children",
                    tok_received.countChildren(),
                    this.children.size());

            // validate each child
            int i = 0;
            for (MockToken tok : this.children) {
                tok.validate(str_base, tok_received.getChild(i));
                i++;
            }

        } // assertEquals()

        protected abstract void validateCurrent(String str_base,
                NameToken tok_received);

    };  // class MockToken

    private static class MockAtom extends MockToken {
        private String strValue = null;

        private MockAtom() {
            super(TokenCode.Word);
        }

        public static MockAtom make (String str_value) {
            MockAtom test = new MockAtom();
            test.strValue = str_value;
            return test;
        }

        @Override
        protected StringBuilder buildString(StringBuilder bldr) {
            bldr = super.buildString(bldr);
            bldr = bldr.append(" Value='").append(this.strValue).append("'");
            return bldr;
        } // buildString()

        @Override
        protected void validateCurrent(String str_base,
                NameToken tok_received) {
            assertEquals(str_base + "Value",
                    this.strValue,
                 ((NameAtomImpl) tok_received).getValue());
        } // validateCurrent()
    }  // class MockAtom

    private static class MockGroup extends MockToken {

        private int bracket = -1;

        private MockGroup() {
            super(TokenCode.Group);
        }

        public static MockGroup make (int p_bracket, boolean b_open) {
            MockGroup test = new MockGroup();
            test.bracket = p_bracket;
            test.setOpen(b_open);
            return test;
        }

        public static MockGroup make (int p_bracket) {
            return make(p_bracket, false);
        }

        @Override
        protected String getUniqueText() {
            return "" + bracket;
        }

        @Override
        protected StringBuilder buildString(StringBuilder bldr) {
            bldr = super.buildString(bldr);
            bldr = bldr.append(" Bracket=").append(this.bracket);
            return bldr;
        } // buildString()

        @Override
        protected void validateCurrent(String str_base,
                NameToken tok_received) {
            assertEquals(str_base + "Bracket",
                    this.bracket,
                 ((NameGroupImpl) tok_received).getBracket());
        } // validateCurrent()
    } // class MockGroup

    private static class MockList  extends MockToken {

        int level = -1;

        private MockList() {
            super(TokenCode.List);
        }

        public static MockList make (int p_level, boolean b_open) {
            MockList test = new MockList();
            test.level = p_level;
            test.setOpen(b_open);
            return test;
        }

        public static MockList make (int p_level) {
            return make(p_level, false);
        }

        @Override
        protected String getUniqueText() {
            return "" + this.level;
        }

        @Override
        protected StringBuilder buildString(StringBuilder bldr) {
            bldr = super.buildString(bldr);
            bldr = bldr.append(" Level=").append(this.level);
            return bldr;
        } // buildString()

        @Override
        protected void validateCurrent(String str_base,
                NameToken tok_received) {
            assertEquals(str_base + "Level",
                    this.level,
                 ((NameListImpl) tok_received).getLevel());
        } // validateCurrent()
    } // class MockList



    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
        _l.setLevel(Level.FINEST);
        this.parser = new NameTokenizerImpl();
    }

    @After
    public void tearDown() throws Exception {
    }


    private MockToken mockRoot;
    private MockToken mockTos;



    @Test
    public void testMakeGroup() {
        NameTokenImpl tokGroup = NameGroupImpl.make(0);
        assertNotNull("Group", tokGroup);

        NameTokenImpl tokWord = NameAtomImpl.make("fred");
        assertNotNull("Word", tokWord);

        NameTokenImpl tokList = NameListImpl.make(0);
        assertNotNull("List", tokList);

    } // testMakeGroup()

    @Test(expected = NameToken.NameTokenException.class)
    public void testGroupAddNull() throws NameTokenException {
        NameTokenImpl tokGroup = NameGroupImpl.make(0);
        assertNotNull("Group", tokGroup);

        try {
            tokGroup.add(null);
        } catch (NameToken.NameTokenException ex) {
            assertEquals("Type",
                    ExceptionType.ExceptionInvalidParameter, ex.getType());
            assertEquals("Code",
                    ExceptionCode.ExceptionCannotBeNull, ex.getCode());
            throw ex;
        }

    } // testGroupAddNull()

    @Test
    public void testAddToWord() throws NameTokenException {
        String testName = "fred";

        initStack();
        mockTos.addChild(MockAtom.make(testName));

        NameTokenImpl tokGroupTest = NameGroupImpl.make(0);
        assertNotNull("Group Test", tokGroupTest);

        NameListImpl list0 = (NameListImpl) tos;

        NameTokenImpl tokWord = NameAtomImpl.make(testName);
        tos = tos.add(tokWord);
        assertEquals("Top of Stack: Add Word", list0, tos);
        mockRoot.validate("testAddToWord", tokRoot);
    } // testAddToWord()


    @Test
    public void testAddWordToList0() throws NameTokenException {
        String testName = "Ringo";

        initStack();

        mockTos.addChild(MockAtom.make(testName));

        NameListImpl tokList0 = (NameListImpl) tos;
        NameTokenImpl tokWord = NameAtomImpl.make(testName);
        tos = tos.add(tokWord);

        assertEquals("Top of Stack: Add Word", tokList0, tos);
        mockRoot.validate("testAddWordToList0", tokRoot);
    } // testAddWordToList0()

    @Test
    public void testAddSeveralWordsToList0() throws NameTokenException {
        String aWords[] = {"John", "Paul", "George", "Ringo"};

        initStack();
        NameListImpl tokList0 = (NameListImpl) tos;

        for (String strWord : aWords) {
            mockTos.addChild(MockAtom.make(strWord));

            tos = tos.add(NameAtomImpl.make(strWord));
            assertEquals("Top of Stack: Add Word [" + strWord + "]",
                    tokList0, tos);
        }

        mockRoot.validate("testAddSeveralWordsToList0", tokRoot);
    } // testAddSeveralWordsToList0()


    @Test
    public void testNullList()
            throws NameTokenException {

        NameToken root = parser.parse(null);
        assertNotNull("Root", root);

        assertTrue("Root", parser.isEmpty());
    } // testNullList()

    @Test
    public void testEmptyList()
            throws NameTokenException {
        NameToken root = parser.parse("");
        assertNotNull("Root", root);

        assertTrue("Root", parser.isEmpty());
    } // testNullList()


    @Test
    public void testOneWord()
            throws NameTokenException {
        final String _f = "testOneWord";
        final String strWord = "Ringo";

        initMock(0);
        mockTos.addChild(MockAtom.make(strWord));

        NameToken root = parser.parse("Ringo");
        assertNotNull("Root", root);

        mockRoot.validate(_f, root);
    } // testOneWord()

    @Test
    public void testFourWords()
            throws NameTokenException {
        final String _f = "testFourWords";
        String aWords[] = {"John", "Paul", "George", "Ringo"};

        initMock(0);
        for (String strWord : aWords) {
            mockTos.addChild(MockAtom.make(strWord));
        }

        NameToken root = parser.parse("John Paul George Ringo");
        mockRoot.validate(_f, root);
    } // testFourWords()

    @Test
    public void testListLevel1()
            throws NameTokenException {
        final String _f = "testListLevel1";
        final String testPhrase = "John Paul, George Ringo, called the Beatles";

        initMock(2);
        MockList list1 = MockList.make(1);
        MockList list2 = MockList.make(1);
        MockList list3 = MockList.make(1);

        list1.addChild(createMockList0(new String[] { "John", "Paul" } ));
        mockTos.addChild(list1);
        list2.addChild(createMockList0(new String[] { "George", "Ringo" } ));
        mockTos.addChild(list2);
        list3.addChild(createMockList0(new String[] {  "called", "the", "Beatles"} ));
        mockTos.addChild(list3);

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testListLevel1()

    @Test
    public void testListLevel2()
            throws NameTokenException {
        final String _f = "testListLevel2";
        final String testPhrase = "John,    Paul,George,Ringo,;  Lennon, McCartney, Harrison, Starr;";

        initMock(3);
        MockList list1 = MockList.make(2);
        list1.addChild(MockList.make(1).addChild(createMockList0("John")));
        list1.addChild(MockList.make(1).addChild(createMockList0("Paul")));
        list1.addChild(MockList.make(1).addChild(createMockList0("George")));
        list1.addChild(MockList.make(1).addChild(createMockList0("Ringo")));
        list1.addChild(MockList.make(1).addChild(MockList.make(0)));
        mockTos.addChild(list1);

        MockList list2 = MockList.make(2);
        list2.addChild(MockList.make(1).addChild(createMockList0("Lennon")));
        list2.addChild(MockList.make(1).addChild(createMockList0("McCartney")));
        list2.addChild(MockList.make(1).addChild(createMockList0("Harrison")));
        list2.addChild(MockList.make(1).addChild(createMockList0("Starr")));
        mockTos.addChild(list2);

        mockTos.addChild(MockList.make(2).
                addChild(MockList.make(1).
                addChild(MockList.make(0))));

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testListLevel2()

    @Test
    public void testGroup1()
            throws NameTokenException {
        final String _f = "testGroup1";
        final String testPhrase = "A B, (C D) E";
        initMock(2);

        MockList list0AB = createMockList0(new String[] {"A", "B"});
        MockList list0CD = createMockList0(new String[] {"C", "D"});
        MockAtom atom0E = MockAtom.make("E");

        MockGroup groupCD = MockGroup.make(1);
        groupCD.addChild(list0CD);

        MockList list0CDE = MockList.make(0);
        list0CDE.addChild(groupCD);
        list0CDE.addChild(atom0E);

        MockList list1AB = MockList.make(1);
        list1AB.addChild(list0AB);

        MockList list1CDE = MockList.make(1);
        list1CDE.addChild(list0CDE);

        mockTos.addChild(list1AB);
        mockTos.addChild(list1CDE);

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testGroup1()

    @Test
    public void testGroup2()
            throws NameTokenException {
        final String _f = "testGroup2";
        final String testPhrase = "A B, (C D) ( E [F])";
        initMock(2);

        MockList list0AB = createMockList0(new String[] {"A", "B"});
        MockList list0CD = createMockList0(new String[] {"C", "D"});
        MockAtom atom0E = MockAtom.make("E");
        MockList list0F = createMockList0("F");

        MockGroup groupCD = MockGroup.make(1);
        groupCD.addChild(list0CD);

        MockGroup groupEF = MockGroup.make(1);

        MockGroup groupF = MockGroup.make(2);
        groupF.addChild(list0F);

        MockList list0EF = MockList.make(0);
        list0EF.addChild(atom0E);
        list0EF.addChild(groupF);
        groupEF.addChild(list0EF);

        MockList list0CDEF = MockList.make(0);
        list0CDEF.addChild(groupCD);
        list0CDEF.addChild(groupEF);

        MockList list1AB = MockList.make(1);
        list1AB.addChild(list0AB);

        MockList list1CDEF = MockList.make(1);
        list1CDEF.addChild(list0CDEF);

        mockTos.addChild(list1AB);
        mockTos.addChild(list1CDEF);

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testGroup2()

    @Test
    public void testGroup3()
            throws NameTokenException {
        final String _f = "testGroup3";
        final String testPhrase = "A B, (C D) ( E [F)";
        initMock(2);

        MockList list0AB = createMockList0(new String[] {"A", "B"});
        MockList list0CD = createMockList0(new String[] {"C", "D"});
        MockAtom atom0E = MockAtom.make("E");
        MockList list0F = createMockList0("F");

        MockGroup groupCD = MockGroup.make(1);
        groupCD.addChild(list0CD);

        MockGroup groupEF = MockGroup.make(1);

        MockGroup groupF = MockGroup.make(2);
        groupF.addChild(list0F);

        MockList list0EF = MockList.make(0);
        list0EF.addChild(atom0E);
        list0EF.addChild(groupF);
        groupEF.addChild(list0EF);

        MockList list0CDEF = MockList.make(0);
        list0CDEF.addChild(groupCD);
        list0CDEF.addChild(groupEF);

        MockList list1AB = MockList.make(1);
        list1AB.addChild(list0AB);

        MockList list1CDEF = MockList.make(1);
        list1CDEF.addChild(list0CDEF);

        mockTos.addChild(list1AB);
        mockTos.addChild(list1CDEF);

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testGroup3()

    @Test
    public void testGroup4()
            throws NameTokenException {
        final String _f = "testGroup4";
        final String testPhrase = "A B, (C D) ( E [F";
        initMock(2);

        MockList list0AB = createMockList0(new String[] {"A", "B"});
        MockList list0CD = createMockList0(new String[] {"C", "D"});
        MockAtom atom0E = MockAtom.make("E");
        MockList list0F = createMockList0("F");

        MockGroup groupCD = MockGroup.make(1);
        groupCD.addChild(list0CD);

        MockGroup groupEF = MockGroup.make(1);

        MockGroup groupF = MockGroup.make(2);
        groupF.addChild(list0F);

        MockList list0EF = MockList.make(0);
        list0EF.addChild(atom0E);
        list0EF.addChild(groupF);
        groupEF.addChild(list0EF);

        MockList list0CDEF = MockList.make(0);
        list0CDEF.addChild(groupCD);
        list0CDEF.addChild(groupEF);

        MockList list1AB = MockList.make(1);
        list1AB.addChild(list0AB);

        MockList list1CDEF = MockList.make(1);
        list1CDEF.addChild(list0CDEF);

        mockTos.addChild(list1AB);
        mockTos.addChild(list1CDEF);

        // System.out.print(_f + " " + mockRoot.dump());
        // mockRoot.trace();
        NameToken root = parser.parse(testPhrase);
        mockRoot.validate(_f, root);
    } // testGroup4()

    private MockList createMockList0(String[] p_words) {
        MockList list0 = MockList.make(0);
        for (String strWord : p_words) {
            list0.addChild(MockAtom.make(strWord));
        } // for

        return list0;
    } // createMockList0()

    private MockList createMockList0(String p_word) {
        return createMockList0(new String[] { p_word});
    } // createMockList0()


    private void initStack() throws NameTokenException {
        tokRoot = NameGroupImpl.make(0);
        assertNotNull("Group0", tokRoot);
        NameListImpl list0 = NameListImpl.make(0);
        assertNotNull("List0", list0);
        NameTokenImpl tokWord = NameAtomImpl.make("fred");
        assertNotNull("Word", tokWord);

        tos = tokRoot.add(list0);
        assertNotNull("Group Test", tos);
        assertEquals("Top of Stack: Add List0", list0, tos);

        mockRoot = MockGroup.make(0, true);
        mockTos = MockList.make(0, true);
        mockRoot.addChild(mockTos);
    } // initStack()

    private void initMock(int levelList) {
        mockRoot = MockGroup.make(0);
        mockTos = MockList.make(levelList);
        mockRoot.addChild(mockTos);
    } // initMock()
} // class ParsedNameTest
