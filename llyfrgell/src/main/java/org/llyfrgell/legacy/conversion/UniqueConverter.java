/**
 * UniqueConverter.java Jan 21, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.legacy.EnumList;
import org.llyfrgell.model.Unique;
import org.llyfrgell.model.UniqueBase;

/**
 *
 * @author William Alan Ritch Jan 21, 2014
 */
public class UniqueConverter {

    /**
     *
     */
    public UniqueConverter() {
    }
    /**
     * Convert an EnumList object into a BaseCode object.
     *
     * @param p_legacy
     *            Legacy object
     * @param p_item
     *            Converted object
     * @return Converted object.
     */
    protected final Unique convert_internal(
            EnumList p_legacy, UniqueBase p_item) {
        p_item.setId(p_legacy.getId());
        return p_item;
    } // convert()

} // UniqueConverter()
