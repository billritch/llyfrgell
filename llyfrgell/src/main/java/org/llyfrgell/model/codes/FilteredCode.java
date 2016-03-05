package org.llyfrgell.model.codes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A code that supports filtering.
 *
 *
 * @author William Alan Ritch 2008/06/06
 * @revised William Alan Ritch 2014/01/21.
 *
 */
public abstract class FilteredCode extends
        BaseCode {
    private CodeFilter filter;

    public FilteredCode(String xml_list_name, String xml_name) {
        super(xml_list_name, xml_name);
    } // BaseCode()

    /**
     * Store the code filter value.
     *
     * @param p_filter
     *            Code filter value.
     */
    public final void setCodeFilter(CodeFilter p_filter) {
        filter = p_filter;
    } // setCodeFilter()

    /***
     * Does this code filter match the supplied one?
     *
     * @param o_value
     *            Code filter value to check match.
     * @return \c true if the code filters match.
     */
    public boolean matches(Object o_value) {
        if (null == filter) {
            return false;
        }

        return filter.matches(o_value);
    } // matches

    /*
     * (non-Javadoc)
     *
     * @see
     * org.llyfrgell.model.codes.BaseCode#buildString(java.lang.StringBuilder)
     */
    @Override
    protected StringBuilder buildString(StringBuilder builder) {
        builder = super.buildString(builder);

        // Description
        builder.append(" filter=");
        if (null == filter) {
            builder.append("-null-");
        } else {
            builder.append("'");
            builder.append(filter.toString());
            builder.append("'");
        }

        return builder;
    } // buildString()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.codes.BaseCode#toXml(org.w3c.dom.Document)
     */
    @Override
    public Element toXml(Document doc) {
        Element el = super.toXml(doc);
        if (null != el) {
            if (null != filter) {
                Element elCode = filter.toXml(doc);
                if (null != elCode) {
                    el.appendChild(elCode);
                }
            } // (null != filter)
        } // (null != el)

        return el;
    } // toXml()

} // interface FilteredCode
