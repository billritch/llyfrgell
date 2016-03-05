package org.llyfrgell.model.codes;

/**
 * Medium.
 *
 * @author William Alan Ritch Jun 6, 2008
 *
 */
public class Medium
    extends FilteredCode
{
    private final static String XML_NAME = "medium";
    private final static String XML_LIST_NAME = "medium_list";

    /**
    *
    */
    public Medium()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Medium