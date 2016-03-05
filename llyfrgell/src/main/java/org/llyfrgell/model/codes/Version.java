package org.llyfrgell.model.codes;

/**
 * Version.
 * @author William Alan Ritch Jun 6, 2008
 *
 */
public class Version
	extends BaseCode
{
    private final static String XML_NAME = "version";
    private final static String XML_LIST_NAME = "version_list";

    /**
     *
     */
    public Version()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Version