package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.codes.Category;


/**
 *
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class CategoryConverter extends EnumListConverter
{
    public Category convert(org.llyfrgell.legacy.generated.Category p_legacy) {
        Category category = (Category)
                convert_internal(p_legacy, new Category());
        convert_filter(p_legacy.getType(), category);
        return category;
    } // convert()
} // CategoryConverter
