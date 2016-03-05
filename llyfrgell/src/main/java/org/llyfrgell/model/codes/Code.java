package org.llyfrgell.model.codes;

import org.llyfrgell.model.Unique;

/***
 * A single element stored in a code table.
 *
 * @author William Alan Ritch Nov 6, 2013
 *
 */
public interface Code extends Unique
{

    /**
     * Return the enumerated name text.
     *
     * @return Returns the name of this enumeration.
     */
    String getName();

    /**
     * Long description of this code.
     *
     * @return
     */
    String getDescription();

} // interface Code
