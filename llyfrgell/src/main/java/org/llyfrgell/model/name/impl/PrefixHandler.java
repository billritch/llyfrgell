/**
 * PrefixHandler.java Jul 25, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.regex.Pattern;

/**
 * Affix handler for standard English-language prefix.
 *
 * Rules:
 *
 * Generically, if the word begins with a capital letter and ends with
 * a period it is considered a prefix.
 *
 * Additionally the follow words are recognized:
 *  Sir
 *  Lady
 *  Lord
 *  Doctor
 *
 *
 * @author William Alan Ritch Jul 25, 2014
 *
 */
public class PrefixHandler extends
        AffixHandlerBase {

    public PrefixHandler() {
        // initialize the prefix map
        addAffix("sir", "Sir");
        addAffix("lady", "Lady");
        addAffix("lord", "Lord");
        addAffix("doctor", "Doctor");
    } // PrefixHandler()


    private Pattern patPrefix = null;

    /**
     * Get the pattern used to match a prefix that is an upper-case letter
     * followed by some characters and terminating in a period.
     *
     * @return Pattern for Prefix.
     */
    @Override
    protected Pattern getAffixPattern() {
        if (null == patPrefix) {
            patPrefix = Pattern.compile("[A-Z].*\\.");
        }

        return patPrefix;
    } // getPrefixPattern()

    } // class PrefixHandler
