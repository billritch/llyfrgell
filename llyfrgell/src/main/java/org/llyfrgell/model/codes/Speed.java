package org.llyfrgell.model.codes;


/**
 * Speed.
 *
 * @author William Alan Ritch Jun 6, 2008
 *
 */
public class Speed
    extends BaseCode
{
    private final static String XML_NAME = "speed";
    private final static String XML_LIST_NAME = "speed_list";

    /**
    *
    */
    public Speed()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Speed