/**
 * SimpleCodeFilter.java Jan 21, 2014
 */
package org.llyfrgell.model.codes;

import org.jboss.logging.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A SimpleCodeFilter compares one object to another. These objects match if
 * they are equal (using the object's "equals()" method)
 *
 * @author William Alan Ritch Jan 21, 2014
 */
public abstract class SimpleCodeFilter implements
        CodeFilter {

    // names of the XML fields we generate
    private final static String FILTER_XML_NAME = "filter";

    /**
     *
     */
    public SimpleCodeFilter() {
    }

    /**
     * Get the Filter Value part of this node to match it against the
     * the target value.
     *
     * @return The filter value object.
     */
    protected abstract Object getFilterValue();

    /*
     * (non-Javadoc)
     *
     * @see org.llyfrgell.model.codes.CodeFilter#matches(java.lang.Object)
     */
    public boolean matches(Object target) {
        Object oValue = getFilterValue();

        // if the value and the target are both null - they match
        if (null == oValue) {
            return (null == target);
        }

        // if the target is not null at this point there can be not match
        if (null == target) {
            return false;
        }

        // TODO does equals work safely if the objects don't have any
        // common ancestor types?
        return (target.equals(oValue));
    } // matches()


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Object o = getFilterValue();
        if (null == o) {
            return "-null-";
        }
        return o.toString();
    } // toString()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.codes.CodeFilter#toXml(org.w3c.dom.Document)
     */
    public Element toXml(Document doc) {
        Element elCode = null;

        Object oValue = getFilterValue();
        if (null != oValue) {
            String strValue = oValue.toString();
            if (null != strValue) {
                try {
                elCode = doc.createElement(FILTER_XML_NAME);
                elCode.appendChild(doc.createTextNode(strValue));
                } catch (DOMException ex) {
                    Logger l = Logger.getLogger(this.getClass().getName());
                    l.error("Could not create Code XML element.", ex);
                }
            } // (null != strValue)
        }  // (null != oValue)

        return elCode;
    } // toXml()

} // class SimpleCodeFilter
