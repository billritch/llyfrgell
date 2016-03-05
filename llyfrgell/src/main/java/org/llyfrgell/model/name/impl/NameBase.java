package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.UniqueBase;
import org.llyfrgell.model.name.Name;

/***
 * The interface for all kinds of names
 *
 * @author William Alan Ritch Feb 21, 2016
 *
 */
public abstract class NameBase
    extends UniqueBase
    implements Name {

    private final static String XML_NAME = "namebase";
    private final static String XML_LIST_NAME = "namebase_list";

    /**
     * Used to generate XML header.
     */
    protected String xmlBaseName;

    private CharSequence strFullName;
    private CharSequence strSortName;
    private boolean bPrimary;
    private boolean bShort;

    /***
     * Default constructor.
     */
    public NameBase() {
        super(0, XML_NAME, XML_LIST_NAME);
    } // NameBase()

    /**
     * Constructor used to provide the XML base name.
     *
     * @param xml_base_name XML base name.
     */
    protected NameBase(String xml_list_name, String xml_base_name) {
        super(0, xml_list_name, xml_base_name);
    } // NameBase()

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
    public CharSequence getFullName() {
        return strFullName;
    } // getFullName()

    /***
     * Store the full name.
     *
     * @param str_name Name to be stored.
     */
    protected void setFullName(CharSequence str_name) {
        strFullName = str_name;
    } // setFullName()


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
    public CharSequence getSortName() {
        return strSortName;
    } // getSortName()

    /***
     * Store the sort name.
     *
     * @param str_name Name to be stored.
     */
    protected void setSortName(CharSequence str_name) {
        strSortName = str_name;
    } // setSortName()

    /***
     * Property: Primary.
     *
     * Is this the primary name?
     *
     * @return Primary flag.
     */
    public boolean isPrimary() {
        return bPrimary;
    } // isPrimary()

    /***
     * Store the Primary flag.
     *
     * @param b_primary Primary flag to be set.
     */
    protected void setPrimary(boolean b_primary) {
        bPrimary = b_primary;
    } // setPrimary()

    /***
     * Property: Short.
     *
     * Is this a short name.
     *
     * @return Short flag.
     */
    public boolean isShort() {
        return bShort;
    } // isShort()

    /***
     * Store the Short flag.
     *
     * @param b_short Short flag to be set.
     */
    protected void setShort(boolean b_short) {
        bShort = b_short;
    } // setPrimary()

} // class NameBase
