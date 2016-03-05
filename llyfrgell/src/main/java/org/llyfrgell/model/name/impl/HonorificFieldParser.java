package org.llyfrgell.model.name.impl;

import java.util.Iterator;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

/***
 *
 * @author William Alan Ritch Jul 22, 2014
 */
public class HonorificFieldParser extends
        NameFieldParserImpl {

    public boolean parse(Name p_name, NameToken p_tokens)
            throws NameTokenException {

        // we are looking for a group
        if (null == p_tokens) {
            return false;
        }

        // ignore deactivated tokens
        if (!p_tokens.isActive()) {
            return false;
        }

        boolean bReturn = false;

        // atomic? this might be an honorific
        if (p_tokens.isAtomic()) {
            // gather honorific
            CharSequence honor = p_tokens.getValue();
            if ((null != honor) && (0 != honor.length())) {
                ((SimpleName) p_name).addHonorific(honor);
                bReturn = true;
            }

        } else if (p_tokens.isList()) {
            // more than one honorific
            Iterator<NameToken> it = p_tokens.iterator();
            if (null != it) {

                while (it.hasNext()) {
                    NameTokenImpl tokChild = (NameTokenImpl) it.next();
                    if (null != tokChild) {
                        boolean bParsed = parse(p_name, tokChild);
                        if (bParsed) {
                            tokChild.deactivate();
                        }
                        bReturn |= bParsed;
                    }
                } // while (it)
            }

        } else if (p_tokens.isGroup()) {
            // honorifics embedded here
            if (p_tokens.isParent()) {
                bReturn = parse(p_name, p_tokens.getChild(0));
                if (bReturn) {
                    p_tokens.deactivate();
                }
                return bReturn;
            }
        } // isGroup

        return bReturn;
    } // parse()

} // class HonorificFieldParser
