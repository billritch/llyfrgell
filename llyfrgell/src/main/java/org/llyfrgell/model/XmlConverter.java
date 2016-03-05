package org.llyfrgell.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/***
 * Convert an object to and from XML.
 * @author William Alan Ritch Feb 21, 2016
 *
 */
public interface XmlConverter {

    /***
     * Comvert an object into and XML element.
     *
     * @param doc An XML document which will store the XML element.
     * @return The create element.
     */
    Element toXml(Document doc);

    /***
     * Convert the XML element into the current object.
     *
     * @param el XML element.
     * @return true if the conversion was OK.
     */
    boolean fromXml(Element el);

    public String getXmlListName();

    public String getXmlName();


} // interface XmlConverter
