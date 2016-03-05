/**
 * ColourConverter.java Nov 19, 2013
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Color;


/**
 * @author William Alan Ritch Nov 19, 2013
 *
 */
public class ColourConverter extends EnumListConverter
{
    public Color convert(org.llyfrgell.legacy.generated.Colour p_legacy) {
        return (Color) convert_internal(p_legacy, new Color());
    } // convert()
} // ColourConverter
