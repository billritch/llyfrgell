/**
 * SurnameFirstFieldParser.java Jan 27, 2014
 */
package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

/**
 */
public class SurnameFirstFieldParser extends
    NameFieldParserImpl {
//    private final Logger _l = Logger.getLogger(this.getClass().getName());

    /**
     *
     */
    public SurnameFirstFieldParser() {
    }

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameFieldParser#parse(org.llyfrgell.model.name.Name, org.llyfrgell.model.name.NameToken)
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

        SimpleName oName = (SimpleName) p_name;

        // if this is a more than a level 1 list each child is part of a name
        // otherwise it is just one name
        if (p_tokens.getLevel() > 1) {
            // extract the name parts of the list
            boolean bUsedSurname = false;
            boolean bUsedGivenName = false;

            for (NameToken tok : p_tokens) {
                if (null != tok) {
                    StringBuilder strName = new StringBuilder();
                    // ignore groups
                    if (!tok.isGroup()) {
                        buildName(strName, tok);
                        if (0 != strName.length()) {
                            if (!bUsedSurname) {
                                oName.setSurname(strName);
                                bUsedSurname = true;
                            } else if (!bUsedGivenName) {
                                oName.setGivenName(strName);
                                bUsedGivenName = true;
                            }
                            tok.deactivate();
                        }
                    }
                }
            } // for

            if (bUsedGivenName || bUsedSurname) {
                p_tokens.deactivate();
            }

        } else {
            // get just the given name
            StringBuilder strName = new StringBuilder();
            buildName(strName , p_tokens);
            if (0 != strName.length()) {
                oName.setSurname(strName);
                p_tokens.deactivate();
            }
        }

        return !(p_tokens.isActive());
    } // parse

} // class SurnameFirstFieldParser()
