/**
 * DutyConverter.java Jan 17, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Genre;


/**
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class GenreConverter extends EnumListConverter
{
    public Genre convert(org.llyfrgell.legacy.generated.Genre p_legacy) {
        return (Genre) convert_internal(p_legacy, new Genre());
    } // convert()
} // DutyConverter
