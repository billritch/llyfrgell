/**
 * CreatorImpl.java Feb 21, 2016
 */
package org.llyfrgell.model.creator.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.llyfrgell.model.UniqueBase;
import org.llyfrgell.model.XmlConverter;
import org.llyfrgell.model.codes.Metier;
import org.llyfrgell.model.creator.Creator;
import org.llyfrgell.model.name.Name;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author William Alan Ritch Feb 21, 2016
 *
 */
public class CreatorImpl
    extends UniqueBase
    implements Creator,
    XmlConverter {

    private final static String XML_NAME = "creator";
    private final static String XML_LIST_NAME = "creator_list";
    private final static String XML_NAME_LIST = "names";
    private final static String XML_METIER_LIST = "metiers";

    private List<Name> listNames;
    private List<Metier> listMetiers;

    /**
     *
     */
    public CreatorImpl() {
        super(0, XML_NAME, XML_LIST_NAME);
        listNames = new ArrayList<Name>();
        listMetiers = new ArrayList<Metier>();
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.creator.Creator#getOfficialName()
     */
    public Name getOfficialName() {
        return listNames.isEmpty() ? null : listNames.get(0);
    } // getOfficialName()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.creator.Creator#getNames()
     */
    public List<Name> getNames() {
        return listNames;
    } // getNames()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.creator.Creator#getPrimaryMetier()
     */
    public Metier getPrimaryMetier() {
        return listMetiers.isEmpty() ? null : listMetiers.get(0);
    } // getPrimaryMetier()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.creator.Creator#getMetiers()
     */
    public List<Metier> getMetiers() {
        return listMetiers;
    } // getMetiers()

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return buildString(null).toString();
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
        builder.append(" official name='");
        builder.append(getOfficialName());
        builder.append("'");

        // Description
        builder.append(" metier='");
        builder.append(getPrimaryMetier());
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
        List<Name> listNames;
        List<Metier> listMetiers;

        try {
            el = super.toXml(doc);

            Element elField;

            // build all the names

            // build all the metiers

            // add the Name - if non-null
            listNames = getNames();
            if (null != listNames) {
                elField = doc.createElement(XML_NAME_LIST);
                for(Name oneName : listNames) {
                    elField.appendChild(oneName.toXml(doc));
                }
                el.appendChild(elField);
            }

            // add the Description - if non-null
            listMetiers = getMetiers();
            if (null != listMetiers) {
                elField = doc.createElement(XML_METIER_LIST);
                for(Metier oneMetier : listMetiers) {
                    elField.appendChild(oneMetier.toXml(doc));
                }
                el.appendChild(elField);
            }
        } catch (DOMException ex) {
            Logger l = Logger.getLogger(this.getClass().getName());
            l.error("Could not create Code XML element.", ex);
            throw ex;
        }

        return el;
    } // toXML()

} // class CreatorImpl
