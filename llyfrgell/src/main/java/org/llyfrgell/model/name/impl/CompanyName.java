package org.llyfrgell.model.name.impl;

public class CompanyName
    extends NameBase {

    private static final String XML_NAME = "CompanyName";
    private static final String XML_LIST_NAME = "CompanyName_List";

    public CompanyName() {
        super(XML_LIST_NAME, XML_NAME);
    }

} // class CompanyName
