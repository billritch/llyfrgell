package org.llyfrgell.model.name;

import java.util.List;

/**
 * Name of a person.
 *
 * Since a person might have multiple names (read "aliases") this can return
 * a single name or an array of them. A single name is of type "OneName".
 *
 * Multiple names are ordered. The primary name is returned by the
 * getPrimaryName() call.
 *
 * @author William Alan Ritch Oct 30, 2013
 */
public interface NameAliases extends OneName
{
    /**
     * Is this a single name or a multiple name? If it is a single name
     * then the "getPrimaryName()" should be called to get the OneName
     * object.
     *
     * @return \c true if a single name.
     */
    boolean isSingle();

    OneName getPrimary();

    List<OneName> getList();


} // interface Name
