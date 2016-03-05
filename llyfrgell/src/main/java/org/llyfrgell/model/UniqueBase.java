/**
 * UniqueBase.java Jan 21, 2014
 */
package org.llyfrgell.model;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jboss.logging.Logger;
import org.springframework.util.StringUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author William Alan Ritch Jan 21, 2014
 */
public abstract class UniqueBase implements
        Unique, XmlConverter {

    private final String XML_LIST_NAME;
    private final String XML_NAME;

    // names of the XML fields we generate
    private final static String ID_XML_NAME = "id";

    // XPath for parsing
    protected final static XPath xpath = XPathFactory.newInstance().newXPath();


    public UniqueBase(long p_id, String xml_list_name, String xml_name) {
        XML_LIST_NAME = xml_list_name;
        XML_NAME = xml_name;
        setId(p_id);
    } // UniqueBase()

    private long id;                    // Code ID

    /* (non-Javadoc)
     * @see org.llyfrgell.model.Unique#getId()
     */
    public final long getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.Unique#toXml(org.w3c.dom.Document)
     */
    public Element toXml(Document doc) {
        Element el = null;

        try {
            Element elId;
            el = doc.createElement(getXmlName());

            // add the ID
            elId = doc.createElement(ID_XML_NAME);
            elId.appendChild(doc.createTextNode(getIdString()));
            el.appendChild(elId);

        } catch (DOMException ex) {
            Logger l = Logger.getLogger(this.getClass().getName());
            l.error("Could not create Code XML element.", ex);
        }

        return el;
    } // toXml()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.Unique#fromXml(org.w3c.dom.Element)
     */
    public boolean fromXml(Element el) {
        if (null == el) {
            return false;
        }

        // is the ID string there
        try {
            String strId = xpath.evaluate("id/text()", el);

            if (!StringUtils.hasText(strId)) {
                return false;
            }

            long id = Long.parseLong(strId);
            setId(id);
        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    } // fromXml()

    public String getXmlListName()
    {
        return XML_LIST_NAME;
    } // getXmlListName()

//    protected void setXmlListName(String strXmlListName) {
//        XML_LIST_NAME = strXmlListName;
//    } // setXmlListName()

    /**
     * Get the XML name of this code.
     *
     * @return XML name of code.
     */
    public String getXmlName()
    {
        return XML_NAME;
    } // getXmlName()

//    protected void setXmlName(String strXmlName) {
//        XML_NAME = strXmlName;
//    } // setXmlName


    /**
     * Setter
     *
     * @param n_id
     */
    public final void setId(long n_id)
    {
        this.id = n_id;
    }

    /**
     * Return the string representation of the ID value.
     * A "0" is returned if the ID value is \c null.
     *
     * @return String representation.
     */
    private String getIdString() {
        Long idValue = getId();
        return Long.toString(idValue);
    } // getIdString()





} // class UniqueBase
