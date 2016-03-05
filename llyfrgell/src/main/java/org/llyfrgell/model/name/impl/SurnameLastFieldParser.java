/**
 * SurnameFirstFieldParser.java Jan 27, 2014
 */
package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

/**
 */
public class SurnameLastFieldParser extends
    NameFieldParserImpl {
//    private final Logger _l = Logger.getLogger(this.getClass().getName());

    /**
     *
     */
    public SurnameLastFieldParser() {
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

        SimpleName name = (SimpleName) p_name;

        boolean bUsedSurname = false;
        boolean bUsedGivenName = false;

        // get down to the first level 0
        if (0 == p_tokens.getLevel()) {
            StringBuilder strName = new StringBuilder();

            // parse the surname first
            int childCount = p_tokens.countChildren();
            buildName(strName, p_tokens.getChild(childCount-1));
            if (0 != strName.length()) {
                name.setSurname(strName);
                bUsedSurname = true;
            }

            strName = new StringBuilder();
            buildName(strName, p_tokens);
            if (0 != strName.length()) {
                name.setGivenName(strName);
                bUsedGivenName = true;
            }
        }


        if (bUsedGivenName || bUsedSurname) {
            p_tokens.deactivate();
        }
        return !(p_tokens.isActive());
    } // parse

} // class SurnameFirstFieldParser()
