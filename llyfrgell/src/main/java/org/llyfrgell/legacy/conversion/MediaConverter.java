package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Medium;

public class MediaConverter extends EnumListConverter
{
    public Medium convert(org.llyfrgell.legacy.generated.Media p_legacy) {
        Medium medium = (Medium)
                convert_internal(p_legacy, new Medium());

        String strType = p_legacy.getMediumCategory();
        if (null != strType) {
            if (strType.length() > 0) {
                strType = strType.substring(0, 1);
            } else {
                strType = null;
            }
        }

        convert_filter(strType, medium);
        return medium;
    } // convert()

} // class MediaConverter
