/**
 *
 */
package org.llyfrgell.model.name;

import org.llyfrgell.model.name.NameToken.NameTokenException;


/**
 * Parse a list of tokens (created by a {@link NameTokenizer})
 * into a single name (@link OneName).
 *
 * Human names have lots of peculiarities and non-standard formats.
 *
 * @author William Alan Ritch 2008/02/17.
 * @revised William Alan Ritch 2008/02/23.
 * Renamed to "NameParser"
 * @revised William Alan Ritch 2008/02/25.
 * Renamed many methods.  Improved documentation.
 * @revised William Alan Ritch 2013/10/30.
 * Renamed to "NameParser."
 * @revised William Alan Ritch 2014/07/21.
 *  -   Changed signature to return {@link OneName}.
 */
public interface NameParser
{
    /**
     * Parse a series of name tokens into a {@link OneName} object.
     *
     * @param tok Token where to start parsing.
     * @return an object with the name fields filled in.
     * @exception NameTokenException parsing error.
     */
    Name parse(NameToken tok) throws NameTokenException;

    void addFieldParser(NameFieldParser p_parser);

} // interface NameParser
