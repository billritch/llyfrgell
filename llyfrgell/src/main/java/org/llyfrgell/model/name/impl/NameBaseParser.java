package org.llyfrgell.model.name.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.llyfrgell.model.name.Name;
import org.llyfrgell.model.name.NameFieldParser;
import org.llyfrgell.model.name.NameParser;
import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException;

public class NameBaseParser
    implements NameParser {

    //private final Logger _l = Logger.getLogger(this.getClass().getName());

    /**
     * The name we are assembling into. Passed as a parameter to the
     * constructor.
     */
    private Name name;

    /**
    * Field parsers that get to handle the name fields.
    *
    * This is the Gang of 4 pattern "Chain of Responsibility." Each field
    * parser has a crack at parsing the name fields and adding them into the
    * name object.
    *
    * @{
    */
    private ArrayList<NameFieldParser> corFieldParsers = null;



    /**
    *
    */
    public NameBaseParser(Name p_name) {
        name = p_name;
    } // NameBaseParser()


    /* (non-Javadoc)
    * @see org.llyfrgell.model.name.NameParser#parse(org.llyfrgell.model.name.NameToken)
    */
    public Name parse(NameToken tok) throws NameTokenException {

    // if this is a level 0 group we are at the root of the tree.
    if (tok.isGroup()) {
        // we should have one child
        if (1 != tok.countChildren()) {
            throw new NameTokenException(tok);
        }

        tok = tok.getChild(0);
    }

    // are there any name field parsers?
    Iterator<NameFieldParser> it = iterateFields();

    // parse the name
    while (it.hasNext()) {
        NameFieldParser p = it.next();
        if (null != p) {
            if (p.parse(name, tok)) {
                tok.deactivate();
            }
        }
    } // while it

    return name;
    } // parse()

    /**
    * @{
    * Field parsers.
    */

    /*
    * (non-Javadoc)
    * @see org.llyfrgell.model.name.NameParser#addFieldParser(org.llyfrgell.model.name.NameFieldParser)
    */
    public void addFieldParser(NameFieldParser p_parser) {
        if (null == corFieldParsers) {
            corFieldParsers = new ArrayList<NameFieldParser>();
        }
        corFieldParsers.add(p_parser);
    } // addFieldParser()

    /**
    * Iterator for the field parsers.
    *
    * If there are no field parsers return a degenerate iterator.
    *
    * @return The iterator for each of the field parsers.
    */
    private Iterator<NameFieldParser> iterateFields() {
        Iterator<NameFieldParser> it;
        if (null == corFieldParsers) {
            it = new Iterator<NameFieldParser> () {
                public boolean hasNext() {
                    return false;
                }

                public NameFieldParser next() {
                    return null;
                }
            };
        } else {
            it = corFieldParsers.iterator();
        }
    return it;
    } // iterateFields()

    /**
    * @}
    * Field parsers.
    */


} // class SimpleNameParser
