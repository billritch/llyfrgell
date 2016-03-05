/**
 * NameListImpl.java Mar 4, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.Iterator;
import java.util.logging.Level;

import org.llyfrgell.model.name.NameToken;

/**
 * A NameListImpl is a collection of names separated by common separator
 * characters (e.g., commas and semi-colons).
 *
 * @author William Alan Ritch Mar 4, 2014
 */
public class NameListImpl extends
        NameTokenImpl {

    /** List level. */
    private int level = 0;

    /***
     * Create a Name List element of specified level.
     *
     * @param level
     * @return
     */
    public static NameListImpl make(int level) {
        NameListImpl token = new NameListImpl();
        token.setLevel(level);
        return token;
    } // make()

    /**
     *
     */
    private NameListImpl() {
        super(TokenCode.List);
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
     * @see org.llyfrgell.model.name.NameToken#isList()
     */
    @Override
    public boolean isList() {
        return true;
    } // isList()


    /**
     * A Level 0 list has special features. Words and Groups can be added only
     * to Level 0 lists, but not to other kinds of lists. Remember a Group
     * is a Level 0 list.
     *
     * @return \c true if this token is a Level 0 List or a Group.
     */
    @Override
    public boolean isList0() {
        return (0 == this.getLevel());
    } // isList0()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#isListN()
     */
    @Override
    public boolean isListN() {
        return (this.getLevel() > 0);
    } // isListN()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isListN(int)
     */
    @Override
    public boolean isListN(int n_level) {
        return (n_level == this.getLevel());
    } // isListN()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#buildString(java.lang.StringBuilder, int)
     */
    @Override
    protected StringBuilder buildString(StringBuilder bldr, int offset) {
        bldr.append("[");
        super.buildString(bldr, offset);
        bldr.append(" Lev=").append(this.getLevel());
        bldr = buildStringChildren(bldr, offset);
        bldr.append("]");
        return bldr;
    } // buildString()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getLevel()
     */
    @Override
    public final int getLevel() {
        return this.level;
    } // getLevel()

    protected final void setLevel(int p_level) {
        this.level = p_level;
    } // setLevel()



    /**
     * @{
     * Validation routines.
     *
     */

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThis()
     *
     * The level cannot be negative.
     */
    @Override
    protected boolean validateThis() {
        final String _f = "validateThis";

        boolean bValidated = true;

        if (this.getLevel() < 0) {
            _l.logp(Level.WARNING, _c, _f,
                    "A List may not have a level {1} less than 0. {0}",
                    new Object[] {this.toString(), "" + this.getLevel() });
            bValidated = false;
        }
        return bValidated;
    } // validateThis()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThat(org.llyfrgell.model.name.impl.NameTokenImpl)
     *
     * List0s can add Atomics, Groups, or ListNs.
     * ListNs can only add a list that is a proper child
     */
    @Override
    protected boolean validateThat(NameTokenImpl that) {
        final String _f = "validateThat";

        boolean bValidated = true;

        if (this.isListN()) {
            if (!that.isList()) {
                _l.logp(Level.WARNING, _c, _f,
                        "Only Lists may be added to a List with Level > 0. Not {1}. {0}",
                        new Object[] {this.toString(), that.toString() });
                bValidated = false;
            } else {
                NameListImpl listThat = (NameListImpl) that;
                if (0 != this.compareLevels(listThat)) {
                    _l.logp(Level.WARNING, _c, _f,
                            "Attempting to add ({1}) which is not the first generation child of its parent. {0}",
                            new Object[] {this.toString(), that.toString()});
                    bValidated = false;
                }
            }
        } else {
            if (that.isList0()) {
                _l.logp(Level.WARNING, _c, _f,
                        "Attempting to add a List 0 ({1}) to a List0. {0}",
                        new Object[] {this.toString(), that.toString()});
                bValidated = false;
            }
        }
        return bValidated;
    } // validateThat()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateParent()
     *
     * Any List must have a parent.
     *  - List parent:
     *      - Must be either a List or a Group
     *      - If the parent is a List is must have a Level 1 higher
     *      - that then current level.
     *  - If this list is Open it's parent must also be open.
     */
    @Override
    protected boolean validateParent() {
        final String _f = "validateParent";

        boolean bValidated = true;

        NameTokenImpl tokParent = this.getParent();

        if (null == tokParent) {
            _l.logp(Level.WARNING, _c, _f,
                    "The List must have a parent. {0}",
                    this.toString());
                bValidated = false;
        } else if (tokParent.isGroup() || tokParent.isList()) {

            if (tokParent.isList()) {
                NameListImpl listParent = (NameListImpl) tokParent;
                // this list must be one less that the parent's list
                if (0 != listParent.compareLevels(this)) {
                    _l.logp(Level.WARNING, _c, _f,
                            "This List is not the first generation child of its Parent. {0}",
                            new Object[] { this.toString(), listParent.toString()} );
                        bValidated = false;
                }
            }

            if (this.isOpen() && !tokParent.isOpen()) {
                _l.logp(Level.WARNING, _c, _f,
                        "List is Open therefore its Parent {1} must be Open {0}",
                        new Object[] {this.toString(), tokParent.toString()});
                bValidated = false;
            }

        } else {
            // invalid type of parent
            _l.logp(Level.WARNING, _c, _f,
                    "A List's parent {1} must be Group or a List. {0}",
                    new Object[] {this.toString(), tokParent.toString()});
            bValidated = false;
        }


        return bValidated;
    } // validateParent()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateChildren()
     *
     * Validations are that each child is a legitimate child of the parent.
     *  - For lists the children of the parent list must have a level that
     *    is one less that the level of the parent.
     *
     * Children are only validated if the "shouldValidateChildren()" test
     * returns \c true.
     */
    @Override
    protected boolean validateChildren() {
        final String _f = "validateChildren";
        boolean bValidated = true;

        if (this.isParent()) {

            // iterate through the children
            Iterator<NameToken> it = iteratorComplete();
            if (null != it) {
                while (it.hasNext()) {
                    NameTokenImpl tokChild = (NameTokenImpl) it.next();

                    // Each child's parent should point to this token
                    if (!tokChild.isParentEqual(this)) {
                        _l.logp(Level.WARNING, _c, _f,
                            "Child ({1}) has a parent that is not this parent. {0}",
                            new Object[] { this.toString(), tokChild.toString() });

                        bValidated = false;
                    }

                    // List0 - can have Words or Groups as its children
                    if (this.isList0()) {
                        if (!(tokChild.isAtomic() || tokChild.isGroup())) {
                            // child is not a valid type
                            _l.logp(Level.WARNING, _c, _f,
                                "Child ({1}) has a is wrong token type (must be Word or Group) for this parent. {0}",
                                new Object[] { this.toString(), tokChild.toString() });
                            bValidated = false;
                        }

                    } else {
                        // List (Level > 0) - can only have children
                        // which are List (Parent Level - 1)
                        if (!tokChild.isList()) {
                            // child is not a valid type
                            _l.logp(Level.WARNING, _c, _f,
                                "Child ({1}) has a is wrong token type (must be List) for this parent. {0}",
                                new Object[] { this.toString(),
                                    tokChild.toString() });
                            bValidated = false;

                        } else {
                            if (0 != this.compareLevels((NameListImpl) tokChild)) {
                                // child has the wrong level
                                _l.logp(Level.WARNING, _c, _f,
                                    "Child ({1}) is not the first generation child of its parent. {0}",
                                    new Object[] { this.toString(),
                                        tokChild.toString() });
                                bValidated = false;
                            }
                        }
                    }

                } // while()
            }
        }

        return bValidated;
    } // validateChildren()

    /**
     * @}
     */


    /**
     * @{
     * add...() implementation
     */


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addAtomic(org.llyfrgell.model.name.impl.NameAtomImpl)
     *
     * At this point we are only trying to add Atomic tokens to a List0.
     * Add THAT to list of children.
     * Return this.
     */
    @Override
    protected NameToken addAtomic(NameAtomImpl that)
            throws NameTokenException {
        this.addChild(that);
        return this;
    } // addAtomic()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addOpenGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     *
     * At this point we are only adding open groups to a List0.
     * Add THAT to the list of children.
     * Return THAT.
     */
    @Override
    protected NameToken addOpenGroup(NameGroupImpl that)
            throws NameTokenException {
        this.addChild(that);
        return that;
    } // addOpenGroup()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addCloseGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     *
     * Start closing groups.
     *  - Close this
     *  - If this matches the bracketing of that we are finished closing
     *    and we will return our parent.
     *  - Otherwise add this Close Group token to our parent.
     */
    @Override
    protected NameToken addCloseGroup(NameGroupImpl that)
            throws NameTokenException {
//      String _f = "addCloseGroup";

      NameTokenImpl tokParent = null;

      this.close();
      tokParent = this.getParent();
      return tokParent.addCloseGroup(that);
    } // addCloseGroup()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addListN(org.llyfrgell.model.name.impl.NameListImpl)
     *
     * We have validated the add and all we have to do is to add this to
     * the children.
     * We return the new item we have added.
     */
    @Override
    protected NameToken addListN(NameListImpl that) throws NameTokenException {
        return add2List(that);
    } // addListN()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addList0(org.llyfrgell.model.name.impl.NameListImpl)
     *
     * At this point we have validated THAT and it is not a List 0
     */
    @Override
    protected NameToken addList0(NameListImpl that) throws NameTokenException {
        return add2List(that);
    } // addList0()



    private NameToken add2List(NameListImpl that) throws NameTokenException {
        // we close this list
        this.close();

        NameListImpl tokLast =  this;
        NameTokenImpl tokCurrent = this;

        // then close all of its parents until we get to one at the same
        // level as THAT or a group.
        while (that.isCloseable(tokCurrent)) {
            tokLast = (NameListImpl) tokCurrent;
            tokLast.close();
            tokCurrent = tokCurrent.getParent();
        } // while isCloseable()


        // at this point:
        // tokLast is the last token we have closed
        // tokCurrent is the parent above tokLast - they may be the same

        if (tokLast.getLevel() < that.getLevel()) {
            // Unhook tokLast from its parent (tokCurrent)
            tokLast.makeOphan();

            // Create a new parent for tokLast, keep adding parents
            // until we get to Level == that.Level+1. This will be the
            // "tokParent"
            NameListImpl tokParent = null;
            int level = tokLast.getLevel();
            while (level <= (that.getLevel())) {
                level ++;
                tokParent = NameListImpl.make(level);
                tokParent.setLocation(this);
                tokParent.addChild(tokLast);
                tokLast = tokParent;
            }

            // Add THAT to tokParent
            tokParent.addChild(that);

            // Add tokParent to tokCurrent
            tokCurrent.addChild(tokParent);

        } else if (tokLast.getLevel() == that.getLevel()) {
            //      This means THAT should become the brother of tokLast
            NameListImpl tokParent = null;
            if (tokCurrent.isGroup()) {
                // Unhook tokLast from its parent (tokCurrent)
                tokLast.makeOphan();

                // Create a List of Level = that.Level +1
                tokParent = NameListImpl.make(that.getLevel()+1);
                tokParent.setLocation(that);
                // Make tokLast a child of tokParent
                tokParent.addChild(tokLast);
                tokCurrent.add(tokParent);
            } else {
                tokParent = (NameListImpl) tokCurrent;
            }
            tokParent.addChild(that);
        } else {
            // impossible
        }

        // Create all children of THAT down to a List0
        return that.createListChildren();
    } // add2List()



    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#deactivate()
     *
     * Deactivate this token if all of its child tokens are not active.
     */
    public void deactivate() {
        if (!this.isActive()) {
            // if there is no iterator - there are no children
            Iterator<NameToken> it = iterator();
            // if there are no active children we can deactivate
            if ((null == it) || (!it.hasNext())) {
                super.deactivateUnconditional();
            }
        }
    } // deactivate()


    /**
     * Can this be closed?
     * It can be closed if the THAT is a list whose level is <= this' level.
     *
     * @param that
     * @return \c true if we can close that.
     */
    private boolean isCloseable(NameTokenImpl that) {
        if (null == that) {
            return false;
        }
        if (!that.isList()) {
            return false;
        }
        return ((NameListImpl) that).getLevel() <= this.getLevel();
    } // isCloseable()

    /**
     * Create multiple children of this List.
     *
     * Starting with this list create a dynasty of Lists all the way
     * down to a List0.
     *
     * @return The created List0.
     * @throws NameTokenException
     */
    private NameToken createListChildren() throws NameTokenException {
//        final String _f = "createListChildren";

        NameListImpl listNext = null;
        NameListImpl listCurrent = this;
        int levCurrent = this.getLevel();

        while (0 != levCurrent) {
            levCurrent--;
            listNext = NameListImpl.make(levCurrent);
            listNext.setLocation(this);
            listCurrent.addChild(listNext);
            listCurrent = listNext;
        }

        return listCurrent;
    } // createListChildren()

    /**
     * Orphan the specified object.
     *
     * We unhook this from its parent, setting the parent pointer to \c null.
     * We also remove the child from its parent.
     * @throws NameTokenException
     */
    private void makeOphan() throws NameTokenException {
        if (null != getParent()) {
            // ask my parent to give me up
            getParent().removeChild(this);
        }

        setParent(null);
    } // makeOphan()

    /**
     * We compare the level of THIS List with THAT list.
     * We return a 0 is THAT List has a level exactly one less than
     * THIS list.  We return a positive number if THIS list has a higher
     * level than THAT List.  Otherwise return a negative number to
     * indicate that THIS List has a lower level than THAT List.
     *
     * @param that
     * @return - 0 if THAT List is just below THIS List
     * @throws NameTokenException
     */
    private int compareLevels(NameListImpl that) {
        final String _f = "compareLevels";

        if (null == that ) {
            _l.logp(Level.WARNING, _c, _f,
                    "Cannot compare levels if parameter is NULL. {0}",
                    this.toString());
            return 1;
        }
        return this.getLevel() - (1 + that.getLevel());
    } // compareLevels()




} // class NameListImpl
