/**
 * AffixHandlerBase.java Jul 29, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.llyfrgell.model.name.NameAffixHandler;

/**
 * @author William Alan Ritch 2014/07/29.
 *
 */
public abstract class AffixHandlerBase  implements
    NameAffixHandler {

    /**
     *
     */
    public AffixHandlerBase() {
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameAffixHandler#isValid(java.lang.CharSequence)
     */
    public boolean isValid(CharSequence str_affix) {
        boolean bMatch = getAffixPattern().matcher(str_affix).matches();
        if (!bMatch) {
            bMatch = (null != lookupAffix(str_affix));
        }

        return bMatch;
    } // isValid()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameAffixHandler#normalize(java.lang.CharSequence)
     */
    public CharSequence normalize(CharSequence str_affix) {
        CharSequence strReturn = lookupAffix(str_affix);
        return (null == strReturn) ? str_affix : strReturn;
    } // normalize()


    // The map of additionally allowed affixes
    private Map<CharSequence, CharSequence> mapAffixes;

    protected abstract Pattern getAffixPattern();

    /**
     * Add a affix definition to the table of affixes.
     *
     * This definition will let you map from a user-defined affix
     * into a canonical one.
     *
     * For example you could map "Doc", "Dr" and "Doktor" into "Dr." by using
     * three additions to the affix table.
     *
     * Note: all the affix keywords are stored and looked-up as lower-case
     * words.
     *
     * @param affix New affix.
     * @param affixCanonical Official, canonical form of the affix.
     */
    protected void addAffix(CharSequence affix, CharSequence affixCanonical) {
        if (null == mapAffixes) {
            mapAffixes = new HashMap<CharSequence, CharSequence>();
        }

        mapAffixes.put(affix.toString().toLowerCase(), affixCanonical);
    } // addAffix()

    /**
     *
     * @param affix
     * @return
     */
    protected CharSequence lookupAffix(CharSequence affix) {
        if (null == mapAffixes) {
            return null;
        }

        return mapAffixes.get(affix.toString().toLowerCase());
    }  // lookupAffix()

} // AffixHandlerBase()
