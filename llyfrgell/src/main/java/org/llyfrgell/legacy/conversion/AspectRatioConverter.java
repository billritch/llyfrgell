package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Aspect;

/**
 *
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class AspectRatioConverter extends EnumListConverter
{
    public Aspect convert(org.llyfrgell.legacy.generated.AspectRatio p_legacy) {
        return (Aspect) convert_internal(p_legacy, new Aspect());
    } // convert()

} // AspectRatioConverter()
