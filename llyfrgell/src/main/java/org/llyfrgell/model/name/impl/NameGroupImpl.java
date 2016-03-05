/**
 * NameGroupImpl.java Mar 4, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.logging.Level;

import org.llyfrgell.model.name.NameToken;


/**
 * A NameGroupImpl is a collection of names, contained with "grouping
 * characters" (such as parentheses, brackets, and braces).
 *
 * @author William Alan Ritch Mar 4, 2014
 */
public class NameGroupImpl extends
        NameTokenImpl {

    // Which bracket for this Group, 0 is only used for the root.
    private int nBracket = 0;

    /***
     * Create an opened Group of a specific bracket type.
     *
     * @param p_bracket Bracket type.
     * @return Created Name Group token.
     */
    public static NameGroupImpl make(int p_bracket) {
        return NameGroupImpl.make(p_bracket >= 0, p_bracket);
    } // make()

    /***
     * Create Group with specified bracket type and open status.
     *
     * @param b_state Open state: \c true for Open.
     * @param p_bracket Bracket type.
     * @return Created Name Group token.
     */
    public static NameGroupImpl make(boolean b_state, int p_bracket) {
        NameGroupImpl token = new NameGroupImpl();

        token.setOpen(b_state);
        token.nBracket = Math.abs(p_bracket);

        return token;
    } // make()


    /**
     * Default constructor.
     */
    private NameGroupImpl() {
        super(TokenCode.Group);
    }


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#isMolecular()
     */
    @Override
    public boolean isMolecular() {
        return true;
    } // isMolecular()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isGroup()
     */
    @Override
    public boolean isGroup() {
        return true;
    } // isGroup()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#buildString(java.lang.StringBuilder, int)
     */
    @Override
    protected StringBuilder buildString(StringBuilder bldr, int offset) {
        bldr.append("{");
        super.buildString(bldr, offset);
        bldr.append(" Bracket level=")
            .append(nBracket);
        bldr = buildStringChildren(bldr, offset + 1);
        bldr.append("}");
        return bldr;
    } // buildString()


    /**
     * Bracket code for this Group.
     * @return
     */
    protected int getBracket() {
        return nBracket;
    }

    /*

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addChild(org.llyfrgell.model.name.impl.NameTokenImpl)
     */
    @Override
    protected void addChild(NameToken that)
            throws NameTokenException {
        final String _f = "addChild";

        // only one child may be added
        if (this.isParent()) {
            _l.logp(Level.WARNING, _c, _f,
                    "Cannot add another child. {0}",
                    new Object[] { this.toString() });
            throw NameTokenException.makeInvalidOperationException(
                    this, NameTokenException.ExceptionCode.ExceptionCannotReplaceChild);
        }

        super.addChild(that);
    } // addChild()

    /**
     * @{
     * Validation routines.
     *
     */

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThis()
     */
    @Override
    protected boolean validateThis() {
//        final String _f = "validateThis";
        return true;
    } // validateThis()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThat(org.llyfrgell.model.name.impl.NameTokenImpl)
     */
    @Override
    protected boolean validateThat(NameTokenImpl that) {
        return true;
    } // validateThat()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateParent()
     *
     * The Group must have a parent - unless it is a Group0.
     *  - Group parent:
     *      - Must a Group or a List0
     *      - An Open Group must have a parent that is Open
     *      - Closed Group can have closed or open parent
     */
    @Override
    protected boolean validateParent() {
        final String _f = "validateParent";

        boolean bValidated = true;

        NameTokenImpl tokParent = this.getParent();

        if (null == tokParent) {
            if (0 != this.getBracket()) {
                _l.logp(Level.WARNING, _c, _f,
                    "A Group with no parent is a root. It's group bracket must be 0. {0}",
                    this.toString());
                bValidated = false;
            }
        } else if (!(tokParent.isGroup() || tokParent.isList0())) {
            _l.logp(Level.WARNING, _c, _f,
                    "A Group's parent {1} must be Group or a List0. {0}",
                    new Object[] {this.toString(), tokParent.toString()});
            bValidated = false;
        } else if (this.isOpen() && (!tokParent.isOpen())) {
            _l.logp(Level.WARNING, _c, _f,
                    "Group is Open therefore its Parent {1} must be Open {0}",
                    new Object[] {this.toString(), tokParent.toString()});
            bValidated = false;
        }
        return bValidated;
    } // validateParent()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateChildren()
     *
     * This Group may have no children.
     */
    @Override
    protected boolean validateChildren() {
//        final String _f = "validateChildren";
        boolean bValidated = true;

        // TODO - what validation
        return bValidated;
    } // validateChildren()


    /**
     * @}
     */



    /**
     * @{
     * Implementation of the add token methods.
     *
     */


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addAtomic(org.llyfrgell.model.name.impl.NameAtomImpl)
     *
     * - Create a Level0 List
     * - Add the Atomic to the List
     * - Add the List to this Group
     *
     * TopOfStack = the List0 created.
     */
    @Override
    protected NameToken addAtomic(NameAtomImpl that)
            throws NameTokenException {

        // create a list 0 just for this atom
        NameListImpl list0 = NameListImpl.make(0);
        list0.setLocation(this);
        NameToken tos = addList0(list0);
        list0.add(that);

        // add list to this group
        return tos;
    } // addAtomic()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addOpenGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     *
     * If this is an Open Group, add THAT as the child.
     * TopOfStack THAT.
     *
     */
    @Override
    protected NameToken addOpenGroup(NameGroupImpl that)
            throws NameTokenException {
//        final String _f = "addOpenGroup";

        NameToken tokReturn = null;

        this.addChild(that);
        tokReturn = that;
        return tokReturn;
    } // addOpenGroup()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addCloseGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     * - Add a Group (Close)
     *
     * Start closing groups.
     *  - Close this
     *  - If this matches the bracketing of that we are finished closing
     *    and we will return our parent.
     *  - Otherwise add this Close Group token to our parent.
     *
     */
    @Override
    protected NameToken addCloseGroup(NameGroupImpl that)
            throws NameTokenException {
//        String _f = "addCloseGroup";

        NameTokenImpl tokParent = this.getParent();

        this.close();
        if (!this.matches(that)) {
            if (null != tokParent) {
                tokParent = this.getParent();
                return tokParent.addCloseGroup(that);
            }
        }
        return tokParent;
    } // addCloseGroup()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addListN(org.llyfrgell.model.name.impl.NameListImpl)
     * - Add a ListN (a List of level N)
     *
     *  If this is an Open Group, add the child.
     *  Return this child.
     */
    @Override
    protected NameToken addListN(NameListImpl that) throws NameTokenException {
//        final String _f = "addListN";

        NameTokenImpl tokReturn = null;

        this.addChild(that);
        tokReturn = that;
        return tokReturn;
    } // addListN()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addList0(org.llyfrgell.model.name.impl.NameListImpl)
     * - Add a List0
     *
     *  Same as adding a List N
     */
    @Override
    protected NameToken addList0(NameListImpl that) throws NameTokenException {
        return addListN(that);
    } // addList0()


    /**
     * @}
     */


    /**
     * Does this Group match THAT Group.
     *
     * @param that The other Group.
     * @return \c true if the brackets match.
     */
    private boolean matches(NameGroupImpl that) {
        return (this.getBracket() == that.getBracket());
    } // matches()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#deactivate()
     *
     * Only deactivate if any children are deactivated.
     */
    public void deactivate() {
        if (!(this.isParent() && this.getChild(0).isActive())) {
            super.deactivateUnconditional();
        }
    } // deactivate()

} // class NameGroupImpl()
