/**
 * Aspect.java Jan 16, 2014
 */
package org.llyfrgell.model.codes;

/**
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class Aspect
    extends BaseCode
{
    private final static String XML_NAME = "aspect";
    private final static String XML_LIST_NAME = "aspect_list";

    /**
     *
     */
    public Aspect()
    {
        super(XML_LIST_NAME, XML_NAME);
    }

} // class Aspect()
