/**
 * NameFieldParser.java Jan 23, 2014
 */
package org.llyfrgell.model.name;

import org.llyfrgell.model.name.NameToken.NameTokenException;


/**
 * Parse the fields of a name and choose which fields to
 * store into an Name object.
 *
 * The {@link NameParser} implementation will call one or
 * more of  these objects to parse the fields of a single
 * name.
 *
 * The idea is that a series of these name field parsers are
 * let loose on a list of name fields (tokens).  Each parser is looking
 * for certain fields and as it consumes these fields it deactivates
 * them from the list.  Thus some field parsers might be looking
 * for what is a first name - some might be looking for a generation
 * suffix (such as Jr. or III) - and some might be looking for
 * title prefixes (such as Doctor or Professor).
 *
 * @author William Alan Ritch 2009/04/22.
 */
public interface NameFieldParser
{
    /**
     * Parse the list of name tokens as described above.
     * Each name token which is consumed is deactivated in the list.
     *
     * @param p_name Name object to be filled.
     * @param p_tokens Root of a tree of name tokens.
     * @return \c true if this token root has been deactivated because
     * of this parse.
     * @exception NameTokenException
     */
    boolean parse(Name p_name, NameToken p_tokens)
            throws NameTokenException;
} // interface NameFieldParser
