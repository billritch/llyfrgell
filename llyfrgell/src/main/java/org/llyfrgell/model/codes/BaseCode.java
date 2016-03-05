/**
 * BaseCode.java Nov 6, 2013
 */
package org.llyfrgell.model.codes;

import javax.xml.xpath.XPathExpressionException;

import org.jboss.logging.Logger;
import org.llyfrgell.model.UniqueBase;
import org.springframework.util.StringUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author William Alan Ritch Nov 6, 2013
 *
 */
public abstract class BaseCode
    extends UniqueBase
    implements Code
{
    // names of the XML fields we generate
    private final static String NAME_XML_NAME = "name";
    private final static String DESCRIPTION_XML_NAME = "description";

    private String strName;             // Code name
    private String strDescription;      // Code description

    public BaseCode(String xml_list_name, String xml_name) {
        super(0, xml_list_name, xml_name);
    } // BaseCode()

    public String getName()
    {
        return strName;
    }

    public void setName(String str_name)
    {
        strName = str_name;
    }

    public String getDescription()
    {
        return strDescription;
    }

    public void setDescription(String str_description)
    {
        strDescription = str_description;
    }



    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return buildString(new StringBuilder()).toString();
    }

    protected StringBuilder buildString(StringBuilder builder) {
        if (null == builder) {
            builder = new StringBuilder();
        }

        builder.append(this.getClass().getSimpleName());

        // ID
        builder.append(" id=");
        builder.append(getId());

        // Name
        builder.append(" name='");
        builder.append(getName());
        builder.append("'");

        // Description
        builder.append(" description='");
        builder.append(getDescription());
        builder.append("'");

        return builder;
    } // buildString()


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.codes.Code#toXml(org.w3c.dom.Document)
     */
    @Override
    public Element toXml(Document doc)
    {
        Element el = null;
        String strValue;

        try {
            el = super.toXml(doc);

            Element elField;

            // add the Name - if non-null
            strValue = getName();
            if (null != strValue) {
                elField = doc.createElement(NAME_XML_NAME);
                elField.appendChild(doc.createTextNode(strValue));
                el.appendChild(elField);
            }

            // add the Description - if non-null
            strValue = getDescription();
            if (null != strValue) {
                elField = doc.createElement(DESCRIPTION_XML_NAME);
                elField.appendChild(doc.createTextNode(strValue));
                el.appendChild(elField);
            }
        } catch (DOMException ex) {
            Logger l = Logger.getLogger(this.getClass().getName());
            l.error("Could not create Code XML element.", ex);
            throw ex;
        }

        return el;
    } // toXML()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.UniqueBase#fromXml(org.w3c.dom.Element)
     */
    @Override
    public boolean fromXml(Element el) {
        if (!super.fromXml(el)) {
            return false;
        }

        // is the ID string there
        try {
            // get name
            String strName = xpath.evaluate(NAME_XML_NAME + "/text()", el);
            if (!StringUtils.hasText(strName)) {
                return false;
            }
            setName(strName);

            // get description
            String strDescription = xpath.evaluate(DESCRIPTION_XML_NAME + "/text()", el);
            if (!StringUtils.hasText(strDescription)) {
                return false;
            }
            setDescription(strDescription);

        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    } // fromXml()
} // class BaseCode
