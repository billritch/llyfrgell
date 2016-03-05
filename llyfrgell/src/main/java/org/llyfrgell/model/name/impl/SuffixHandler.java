/**
 * SuffixHandler.java Jul 25, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.regex.Pattern;

/**
 * A suffix looks like
 *  - A set of letters all capitalized
 *  - A set of letters separated by dots.
 *  - A set of letters with dots within them.
 *  - Some words that we expect
 * @author William Alan Ritch Jul 25, 2014
 *
 */
public class SuffixHandler extends
        AffixHandlerBase {

    public SuffixHandler() {
    } // SuffixHandler()


    private Pattern pat = null;

    /**
     * Get the pattern used to match a prefix that is an upper-case letter
     * followed by some characters and terminating in a period.
     *
     * @return Pattern for Prefix.
     */
    @Override
    protected Pattern getAffixPattern() {
        if (null == pat) {
            pat = Pattern.compile("[A-Z]{2,}");
        }

        return pat;
    } // getPrefixPattern()


} // class SuffixHandler
