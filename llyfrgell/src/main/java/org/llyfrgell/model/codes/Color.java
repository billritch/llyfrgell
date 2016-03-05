/**
 * Color.java Jan 10, 2014
 */
package org.llyfrgell.model.codes;

/**
 * Codes of what color property exists for a video.
 *
 * @author William Alan Ritch Jan 10, 2014
 *
 */
public class Color extends
        BaseCode
{
    private final static String XML_NAME = "color";
    private final static String XML_LIST_NAME = "color_list";

    /**
     *
     */
    public Color()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Color
