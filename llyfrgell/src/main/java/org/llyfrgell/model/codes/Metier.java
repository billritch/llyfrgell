/**
 * Metier.java Jan 17, 2014
 */
package org.llyfrgell.model.codes;

/**
 * @author William Alan Ritch Jan 17, 2014
 *
 */
public class Metier extends
        BaseCode {
    private final static String XML_NAME = "metier";
    private final static String XML_LIST_NAME = "metier_list";

    /**
     *
     */
    public Metier() {
        super(XML_LIST_NAME, XML_NAME);
    }
} // class Metier
