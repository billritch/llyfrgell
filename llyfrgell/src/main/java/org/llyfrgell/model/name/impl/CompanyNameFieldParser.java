package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

public class CompanyNameFieldParser
    extends NameFieldParserImpl {

    public CompanyNameFieldParser() {
    }

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameFieldParser#parse(org.llyfrgell.model.name.OneName, org.llyfrgell.model.name.NameToken)
     */
    public boolean parse(Name p_name, NameToken p_tokens)
        throws NameTokenException {
        // this should be a list at least one child
        // first validate that this should be a level N list
        if (null == p_tokens) {
            throw new NameTokenException(p_tokens);
        }
        if (!p_tokens.isList()) {
            throw new NameTokenException(p_tokens);
        }
        if (p_tokens.getLevel() > 2) {
            throw new NameTokenException(p_tokens);
        }
        if (p_tokens.countChildren() <= 0) {
            throw new NameTokenException(p_tokens);
        }

        CompanyName name = (CompanyName) p_name;

        // get down to the first level 0
        if (0 == p_tokens.getLevel()) {
            StringBuilder strName = new StringBuilder();

            // parse the surname first
            int childCount = p_tokens.countChildren();
            buildName(strName, p_tokens.getChild(childCount-1));
            if (0 != strName.length()) {
                name.setFullName(strName);
            }
        }

        p_tokens.deactivate();
        return !(p_tokens.isActive());
    } // parse


}
