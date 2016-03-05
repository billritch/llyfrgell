package org.llyfrgell.model.name;

import org.llyfrgell.model.Unique;
import org.llyfrgell.model.XmlConverter;

public interface Name
    extends Unique,
        XmlConverter {

    /***
     * Property: FullName
     *
     * The full name with all the pieces attached.  Usually has
     * the surname, the given names, any generation designations, and
     * titles and honorifics.
     *
     * The full name may be created on the
     * fly from components stored in the name implementation object.
     *
     * @return Full name.
     */
    CharSequence getFullName();

    /***
     * Property: SortName
     *
     * A Sort name is a name used for generating hashes and determining
     * if this name is the same as another name. It is often case free
     * with all the spaces and punctuation removed.
     *
     * A sort name may be created on-the-fly if one is not already
     * stored.
     *
     * @return Sort name.
     */
    CharSequence getSortName();

    /***
     * Property: Primary.
     *
     * Is this the primary name?
     *
     * @return Primary flag.
     */
    boolean isPrimary();

    /***
     * Property: Short.
     *
     * Is this a short name.
     *
     * @return Short flag.
     */
    boolean isShort();

} // interface Name
