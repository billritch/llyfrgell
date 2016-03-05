package org.llyfrgell.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.llyfrgell.legacy.EnumList;
import org.llyfrgell.legacy.conversion.ArtistConverter;
import org.llyfrgell.legacy.conversion.AspectRatioConverter;
import org.llyfrgell.legacy.conversion.CategoryConverter;
import org.llyfrgell.legacy.conversion.ColourConverter;
import org.llyfrgell.legacy.conversion.CutConverter;
import org.llyfrgell.legacy.conversion.DutyConverter;
import org.llyfrgell.legacy.conversion.GenreConverter;
import org.llyfrgell.legacy.conversion.LabelConverter;
import org.llyfrgell.legacy.conversion.LanguageConverter;
import org.llyfrgell.legacy.conversion.MediaConverter;
import org.llyfrgell.legacy.conversion.RecordingFormatConverter;
import org.llyfrgell.legacy.conversion.RecordingSpeedConverter;
import org.llyfrgell.legacy.conversion.SoundConverter;
import org.llyfrgell.legacy.generated.Artists;
import org.llyfrgell.legacy.generated.AspectRatio;
import org.llyfrgell.legacy.generated.Category;
import org.llyfrgell.legacy.generated.Colour;
import org.llyfrgell.legacy.generated.Cut;
import org.llyfrgell.legacy.generated.Duty;
import org.llyfrgell.legacy.generated.Genre;
import org.llyfrgell.legacy.generated.Label;
import org.llyfrgell.legacy.generated.Language;
import org.llyfrgell.legacy.generated.Media;
import org.llyfrgell.legacy.generated.RecordingFormat;
import org.llyfrgell.legacy.generated.RecordingSpeed;
import org.llyfrgell.legacy.generated.Sound;
import org.llyfrgell.model.artist.person.Artist;
import org.llyfrgell.model.codes.Aspect;
import org.llyfrgell.model.codes.Color;
import org.llyfrgell.model.codes.Format;
import org.llyfrgell.model.codes.Medium;
import org.llyfrgell.model.codes.Metier;
import org.llyfrgell.model.codes.Speed;
import org.llyfrgell.model.codes.Version;
import org.llyfrgell.model.creator.impl.CreatorImpl;
import org.llyfrgell.model.dao.LegacyDao;
import org.llyfrgell.model.dao.PseudoDaoImpl;
import org.llyfrgell.model.name.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author William Alan Ritch Nov 13, 2013
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/org/llyfrgell/applicationContext.xml" })
// @TransactionConfiguration(defaultRollback=false)
@Transactional
public class TestHibernateSpring extends
        AbstractTransactionalJUnit4SpringContextTests
// extends AbstractJUnit4SpringContextTests
{
    private static final String strPrimerXml = "PreExisting.xml";

    private Document xmlDocument;
    private Element elRoot;
    private LegacyDao dao;
    private static PseudoDaoImpl pseudoDao;
    private XPath path;

    @Autowired
    private ApplicationContext context;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        pseudoDao = new PseudoDaoImpl();
        ClassLoader classLoader = pseudoDao.getClass().getClassLoader();
        File file = new File(classLoader.getResource(strPrimerXml).getFile());
        // read the XML file if it exists
        pseudoDao.readXml(file);
    } // setUpBeforeClass()

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        pseudoDao = null;
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .newDocument();
        assertNotNull("XML document", xmlDocument);

        dao = (LegacyDao) context.getBean("legacyDao");
        assertNotNull("Legacy DAO", dao);

        elRoot = xmlDocument.createElement("test");
        assertNotNull("Test root", elRoot);
        xmlDocument.appendChild(elRoot);

        path = XPathFactory.newInstance().newXPath();

        pseudoDao = new PseudoDaoImpl();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        prettyPrint(xmlDocument);
    }

    /**
     * Make sure context and session factory is defined.
     */
    @Test
    public void testContext() {
        assertNotNull("Spring context", context);
        Object o = context.getBean("legacySessionFactory");
        assertNotNull("Session Factory", o);

    }

    @Test
    public void testLegacyDAO() {
        Object o = context.getBean("legacyDao");
        assertNotNull("Legacy DAO", o);
        System.out.println("DAO: " + o.getClass().getCanonicalName());
        assertTrue("Legacy DAO Interface", o instanceof LegacyDao);
    } // testLegacyDAO()

    @Test
    public void testLegacyAspects() {
        List<AspectRatio> listLegacyCodes = dao.listAspect();
        assertNotNull("AspectRatio List", listLegacyCodes);

        AspectRatioConverter converter = new AspectRatioConverter();

        System.out.println("Aspect");
        try {
            Node elList = null;

            for (AspectRatio legacy : listLegacyCodes) {
                // create a Code object
                Aspect codeItem = converter.convert(legacy);
                printConversion(System.out, legacy, codeItem);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getAspect().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getAspectName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getAspectName());

                elList.appendChild(el);

                pseudoDao.listAspect().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listAspect().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyAspects()

    @Test
    public void testLegacyCategory() {
        List<Category> listLegacyCodes = dao.listCategory();
        assertNotNull("Category List", listLegacyCodes);

        CategoryConverter converter = new CategoryConverter();

        System.out.println("Category");
        try {
            Node elList = null;

            for (Category legacy : listLegacyCodes) {
                // create a Code object
                org.llyfrgell.model.codes.Category codeItem = converter
                        .convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getCategory().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getCategoryName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getCategoryName());

                elList.appendChild(el);
                pseudoDao.listCategory().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listCategory().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyCategory()

    @Test
    public void testLegacyColor() {
        List<Colour> listLegacyCodes = dao.listColor();
        assertNotNull("Color List", listLegacyCodes);

        ColourConverter converter = new ColourConverter();

        System.out.println("Color");
        try {
            Node elList = null;

            for (Colour legacy : listLegacyCodes) {
                // create a Code object
                Color codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getColour().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getColourText());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getColourText());

                elList.appendChild(el);
                pseudoDao.listColor().put(codeItem.getId(), codeItem);

                assertTrue("Not Empty", !pseudoDao.listColor().isEmpty());
            } // for legacy
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyColors()

    @Test
    public void testLegacyCut() {
        List<Cut> listLegacyCodes = dao.listCut();
        assertNotNull("Cut List", listLegacyCodes);

        CutConverter converter = new CutConverter();

        System.out.println("Cut");
        try {
            Node elList = null;

            for (Cut legacy : listLegacyCodes) {
                // create a Code object
                Version codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getCut().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getCutName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getCutName());

                elList.appendChild(el);
                pseudoDao.listVersion().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listVersion().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyCut()

    @Test
    public void testLegacyDuty() {
        List<Duty> listLegacyCodes = dao.listDuty();
        assertNotNull("Duty List", listLegacyCodes);

        DutyConverter converter = new DutyConverter();

        System.out.println("Duty");
        try {
            Node elList = null;

            for (Duty legacy : listLegacyCodes) {
                // create a Code object
                Metier codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getDutyNr().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getDuty());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getDuty());

                elList.appendChild(el);
                pseudoDao.listMetier().put(codeItem.getId(), codeItem);

                assertTrue("Not Empty", !pseudoDao.listMetier().isEmpty());
            } // for legacy
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyDuty()

    @Test
    public void testLegacyGenre() {
        List<Genre> listLegacyCodes = dao.listGenre();
        assertNotNull("Genre List", listLegacyCodes);

        GenreConverter converter = new GenreConverter();

        System.out.println("Genre");
        try {
            Node elList = null;

            for (Genre legacy : listLegacyCodes) {
                // create a Code object
                org.llyfrgell.model.codes.Genre codeItem = converter
                        .convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getGenre().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getGenreName());

                elList.appendChild(el);
                pseudoDao.listGenre().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listGenre().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyGenre()

    @Test
    public void testLegacyLanguage() {
        List<Language> listLegacyCodes = dao.listLanguage();
        assertNotNull("Language List", listLegacyCodes);

        LanguageConverter converter = new LanguageConverter();

        System.out.println("Language");
        try {
            Node elList = null;

            for (Language legacy : listLegacyCodes) {
                // create a Code object
                org.llyfrgell.model.codes.Language codeItem = converter
                        .convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getLanguage().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getLanguageName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getLanguageName());

                elList.appendChild(el);
                pseudoDao.listLanguage().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listLanguage().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyLanguage()

    @Test
    public void testLegacyMedia() {
        List<Media> listLegacyCodes = dao.listMedia();
        assertNotNull("Media List", listLegacyCodes);

        MediaConverter converter = new MediaConverter();

        System.out.println("Media");
        try {
            Node elList = null;

            for (Media legacy : listLegacyCodes) {
                // create a Code object
                Medium codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getMedium().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getMediumName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getMediumName());

                elList.appendChild(el);
                pseudoDao.listMedium().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listMedium().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyMedia()

    @Test
    public void testLegacyRecordingFormat() {
        List<RecordingFormat> listLegacyCodes = dao.listRecordingFormat();
        assertNotNull("Recording Format List", listLegacyCodes);

        RecordingFormatConverter converter = new RecordingFormatConverter();

        System.out.println("Recording Format");
        try {
            Node elList = null;

            for (RecordingFormat legacy : listLegacyCodes) {
                // create a Code object
                Format codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getFormatId().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getFormat());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getFormat());

                elList.appendChild(el);
                pseudoDao.listFormat().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listFormat().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyRecordingFormat()

    @Test
    public void testLegacyRecordingSpeed() {
        List<RecordingSpeed> listLegacyCodes = dao.listRecordingSpeed();
        assertNotNull("RecordingSpeed List", listLegacyCodes);

        RecordingSpeedConverter converter = new RecordingSpeedConverter();

        System.out.println("Recording Speed");
        try {
            Node elList = null;

            for (RecordingSpeed legacy : listLegacyCodes) {
                // create a Code object
                Speed codeItem = converter.convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getSpeed().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getSpeedName());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getSpeedName());

                elList.appendChild(el);
                pseudoDao.listSpeed().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listSpeed().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyRecordingSpeed()

    @Test
    public void testLegacySound() {
        List<Sound> listLegacyCodes = dao.listSound();
        assertNotNull("Sound List", listLegacyCodes);

        SoundConverter converter = new SoundConverter();

        System.out.println("Sound");
        try {
            Node elList = null;

            for (Sound legacy : listLegacyCodes) {
                // create a Code object
                org.llyfrgell.model.codes.Sound codeItem = converter
                        .convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getSound().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getSoundText());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getSoundText());

                elList.appendChild(el);
                pseudoDao.listSound().put(codeItem.getId(), codeItem);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listSound().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacySound()


    @Test
    public void testLegacyLabel() {
        List<Label> listLegacyCodes = dao.listLabel();
        assertNotNull("Sound List", listLegacyCodes);

        LabelConverter converter = new LabelConverter();

        System.out.println("Label");
        try {
            Node elList = null;

            for (Label legacy : listLegacyCodes) {
                // create a Code object
                CreatorImpl creator = converter
                        .convert(legacy);

                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            creator.getXmlListName()));
                }

                printConversion(System.out, legacy, creator);

                // XML node generation
                Element el = creator.toXml(xmlDocument);

                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getId().toString());

                assertEquals("names", "1", path.compile("count(names)").evaluate(el));
                elList.appendChild(el);
                pseudoDao.listCreator().put(creator.getId(), creator);
            } // for legacy

            assertTrue("Not Empty", !pseudoDao.listCreator().isEmpty());
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyLabel()


    /***
     * More complicated conversions
     */

    @Test
    public void testLegacyArtists() {
        List<Artists> listLegacyCodes = dao.listArtists();
        assertNotNull("Artists List", listLegacyCodes);

        ArtistConverter converter = new ArtistConverter();

        System.out.println("Artists");
        try {
            Node elList = null;

            System.out.println(" Count=" + listLegacyCodes.size());

            for (Artists legacy : listLegacyCodes) {
                // create a Code object
                Artist codeItem = converter.convert(legacy);
                if (null == elList) {
                    elList = elRoot.appendChild(xmlDocument.createElement(
                            codeItem.getXmlListName()));
                }

                Name name = codeItem.getName();
                String s = name.getSortName().toString();

                System.out.println(
                        String.format("'%s': [sort:'%s']-%s",
                                name.getFullName(),
                                name.getSortName(),
                                legacy.getArtist()
                            ));


//                printConversion(System.out, legacy, codeItem);

                // XML node generation
                Element el = codeItem.toXml(xmlDocument);

                /*
                assertEquals("ID", path.compile("id/text()").evaluate(el),
                        legacy.getId().toString());
                assertEquals("name", path.compile("name/text()").evaluate(el),
                        legacy.getText());
                assertEquals("description", path.compile("description/text()")
                        .evaluate(el), legacy.getText());
*/
                elList.appendChild(el);
            } // for legacy
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.toString());
            assertTrue(false);
        }
    } // testLegacyArtists()

    private void prettyPrint(Document doc)
            throws TransformerFactoryConfigurationError, TransformerException {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));
        System.out.println(out.toString());
    }

    private void printConversion(PrintStream out, EnumList legacy,
            Object codeItem) {
        // print
        System.out.println("  Before: " + legacy.toString());
        System.out.println("  After:  " + codeItem.toString());
    }

} // class TestHibernateSpring
