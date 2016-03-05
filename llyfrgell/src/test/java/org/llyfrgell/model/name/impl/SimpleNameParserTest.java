/**
 * SimpleNameParserTest.java Jan 27, 2014
 */
package org.llyfrgell.model.name.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.llyfrgell.model.name.NameParser;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;
import org.llyfrgell.model.name.NameTokenizer;
import org.llyfrgell.model.name.OneName;

/**
 * @author William Alan Ritch Jan 27, 2014
 *
 */
public class SimpleNameParserTest {

    private NameTokenizer t;
    private NameParser p;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        t = new NameTokenizerImpl();
        p = new SimpleNameParser();
        assertNotNull("Parser", p);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEmptyParse() throws NameTokenException {
        String strNoName = "";

        NameToken tok = t.parse(strNoName);
        assertNotNull("Token", tok);

        assertFalse("Token should have no children.", tok.isParent());
    } // testEmptyParse()

    @Test
    public void testOneNameOnly() throws NameTokenException {
        String strOneName = "Cher";

        // A single name is a List0 of 1 element
        p.addFieldParser(new SurnameFirstFieldParser());

        OneName name = (OneName) p.parse(t.parse(strOneName));
        assertNotNull("Name", name);
        assertEquals("Surname", strOneName, name.getSurname().toString());
    } // testOneNameOnly()

    @Test
    public void testSurnameLast() throws NameTokenException {
        String strFullName = "Robert Heinlein";

        p.addFieldParser(new SurnameLastFieldParser());

        NameToken tok = t.parse(strFullName);
        OneName name = (OneName) p.parse(tok);
        String x = tok.toString();

        assertNotNull("x", x);
        assertNotNull("Name", name);
        assertEquals("Surname", "Heinlein", name.getSurname().toString());
        assertEquals("Given Name", "Robert", name.getGivenName().toString());
    } // testSurnameLast()

    @Test
    public void testSurnameLastWithMiddle() throws NameTokenException {
        String strFullName = "Robert Anson Heinlein";

        p.addFieldParser(new SurnameLastFieldParser());

        NameToken tok = t.parse(strFullName);
        OneName name = (OneName) p.parse(tok);
        String x = tok.toString();

        assertNotNull("x", x);
        assertNotNull("Name", name);
        // assertEquals("One name", 1, name.size());

        // OneName name = name.get(0);
        assertEquals("Surname", "Heinlein", name.getSurname().toString());
        assertEquals("Given Name", "Robert Anson", name.getGivenName().toString());
    } // testSurnameLastWithMiddle()


    @Test
    public void testSurnameFirst() throws NameTokenException {
        String strFullName = "Heinlein, Robert";

        // When the Surname is first it will create a level 2 list
        // There will be at least 2 elements:
        //  [0] the last name (may be a list or an atom)
        //  [1] the given name (may be a list or an atom)
        //  [2] prefixes and suffixes

        p.addFieldParser(new SurnameFirstFieldParser());

        NameToken tok = t.parse(strFullName);
        OneName name = (OneName) p.parse(tok);
        String x = tok.toString();
        assertNotNull("Token string", x);
        assertNotNull("Name", name);
        assertEquals("Surname", "Heinlein", name.getSurname().toString().toString());
        assertEquals("Given Name", "Robert", name.getGivenName().toString().toString());
    } // testSurnameLast()

    @Test
    public void testSurnameFirstWithMiddle() throws NameTokenException {
        String strFullName = "Heinlein, Robert Anson";

        p.addFieldParser(new SurnameFirstFieldParser());

        NameToken tok = t.parse(strFullName);
        OneName name = (OneName) p.parse(tok);
        String x = tok.toString();
        assertNotNull("Token string", x);
        assertNotNull("Name", name);
        assertEquals("Surname", "Heinlein", name.getSurname().toString());
        assertEquals("Given Name", "Robert Anson", name.getGivenName().toString());
    } // testSurnameFirstWithMiddle()

    @Test
    public void testSurnameFirst2Words() throws NameTokenException {
        String strFullName = "van Vogt, A E";

        p.addFieldParser(new SurnameFirstFieldParser());

        OneName name = (OneName) p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertEquals("Surname", "van Vogt", name.getSurname().toString());
        assertEquals("Given Name", "A E", name.getGivenName().toString());
    } // testSurnameFirst2Words()

    @Test
    public void testMultiwordSingleName() throws NameTokenException {
        String strFullName = "Royal Scottish National Orchestra";

        p.addFieldParser(new SurnameFirstFieldParser());

        NameToken tok = t.parse(strFullName);
        OneName name = (OneName) p.parse(tok);
        String x = tok.toString();

        assertNotNull("Name", name);
        assertEquals("Surname", strFullName, name.getSurname().toString());
        assertEquals("Given Name", "", name.getGivenName().toString());
    } // testMultiwordSingleName()

    @Test
    public void testNameWithTitle() throws NameTokenException {
        String strFullName = "Sullivan, Sir Arthur";

        AffixFieldParser parser = new AffixFieldParser();
        parser.setPrefix(new PrefixHandler());
        parser.setSuffix(new SuffixHandler());

        p.addFieldParser(parser);
        p.addFieldParser(new SurnameFirstFieldParser());

        OneName name = (OneName) p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertEquals("Surname", "Sullivan", name.getSurname().toString());
        assertEquals("Given Name", "Arthur", name.getGivenName().toString());
        assertEquals("Title", "Sir", name.getTitles().toString());
    } // testSurnameFirst2Words()

    @Test
    public void testHonorifcs() throws NameTokenException {
        String strFullName = "Smith, E. E. (Ph.D.)";

        p.addFieldParser(new SurnameFirstFieldParser());
        p.addFieldParser(new HonorificFieldParser());

        OneName name = (OneName) p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertEquals("Surname", "Smith", name.getSurname().toString());
        assertEquals("Given Name", "E. E.", name.getGivenName().toString());
        assertNotNull("Honorific", name.getHonorific());
        assertEquals("Honorific", "Ph.D.", name.getHonorific().toString());
    } // testHonorifcs()

    @Test
    public void testHonorifcs2() throws NameTokenException {
        String strFullName = "E. E. Smith (Ph.D.)";

        p.addFieldParser(new HonorificFieldParser());
        p.addFieldParser(new SurnameLastFieldParser());

        OneName name = (OneName) p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertNotNull("Honorific", name.getHonorific());
        assertEquals("Honorific", "Ph.D.", name.getHonorific().toString());
        assertEquals("Surname", "Smith", name.getSurname().toString());
        assertEquals("Given Name", "E. E.", name.getGivenName().toString());
    } // testHonorifcs()

    @Test
    public void testGeneration() throws NameTokenException {
        String strFullName = "Strauss, Johann II";

        p.addFieldParser(new AffixFieldParser());
        p.addFieldParser(new SurnameFirstFieldParser());

        OneName name = (OneName) p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertEquals("Surname", "Strauss", name.getSurname().toString());
        assertEquals("Given Name", "Johann", name.getGivenName().toString());
        assertEquals("Generation", 2, name.getGeneration());
        assertEquals("Generation Text", "II", name.getGenerationText().toString());
    } // testHonorifcs()

    @Test
    public void testJunior() throws Exception {
        doGenerationTest("Junior", "Jr.", -1);
        doGenerationTest("Jr", "Jr.", -1);
        doGenerationTest("Jr.", "Jr.", -1);
    } // testJunior()

    @Test
    public void testSenior() throws Exception {
        doGenerationTest("Senior", "Sr.", -2);
        doGenerationTest("Sr.", "Sr.", -2);
        doGenerationTest("Sr", "Sr.", -2);
    } // testJunior()

    @Test
    public void testRomanNumerals() throws Exception {
        doRomanTest(1, "I");
        doRomanTest(2, "II");
        doRomanTest(3, "III");
        doRomanTest(4, "IV");
        doRomanTest(5, "V");
        doRomanTest(6, "VI");
        doRomanTest(7, "VII");
        doRomanTest(8, "VIII");
        doRomanTest(9, "IX");
        doRomanTest(10, "X");
        doRomanTest(50, "L");
        doRomanTest(100, "C");
        doRomanTest(500, "D");
        doRomanTest(1000, "M");
        doRomanTest(31, "XXXI");
        doRomanTest(148, "CXLVIII");
        doRomanTest(294, "CCXCIV");
        doRomanTest(312, "CCCXII");
        doRomanTest(421, "CDXXI");
        doRomanTest(528, "DXXVIII");
        doRomanTest(621, "DCXXI");
        doRomanTest(782, "DCCLXXXII");
        doRomanTest(870, "DCCCLXX");
        doRomanTest(941, "CMXLI");
        doRomanTest(1043, "MXLIII");
        doRomanTest(1110, "MCX");
        doRomanTest(1226, "MCCXXVI");
        doRomanTest(1301, "MCCCI");
        doRomanTest(1485, "MCDLXXXV");
        doRomanTest(1509, "MDIX");
        doRomanTest(1607, "MDCVII");
        doRomanTest(1754, "MDCCLIV");
        doRomanTest(1832, "MDCCCXXXII");
        doRomanTest(1993, "MCMXCIII");
        doRomanTest(2074, "MMLXXIV");
        doRomanTest(2152, "MMCLII");
        doRomanTest(2212, "MMCCXII");
        doRomanTest(2343, "MMCCCXLIII");
        doRomanTest(2499, "MMCDXCIX");
        doRomanTest(2574, "MMDLXXIV");
        doRomanTest(2646, "MMDCXLVI");
        doRomanTest(2723, "MMDCCXXIII");
        doRomanTest(2892, "MMDCCCXCII");
        doRomanTest(2975, "MMCMLXXV");
        doRomanTest(3051, "MMMLI");
        doRomanTest(3185, "MMMCLXXXV");
        doRomanTest(3250, "MMMCCL");
        doRomanTest(3313, "MMMCCCXIII");
        doRomanTest(3408, "MMMCDVIII");
        doRomanTest(3501, "MMMDI");
        doRomanTest(3610, "MMMDCX");
        doRomanTest(3743, "MMMDCCXLIII");
        doRomanTest(3844, "MMMDCCCXLIV");
        doRomanTest(3888, "MMMDCCCLXXXVIII");
        doRomanTest(3940, "MMMCMXL");
        doRomanTest(3999, "MMMCMXCIX");
        // doRomanTest(5000, "\u2180");
    } // testRomanNumerals()

    private void doRomanTest(long num, String roman) throws Exception {
        SimpleName name = new SimpleName();

        name.setGeneration(roman);
        assertEquals(String.format("Parsing '%s'", roman), num,
                name.getGeneration());

        name.setGeneration((int) num);
        assertEquals(String.format("Encoding '%d'", num), roman,
                name.getGenerationText());
    } // doRomanTest()

    private void doGenerationTest(String str_generation, String str_canonical,
            int num) throws Exception {
        SimpleName name = new SimpleName();

        name.setGeneration(str_generation);
        assertEquals(String.format("Parsing '%s'", str_generation), num,
                name.getGeneration());

        name.setGeneration(num);
        assertEquals(String.format("Encoding '%d'", num), str_canonical,
                name.getGenerationText());
    } // doRomanTest()

    /*
     *   id=16 text='Sullivan, Sir Arthur'
     *       id=70 text='Andrews Sisters'
  id=74 text='Captain and Tennille'
  id=76 text='Emerson, Lake and Palmer'
  id=144 text='Sherman, Richard M. and Robert B.'
  id=179 text='Volman, Mark (Flo)'
  id=394 text='Simpsons, The'

     */

    /*
    @Test
    public void testMultipleNames() throws NameTokenException {
        String strFullName = "Heinlein, Robert Anson; Asimov, Isaac";

        OneName name = p.parse(t.parse(strFullName));
        assertNotNull("Name", name);
        assertEquals("Two name", 2, listNames.size());

        // first name
        OneName name = listNames.get(0);
        assertEquals("Surname", "Heinlein", name.getSurname().toString());
        assertEquals("Given Name", "Robert Anson", name.getGivenName().toString());

        // second name
        name = listNames.get(1);
        assertEquals("Surname", "Asimov", name.getSurname().toString());
        assertEquals("Given Name", "Isaac", name.getGivenName().toString());
    } // testMultipleNames()

*/
}
