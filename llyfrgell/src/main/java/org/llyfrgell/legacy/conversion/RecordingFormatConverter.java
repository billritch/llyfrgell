/**
 * RecordingFormatConverter.java Jan 16, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Format;

/**
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class RecordingFormatConverter extends EnumListConverter
{
    public Format convert(org.llyfrgell.legacy.generated.RecordingFormat p_legacy) {
        return (Format) convert_internal(p_legacy, new Format());
    } // convert()

} // class RecordingFormatConverter()
