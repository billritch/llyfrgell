/**
 * AffixFieldParser.java Jul 24, 2014
 */
package org.llyfrgell.model.name.impl;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameAffixHandler;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

/**
 * Extract prefixes and suffixes from a simple name.
 *
 * @author William Alan Ritch 2014/07/24.
 */
public class AffixFieldParser extends
        NameFieldParserImpl {

    private NameAffixHandler prefix;
    private NameAffixHandler suffix;

    /**
     *
     */
    public AffixFieldParser() {
        // TODO Auto-generated constructor stub
    }


    public void setPrefix(NameAffixHandler handler) {
        prefix = handler;
    }

    public void setSuffix(NameAffixHandler handler) {
        suffix = handler;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameFieldParser#parse(org.llyfrgell.model.name.OneName, org.llyfrgell.model.name.NameToken)
     */
    public boolean parse(Name p_name, NameToken p_tokens)
            throws NameTokenException {
        boolean bRemoved = false;
        // what we are looking for is a List0.
        // we keep recursing until we find each one of them.

        if (null == p_tokens) {
            throw new NameTokenException(p_tokens);
        }

        // for groups we descend one level
        if (p_tokens.isGroup() && p_tokens.isParent()) {
            if (parse(p_name, p_tokens.getChild(0))) {
                p_tokens.deactivate();
            }

        // if a list we only wish to examine Level 0 lists
        } else if (p_tokens.isList()) {
            if (!p_tokens.isList0()) {
                for(NameToken tok : p_tokens) {
                    if (parse(p_name, tok)) {
                        bRemoved = true;
                        tok.deactivate();
                    } // if
                } // for tok
            } else {

                SimpleName name = (SimpleName) p_name;

                // this is a list0
                if (null != prefix) {
                    for (NameToken tok : p_tokens) {
                        // we want to look at Atoms
                        // and ignore any others
                        if (tok.isAtomic()) {
                            String str = tok.getValue().toString();

                            // if this is a prefix then gather it up.
                            if (!prefix.isValid(str)) {
                                break;
                            }

                            name.addTitle(prefix.normalize(str));
                            tok.deactivate();
                            bRemoved = true;
                        }
                    } // for tok
                }

                // look at suffixes
                if (p_tokens.isParent()) {
                    for (NameToken tok : p_tokens.reverse()) {
                        if (tok.isAtomic()) {
                            String str = tok.getValue().toString();

                            if (name.isGeneration(str)) {
                                try {
                                    if (name.setGeneration(str)) {
                                        tok.deactivate();
                                        bRemoved = true;
                                    }
                                } catch (Exception ex) {
                                    throw new NameTokenException(tok);
                                }
                            } else if (null != suffix) {
                                if (suffix.isValid(str)) {
                                name.addHonorific(suffix.normalize(str));
                                tok.deactivate();
                                bRemoved = true;
                                }
                            } else {
                                break;
                            }
                        } // if Atomic
                    } // for tok
                }
            }
        } // else if is list

        if (bRemoved) {
            p_tokens.deactivate();
        }
        return !(p_tokens.isActive());
    } // parse()

} // class AffixFieldParser
