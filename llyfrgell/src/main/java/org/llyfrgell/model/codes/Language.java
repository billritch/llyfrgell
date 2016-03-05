package org.llyfrgell.model.codes;


public class Language
	extends BaseCode
{
    private final static String XML_NAME = "language";
    private final static String XML_LIST_NAME = "language_list";

    /**
    *
    */
    public Language()
    {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Language