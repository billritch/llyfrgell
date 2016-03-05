package org.llyfrgell.legacy.conversion;

import org.llyfrgell.model.creator.impl.CreatorImpl;
import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameToken.NameTokenException;
import org.llyfrgell.model.name.NameTokenizer;
import org.llyfrgell.model.name.impl.CompanyNameFieldParser;
import org.llyfrgell.model.name.impl.CompanyNameParser;
import org.llyfrgell.model.name.impl.NameTokenizerImpl;
import org.springframework.util.StringUtils;


public class LabelConverter extends EnumListConverter
{
    private NameTokenizer t;
    private CompanyNameParser p;

    public LabelConverter() {
        t = new NameTokenizerImpl();
        p = new CompanyNameParser();
        p.addFieldParser(new CompanyNameFieldParser());
    } // LabelConverter()

    public CreatorImpl convert(org.llyfrgell.legacy.generated.Label p_legacy) {
        CreatorImpl creator = (CreatorImpl) convert_internal(
                p_legacy, new CreatorImpl());

        // add the name
        String strManufacturerName = p_legacy.getManufacturer().trim();
        if (!StringUtils.isEmpty(strManufacturerName)) {
            try {
                Name name = p.parse(t.parse(strManufacturerName));
                creator.getNames().add(name);
            } catch (NameTokenException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return creator;
    } // convert()

} // class LabelConverter
