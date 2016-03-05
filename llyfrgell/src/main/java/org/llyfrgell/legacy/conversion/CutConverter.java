package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Version;

/**
*
* @author William Alan Ritch Jan 16, 2014
*
*/
public class CutConverter extends EnumListConverter
{
    public Version convert(org.llyfrgell.legacy.generated.Cut p_legacy) {
        return (Version) convert_internal(p_legacy, new Version());
    } // convert()
} // CutConverter()
