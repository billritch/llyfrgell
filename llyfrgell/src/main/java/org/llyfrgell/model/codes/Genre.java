package org.llyfrgell.model.codes;

public class Genre
    extends BaseCode
{
    private final static String XML_NAME = "genre";
    private final static String XML_LIST_NAME = "genre_list";

    /**
    *
    */
    public Genre()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Genre