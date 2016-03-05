/**
 * RecordingSpeedConverter.java Jan 17, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Speed;

/**
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class RecordingSpeedConverter extends EnumListConverter
{
    public Speed convert(org.llyfrgell.legacy.generated.RecordingSpeed p_legacy) {
        return (Speed) convert_internal(p_legacy, new Speed());
    } // convert()
} // class RecordingSpeedConverter
