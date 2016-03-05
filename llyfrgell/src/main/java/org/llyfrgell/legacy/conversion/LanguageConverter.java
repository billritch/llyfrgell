/**
 * LanguageConverter.java Jan 17, 2014
 */
package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Language;

/**
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class LanguageConverter extends EnumListConverter
{
    public Language convert(org.llyfrgell.legacy.generated.Language p_legacy) {
        return (Language) convert_internal(p_legacy, new Language());
    } // convert()
} //  class LanguageConverter
