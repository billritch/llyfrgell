package org.llyfrgell.model.dao;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.llyfrgell.model.UniqueBase;
import org.llyfrgell.model.codes.Aspect;
import org.llyfrgell.model.codes.Category;
import org.llyfrgell.model.codes.Color;
import org.llyfrgell.model.codes.Format;
import org.llyfrgell.model.codes.Genre;
import org.llyfrgell.model.codes.Language;
import org.llyfrgell.model.codes.Medium;
import org.llyfrgell.model.codes.Metier;
import org.llyfrgell.model.codes.Sound;
import org.llyfrgell.model.codes.Speed;
import org.llyfrgell.model.codes.Version;
import org.llyfrgell.model.creator.Creator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/***
 * A database simulator for storing the legacy tables are we build the new
 * database.
 *
 * @author William Alan Ritch Feb 19, 2016
 *
 */
public class PseudoDaoImpl {

    private Map<Long, Aspect> _mapAspect;
    private Map<Long, Category> _mapCategory;
    private Map<Long, Color> _mapColor;
    private Map<Long, Format> _mapFormat;
    private Map<Long, Genre> _mapGenre;
    private Map<Long, Language> _mapLanguage;
    private Map<Long, Medium> _mapMedium;
    private Map<Long, Metier> _mapMetier;
    private Map<Long, Sound> _mapSound;
    private Map<Long, Speed> _mapSpeed;
    private Map<Long, Version> _mapVersion;

    private Map<Long, Creator> _mapCreator;

    private XPath xpath;

    public PseudoDaoImpl() {
        _mapAspect = null;
        _mapCategory = null;
        _mapColor = null;
        _mapFormat = null;
        _mapGenre = null;
        _mapLanguage = null;
        _mapMedium = null;
        _mapMetier = null;
        _mapSound = null;
        _mapSpeed = null;
        _mapVersion = null;

        xpath = XPathFactory.newInstance().newXPath();
    } // PseudoDaoImpl()

    public void readXml(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);

            if (null != doc) {
                // handle the lists of items
                Element elRoot = (Element) xpath.evaluate("/llyfrgell", doc, XPathConstants.NODE);

                _mapAspect = buildListFromXml(Aspect.class, elRoot);
                _mapCategory = buildListFromXml(Category.class, elRoot);
                _mapColor = buildListFromXml(Color.class, elRoot);
                _mapFormat = buildListFromXml(Format.class, elRoot);
                _mapGenre = buildListFromXml(Genre.class, elRoot);
                _mapLanguage = buildListFromXml(Language.class, elRoot);
                _mapMedium = buildListFromXml(Medium.class, elRoot);
                _mapMetier = buildListFromXml(Metier.class, elRoot);
                _mapSound = buildListFromXml(Sound.class, elRoot);
                _mapSpeed = buildListFromXml(Speed.class, elRoot);
                _mapVersion = buildListFromXml(Version.class, elRoot);
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } // readXml()

    private <T extends UniqueBase> Map<Long, T> buildListFromXml(
            Class<T> cls, Element elRoot) {

        Map<Long, T> list = new HashMap<Long, T>();

        try {

            // build a simple object
            T item = cls.newInstance();
            String pathName = item.getXmlListName()
                    + "/" + item.getXmlName();

            NodeList nodes = (NodeList) xpath.evaluate(pathName,
                    elRoot,
                    XPathConstants.NODESET);

            if ((null != nodes) && (nodes.getLength() > 0)) {
                int nLength = nodes.getLength();

                // iterate through the nodes
                for(int i = 0; i < nLength; i++) {
                    Node node = nodes.item(i);
                    if (null != node) {
                        item = cls.newInstance();
                        if (item.fromXml((Element) node)) {
                            list.put(item.getId(), item);
                        } // if fromXml
                    } // if (null != node)
                } // for i
            } // if nodes not empty
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
      } // buildListFromXml()


    public Map<Long, Aspect> listAspect()
    {
        if (null == _mapAspect) {
            _mapAspect = new HashMap<Long, Aspect>();
        }
        return _mapAspect;
    } // listAspect()

    public Map<Long, Category> listCategory()
    {
        if (null == _mapCategory) {
            _mapCategory = new HashMap<Long, Category>();
        }
        return _mapCategory;
    } // _mapCategory()

    public Map<Long, Color> listColor()
    {
        if (null == _mapColor) {
            _mapColor = new HashMap<Long, Color>();
        }
        return _mapColor;
    } // listColor()

    public Map<Long, Format> listFormat()
    {
        if (null == _mapFormat) {
            _mapFormat = new HashMap<Long, Format>();
        }
        return _mapFormat;
    } // listFormat()

    public Map<Long, Genre> listGenre()
    {
        if (null == _mapGenre) {
            _mapGenre = new HashMap<Long, Genre>();
        }
        return _mapGenre;
    } // listGenre()

    public Map<Long, Language> listLanguage()
    {
        if (null == _mapLanguage) {
            _mapLanguage = new HashMap<Long, Language>();
        }
        return _mapLanguage;
    } // listLanguage()

    public Map<Long, Medium> listMedium()
    {
        if (null == _mapMedium) {
            _mapMedium = new HashMap<Long, Medium>();
        }
        return _mapMedium;
    } // listMedium()

    public Map<Long, Metier> listMetier()
    {
        if (null == _mapMetier) {
            _mapMetier = new HashMap<Long, Metier>();
        }
        return _mapMetier;
    } // listMetier()

    public Map<Long, Sound> listSound()
    {
        if (null == _mapSound) {
            _mapSound = new HashMap<Long, Sound>();
        }
        return _mapSound;
    } // listSound()

    public Map<Long, Speed> listSpeed()
    {
        if (null == _mapSpeed) {
            _mapSpeed = new HashMap<Long, Speed>();
        }
        return _mapSpeed;
    } // listSpeed()

    public Map<Long, Version> listVersion()
    {
        if (null == _mapVersion) {
            _mapVersion = new HashMap<Long, Version>();
        }
        return _mapVersion;
    } // listVersion()

    public Map<Long, Creator> listCreator()
    {
        if (null == _mapCreator) {
            _mapCreator = new HashMap<Long, Creator>();
        }
        return _mapCreator;
    } // listCreator()


} // class PseudoDaoImpl
