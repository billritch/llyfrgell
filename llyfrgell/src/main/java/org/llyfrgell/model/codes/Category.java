/**
 * Category.java Jan 16, 2014
 */
package org.llyfrgell.model.codes;

/**
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class Category extends
        FilteredCode {
    private final static String XML_NAME = "category";
    private final static String XML_LIST_NAME = "category_list";

    /**
     *
     */
    public Category() {
        super(XML_LIST_NAME, XML_NAME);
    }
}
