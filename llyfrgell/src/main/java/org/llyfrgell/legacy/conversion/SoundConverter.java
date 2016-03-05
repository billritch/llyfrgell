package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Sound;

/**
 *
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class SoundConverter extends EnumListConverter
{
    public Sound convert(org.llyfrgell.legacy.generated.Sound p_legacy) {
        return (Sound) convert_internal(p_legacy, new Sound());
    } // convert()

} // class SoundConverter
