package org.llyfrgell.model.name;

import org.llyfrgell.model.name.NameToken.NameTokenException;


/**
 * Parse a name or a list of names into a series of "Name Tokens" which
 * can be more easily processed by various name parsers.
 *
 * Human names have lots of peculiarities and non-standard formats.
 * This allows the user to parse a name
 * and get the individual pieces of it so that it might be parsed
 * into a series of names.
 *
 * @author William Alan Ritch 2014/02/02.
 * @revised William Alan Ritch 2014/07/18.
 *  -   Changed name from "ParsedName".
 * @revised William Alan Ritch 2014/07/21.
 *  -   Merged with "NameParser"
 */
public interface NameTokenizer {

    /***
     * Parse the string into a series of Name Tokens.
     *
     * @return The root of the Name Token tree.
     * @exception NameTokenException error by tokenizing the string.
     */
    NameToken parse(CharSequence str) throws NameTokenException;

    /***
     * Is the Name Token tree empty?
     *
     * @return \c true if the NameTokenizer has no contents.
     */
    boolean isEmpty();

    /**
     * Get the first name token of the parsed string.
     *
     * @return First (root) name token.
     */
    NameToken getFirstToken();

} // interface NameTokenizer
