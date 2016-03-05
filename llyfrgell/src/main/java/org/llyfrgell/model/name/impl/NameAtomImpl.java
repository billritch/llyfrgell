/**
 * NameAtomImpl.java Mar 4, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.logging.Level;

import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException.ExceptionCode;

/**
 * A NameAtomImpl is a single word. Words are usually separated by whitespace
 * or some kind of punctuation.
 *
 * A Name Atom is always closed. Nothing may be added directly to it.
 * Its parent must always be a List0.
 *
 * @author William Alan Ritch Mar 4, 2014
 */
public class NameAtomImpl extends
        NameTokenImpl {

    private CharSequence strValue = null;

    public static NameAtomImpl make(CharSequence str_word) {
        NameAtomImpl token = new NameAtomImpl();
        token.setValue(str_word);
        token.close();
        return token;
    } // make()

    /**
     * Default constructor.
     */
    private NameAtomImpl() {
        super(TokenCode.Word);
    }

    /**
     * @see org.llyfrgell.model.name.NameToken#isAtomic()
     *
     * Always returns \c true.
     */
    @Override
    public boolean isAtomic() {
        return true;
    } // isAtomic()


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#buildString(java.lang.StringBuilder, int)
     */
    @Override
    protected StringBuilder buildString(StringBuilder bldr, int offset) {
        if (null == this.getValue()) {
            bldr.append("-null-");
        } else {
            bldr.append('"').append(this.getValue()).append('"');
        }
        return bldr;
    } // buildString()


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#getValue()
     */
    @Override
    public final CharSequence getValue() {
        return this.strValue;
    }

    protected final void setValue(CharSequence str_value) {
        this.strValue = str_value;
    }


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isParent()
     */
    @Override
    public boolean isParent() {
        return false;
    }



    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#countChildren()
     */
    @Override
    public int countChildren() {
        return 0;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getChild(int)
     */
    @Override
    public NameToken getChild(int index) {
        return null;
    }

    /**
     * @{
     * Validation routines.
     *
     * - The value must not be empty or null.
     * - The parent MUST be a List0.
     * - It may have no children.
     */

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThis()
     */
    @Override
    protected boolean validateThis() {
        final String _f = "validateThis";

        boolean bValidated = true;

        // test the value
        CharSequence strValue = this.getValue();
        if (null == strValue) {
            _l.logp(Level.WARNING, _c, _f,
                    "Atomic token must have a non-null value. {0}",
                    this.toString());
            bValidated =  false;
        } // if (null == strValue)

        if (strValue.toString().isEmpty()) {
            _l.logp(Level.WARNING, _c, _f,
                    "Atomic token must have a non-empty value. {0}",
                    this.toString());
            bValidated =  false;
        } // if (strValue.toString().isEmpty())

        return bValidated;
    } // validateThis()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateThat(org.llyfrgell.model.name.impl.NameTokenImpl)
     */
    @Override
    protected boolean validateThat(NameTokenImpl that) {
        return true;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateParent()
     */
    @Override
    protected boolean validateParent() {
        final String _f = "validateParent";

        boolean bValidated = true;

        // check the contents of the parent
        NameToken tokParent = getParent();
        if (null == tokParent) {
            _l.logp(Level.WARNING, _c, _f,
                    "Atomic token's parent MUST not be null. {0}",
                    this.toString());
            bValidated =  false;
        } else if (!tokParent.isList0()) {
                _l.logp(Level.WARNING, _c, _f,
                        "Atomic token's parent {1} MUST be a List. {0}",
                        new Object[] {this.toString(), tokParent.toString()} );
                bValidated =  false;
        } // if (!tokParent.isGroup())

        return bValidated;
    } // validateParent()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#validateChildren()
     */
    @Override
    protected boolean validateChildren() {
        final String _f = "validateChildren";

        boolean bValidated = true;

        // validate the children
        if (this.isParent()) {
            _l.logp(Level.WARNING, _c, _f,
                    "Atomic token may not have any children. {0}",
                    this.toString() );
            bValidated =  false;
        }
        return bValidated;

    } // validateChildren()


    /**
     * @}
     */


    /**
     * @{
     * Implementation of the add token methods.
     * For an Atomic token all we really need to do is to kick the add
     * up to our parent token since nothing may be added directly to
     * a Name Atom.
     *
     * - Add a Group (Close)
     *      Ascend through parents closing all groups and lists that are
     *      along the way until we find a matching group and close it.
     *      TopOfStack - parent of the matching group.
     *
     * - Add a ListN (a List of level N)
     *      Move up the parent chain closing all lists along the way until
     *      we hit either a) any Group, or b) a List of level (N + 1).
     *      If we hit a Group
     *          Create a List of Level N. Insert the new List between the
     *          Group and its current child.
     *      If we hit a List of level (N+1)
     *
     * - Add a List0
     *      Move up to the List0 that owns THIS
     *      Close it.
     *      Make a new List0 which is brother to the parent List0.
     *      If we need to create a parent for both of these Lists, do so.
     */


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addAtomic(org.llyfrgell.model.name.impl.NameAtomImpl)
     */
    @Override
    protected NameToken addAtomic(NameAtomImpl that)
            throws NameTokenException {
        return this.getParent().add(that);
    } // addAtomic()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addOpenGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     *
     * - Add a Group (Open)
     *      Since an Atom is a child of a List0 add THAT as a new brother
     *      to THIS Atom's parent.
     *      TopOfStack - the Group found.
     */
    @Override
    protected NameToken addOpenGroup(NameGroupImpl that)
            throws NameTokenException {
        return this.getParent().add(that);
    } // addOpenGroup

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addCloseGroup(org.llyfrgell.model.name.impl.NameGroupImpl)
     */
    @Override
    protected NameToken addCloseGroup(NameGroupImpl that)
            throws NameTokenException {
        return this.getParent().add(that);
    } // addCloseGroup()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addListN(org.llyfrgell.model.name.impl.NameListImpl)
     */
    @Override
    protected NameToken addListN(NameListImpl that) throws NameTokenException {
        return this.getParent().add(that);
    } // addListN()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addList0(org.llyfrgell.model.name.impl.NameListImpl)
     */
    @Override
    protected NameToken addList0(NameListImpl that) throws NameTokenException {
        return this.getParent().add(that);
    } // addList0()


    /**
     * @}
     */

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#addChild(org.llyfrgell.model.name.NameToken)
     */
    @Override
    protected void addChild(NameToken that)
            throws NameTokenException {
        final String _f = "addChild";

        // closed Group
        _l.logp(Level.WARNING, _c, _f,
                "Cannot add child to Atom. {0}", this.toString());
            throw NameTokenException.makeInvalidOperationException(
                    this, NameTokenException.ExceptionCode.ExceptionCannotAddToAtomic);
    } // addChild()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.impl.NameTokenImpl#removeChild(org.llyfrgell.model.name.NameToken)
     */
    @Override
    protected void removeChild(NameToken that) throws NameTokenException {
        final String _f = "removeChild";
        _l.logp(Level.WARNING, _c, _f,
              "An Atomic token has no children. {0}", toString());
        throw NameTokenException.makeInvalidOperationException(
              this, ExceptionCode.ExceptionNoChildToRemove);
    } // removeChild()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#deactivate()
     */
    public void deactivate() {
       super.deactivateUnconditional();
    } // deactivate()

} // class NameAtomImpl
