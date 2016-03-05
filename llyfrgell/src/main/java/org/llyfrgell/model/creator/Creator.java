package org.llyfrgell.model.creator;

import java.util.List;

import org.llyfrgell.model.XmlConverter;
import org.llyfrgell.model.codes.Metier;
import org.llyfrgell.model.name.Name;

/***
 * A Creator is an entity that creates a work of art. It may be a single person,
 * a team, or a company.
 *
 * A creator has a single primary name (its Official Name) and it may have
 * other names that have been used.
 *
 * @author William Alan Ritch Feb 21, 2016
 *
 */
public interface Creator extends XmlConverter {

    /***
     * Get the official name of the creator.
     * This is the first element in the list of names.
     *
     * @return The official name.
     */
    Name getOfficialName();

    /***
     * All of the names used by this creator.
     *
     * @return Names.
     */
    List<Name> getNames();

    /***
     * Get the primary Metier of this created.
     * This is the first metier in the list of metiers.
     *
     * @return The primary metier.
     */
    Metier getPrimaryMetier();

    /***
     * Get the list of metiers for this creator.
     *
     * @return Metiers.
     */
    List<Metier> getMetiers();

} // interface Creator
