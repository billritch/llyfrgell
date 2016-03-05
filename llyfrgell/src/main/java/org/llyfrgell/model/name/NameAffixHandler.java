/**
 * NameAffixHandler.java Jul 25, 2014
 */
package org.llyfrgell.model.name;

/**
 * Judges the validity of name prefix or suffix. These are also called
 * "pre-nominal" and "post-nominal" letters.
 *
 * Primarily the affix handler uses {@link #isValid(CharSequence)} to determine
 * if a candidate is one of the prefixes or suffixes that we can handle.
 * The rules for determining this are inside the implementing code.
 * It might be a collection of strings that must match, or some regular
 * expression that should be matched.
 *
 * Also a  function is supplied that maps
 * Also a {@link #normalize(CharSequence)} function is provided, which converts
 * the user-entered affix to its standard format.
 *
 * @author William Alan Ritch 2014/07/25.
 */
public interface NameAffixHandler {

    boolean isValid(CharSequence str_affix);

    CharSequence normalize(CharSequence str_affix);

} // interface NameAffixHandler
