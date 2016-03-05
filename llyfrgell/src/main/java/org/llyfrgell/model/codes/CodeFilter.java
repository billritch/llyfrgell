package org.llyfrgell.model.codes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/***
 * A Code Filter is a mechanism to distinguish one type of Code from another.
 *
 * It is useful in displaying a list of codes, where several of the codes in
 * the list share some common attribute if we want to see only those codes
 * which match a specified attribute.
 *
 * For example: assume we have a list of physical units for a work of art.
 * They can be books, magazines, manuscripts, vinyl records, CDs, DVDs,
 * video cassettes, audio cassettes, laser discs, etc.  We could create a
 * filter that would categorize all the "words-on-medium" units as "literary,"
 * all the sound recordings as "audio" and all the video recordings as
 * "video." Then, in the user interface, we can display (as a list) only the
 * appropriate type of units based on the kind of work we are cataloging.
 *
 * @author William Alan Ritch Jan 10, 2014
 *
 */
public interface CodeFilter
{
    /***
     * Does this code filter match the supplied one?
     *
     * @param target Object that may match the filter.
     * @return \c true if this code filter
     */
    boolean matches(Object target);

    /**
     * The filter as an XML element.
     *
     * @param doc XML document;
     * @return Created element.
     */
    Element toXml(Document doc);

} //interface CodeFilter
