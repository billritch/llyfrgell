/**
 * DutyConverter.java Jan 17, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Metier;

/**
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class DutyConverter extends EnumListConverter
{
    public Metier convert(org.llyfrgell.legacy.generated.Duty p_legacy) {
        return (Metier) convert_internal(p_legacy, new Metier());
    } // convert()
} // DutyConverter
