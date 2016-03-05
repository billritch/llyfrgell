package org.llyfrgell.legacy.conversion;

import org.llyfrgell.legacy.EnumList;
import org.llyfrgell.model.codes.ArtCodeFilter;
import org.llyfrgell.model.codes.BaseCode;
import org.llyfrgell.model.codes.FilteredCode;

/**
 * Convert a legacy enumerated item into a modern "Code" Item.
 *
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class EnumListConverter  extends UniqueConverter {
    protected static final String Filter_Video = "v";
    protected static final String Filter_Audio = "a";
    protected static final String Filter_Literary = "l";
    protected static final String Filter_Book = "b";

    protected EnumListConverter() {
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
    protected final BaseCode convert_internal(
            EnumList p_legacy, BaseCode p_item) {
        super.convert_internal(p_legacy, p_item);
        p_item.setName(p_legacy.getText());
        p_item.setDescription(p_legacy.getText());
        return p_item;
    } // convert()

    protected final BaseCode convert_filter(
            String str_type, FilteredCode p_item) {
        if (null != str_type) {

            if (Filter_Video.equalsIgnoreCase(str_type)) {
                p_item.setCodeFilter(ArtCodeFilter.Video);
            } else if (Filter_Audio.equalsIgnoreCase(str_type)) {
                p_item.setCodeFilter(ArtCodeFilter.Audio);
            } else if (Filter_Literary.equalsIgnoreCase(str_type)) {
                p_item.setCodeFilter(ArtCodeFilter.Literary);
            }

        }
        return p_item;
    } // convert_filter()

} // class EnumListConverter
