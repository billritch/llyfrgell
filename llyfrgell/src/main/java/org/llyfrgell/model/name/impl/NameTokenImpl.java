package org.llyfrgell.model.name.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.llyfrgell.model.name.NameToken;
import org.llyfrgell.model.name.NameToken.NameTokenException.ExceptionCode;

public abstract class NameTokenImpl
    implements NameToken {

    protected final String _c = this.getClass().getSimpleName();
    protected final Logger _l = Logger.getLogger(_c);

    private TokenCode elCode;
    private NameTokenImpl parent = null;
    private boolean bOpen = true;

    // Is the token active or not
    private boolean bActive = true;

    private boolean bFixExpected = false;
    private boolean bValidateChildren = true;

    // Location of this token
    private Location loc;

    /** a series of children. */
    private List<NameToken> children = null;

    private static class Reversed<T> implements Iterable<T> {
        private final List<T> original;

        public Reversed(List<T> original) {
            this.original = original;
        }

        public Iterator<T> iterator() {
            final ListIterator<T> i = original.listIterator(original.size());

            return new Iterator<T>() {
                public boolean hasNext() { return i.hasPrevious(); }
                public T next() { return i.previous(); }
                public void remove() { i.remove(); }
            };
        }

        public static <T> Reversed<T> reversed(List<T> original) {
            return new Reversed<T>(original);
        }
    } // class Reversed()


    /**
     * Default constructor.  Creates an empty token.
     */
    public NameTokenImpl() {
        this.elCode = TokenCode.Empty;
        _l.setLevel(Level.FINEST);
    } // NameTokenImpl()

    /**
     * Constructor used to create specific kinds of tokens.
     *
     * @param p_code Node's token code.
     */
    protected NameTokenImpl(TokenCode p_code) {
        elCode = p_code;
        _l.setLevel(Level.FINEST);
    } // NameTokenImpl(TokenCode)


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr = buildString(bldr, 0);
        return bldr.toString();
    } // toString()

    /**
     * Document the common parts of this token.
     *
     * The class is identified as well as open or closed status.
     *
     * @param bldr Buffer to accumulate the token description.
     * @param offset Indentation level for this token.
     * @return The passed-in StringBuilder.
     */
    protected StringBuilder buildString(StringBuilder bldr, int offset) {
        bldr.append(_c).append(": ");
//        bldr.append(" ID=").append(getID());
        bldr.append(getCode().toString())
            .append(isOpen() ? " Open" : " Closed");
        return bldr;
    } // buildString()

    /**
     * Add all the children of this token to the documented buffer.
     *
     * @param bldr Buffer to accumulate the token description.
     * @param offset Indentation level for this token.
     * @return The passed-in StringBuilder.
     */
    protected final StringBuilder buildStringChildren(StringBuilder bldr, int offset) {
        // display children (if they exist)
        int childCount = this.countChildren();
        if (isParent()) {
            bldr.append(" Children: (")
                .append(this.countChildren())
                .append(")");
            buildNewLine(bldr, offset);

            // if there is only one child build it as a single line
            // boolean bFirstChild = true;
            // int nrChild = 0;

            if (1 == childCount) {
                NameTokenImpl childImp = (NameTokenImpl) this.children.get(0);
                bldr = childImp.buildString(bldr, offset + 1);
                buildNewLine(bldr, offset-1);
            } else {
                boolean bFirstToken = true;
                // put each child on a different line
                for (NameToken child : children) {
                    NameTokenImpl childImp = (NameTokenImpl) child;
                    if (!bFirstToken) {
                        buildNewLine(bldr, offset);
                    }
                    bldr = childImp.buildString(bldr, offset + 1);
                    bFirstToken = false;
                } // for child

                buildNewLine(bldr, offset-1);
            }

        }
        return bldr;
    } // buildStringChildren()

    protected final StringBuilder buildNewLine(StringBuilder bldr, int offset) {
        bldr.append("\n");
        for (int i = 0; i < offset; i++) {
            bldr.append("  ");
        }

        return bldr;
    } // buildNewLine()

    /**
     * Return current location.
     *
     * @return Location object.
     */
    public final Location getLocation() {
        return loc;
    } // getLocation()

    /**
     * Store the location of this token, referencing the original
     * string that is being parsed.
     *
     * @param str_original Original string.
     * @param ndx Current index.
     * @return \c this - for chaining.
     */
    public final NameTokenImpl setLocation(CharSequence str_original, int ndx) {
        if (null == str_original) {
            str_original = "";
        }
        loc = Location.make(str_original.toString(), ndx);
        return this;
    } // setLocation()

    /**
     * Store the specified Location into the Name Token object.
     *
     * @param p_loc Location to be stored.
     * @return \c this - for chaining.
     */
    public final NameTokenImpl setLocation(NameTokenImpl tok) {
        loc = tok.getLocation();
        return this;
    } // setLocation()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isActive()
     */
    public final boolean isActive() {
        return bActive;
    } // isActive()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#activate()
     */
    public final void activate() {
        bActive = true;
    } // activate()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isAtomic()
     */
    public boolean isAtomic() {
        return false;
    } // isAtomic()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isMolecular()
     */
    public boolean isMolecular() {
        return false;
    } // isMolecular()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isGroup()
     */
    public boolean isGroup() {
        return false;
    } // isGroup()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isList()
     */
    public  boolean isList() {
        return false;
    } // isList()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isList0()
     */
    public boolean isList0() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isListN()
     */
    public boolean isListN() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isListN(int)
     */
    public boolean isListN(int n_level) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getListLevel()
     */
    public int getLevel() {
        return -1;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isChild()
     */
    public boolean isChild() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isOpen()
     */
    public boolean isOpen() {
        return bOpen;
    }


    /**
     * Set the open value.
     *
     * @param b_open New open value.
     */
    protected final void setOpen(boolean b_open) {
        this.bOpen = b_open;
    }

    /**
     * Close the open token.
     */
    protected final void close() {
        setOpen(false);
        Iterator<NameToken> it = iteratorComplete();
        if (null != it) {
            while (it.hasNext()) {
                NameToken tok = it.next();
                if (null != tok) {
                    ((NameTokenImpl) tok).close();
                }
            }
        }
    } // close()


    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<NameToken> iterator() {
        Iterator<NameToken> it = new Iterator<NameToken>() {

            private Iterator<NameToken> it = (null == children)
                    ? null : children.iterator();
            private NameToken currentToken = null;

            /*
             * (non-Javadoc)
             * @see java.util.Iterator#hasNext()
             */
            public boolean hasNext() {
                currentToken = getNextActiveToken();

                return (null != currentToken);
            } // hasNext()

            /*
             * (non-Javadoc)
             * @see java.util.Iterator#next()
             */
            public NameToken next() {
                NameToken tok;

                // if there is a current token, return that
                if (null == currentToken) {
                    currentToken = getNextActiveToken();
                }

                // if there is no current token we have no next
                if (null == currentToken) {
                    throw new NoSuchElementException();
                }

                // return the current token and clear it
                tok = currentToken;
                currentToken = null;
                return tok;
            } // next()

            /**
             * Find the next active token.
             *
             * @return The found token, or \c null if we have come to
             * the end of the list and there was none found.
             */
            private NameToken getNextActiveToken() {
                // if there are no children we have no "next"
                if (null == it) {
                    return null;
                }

                while (it.hasNext()) {
                    NameToken tok = it.next();
                    if (tok.isActive()) {
                        return tok;
                    }
                } // while

                // if we cannot find the token, return null
                return null;
            } // getNextActiveToken()

        }; // iterator class

        return it;
    } // iterator()

    public Iterable<NameToken> reverse() {
        return Reversed.reversed(this.children);
    } // reverse()

    /**
     * This iterate iterates through all of the children. Unlike the
     * publicly available one which only iterates through the active
     * children.
     *
     * Internally this simply returns the iterator for the stored child
     * list.
     *
     * @return An iterator. If there are no children, \c null is returned.
     */
    protected Iterator<NameToken> iteratorComplete() {
        return (null == children) ? null : children.iterator();
    } // iterator()



    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#countChildren()
     */
    public int countChildren() {
        if (null == this.children) {
            return 0;
        }
        return this.children.size();
    } // countChildren();

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getChild(int)
     */
    public NameToken getChild(int index) {
        if (index > this.children.size()) {
            return null;
        }

        return this.children.get(index);
    } // getChild()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#isParent()
     */
    public boolean isParent() {
        return (0 != this.countChildren());
    } // isParent()

    /**
     * Initialize the children as an array of NameTokens.
     */
    private void initializeChildren() {
        if (null == this.children) {
            this.children = new ArrayList<NameToken>();
        }
    } // initializeChildren()


    /**
     * Add a child directly to the end of this List's children.
     * We should have already validated THIS (as a List0).
     *
     * @param that Child to be added to List.
     * @throws NameTokenException If the argument is invalid.
     */
    protected void addChild(NameToken that)
            throws NameTokenException {
        final String _f = "addChild";

        // don't add a null token
        if (null == that) {
            _l.logp(Level.WARNING, _c, _f,
                    "Adding a NULL token to this {0}",
                    this.toString());
            throw NameTokenException.makeInternalValidationException(
                    this, NameTokenException.ExceptionCode.ExceptionCannotBeNull);
        }

        ((NameTokenImpl) that).setParent(this);

        initializeChildren();
        children.add(that);
    } // addChild()

    protected void removeChild(NameToken that) throws NameTokenException {
        final String _f = "removeChild";

        // are there any children
        if (!this.isParent()) {
            _l.logp(Level.WARNING, _c, _f,
                    "No children found. {0}", this.toString());
              throw NameTokenException.makeInvalidOperationException(
                    this, ExceptionCode.ExceptionNoChildToRemove);
        }

        if (null == that) {
            _l.logp(Level.WARNING, _c, _f,
                    "Child to be removed is NULL. {0}", this.toString());
              throw NameTokenException.makeInvalidOperationException(
                    this, ExceptionCode.ExceptionNoChildToRemove);
        }

        if (!this.children.remove(that)) {
            _l.logp(Level.WARNING, _c, _f,
                    "Child to remove {1} not found. {0}",
                    new Object[] { toString(), that.toString()} );
              throw NameTokenException.makeInvalidOperationException(
                    this, ExceptionCode.ExceptionNoChildToRemove);
        }

    } // removeChild()

    /**
     * Deactivate this token regardless of the state of its children.
     */
    protected final void deactivateUnconditional() {
        this.bActive = false;
    } // deactiveUnconditional()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getValue()
     */
    public CharSequence getValue() {
        return "";
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#add(org.llyfrgell.model.name.NameToken)
     */
    public final NameToken add(NameToken that) throws NameTokenException {
        final String _f = "add";

        // validate that we cannot add a NULL
        if (null == that) {
            _l.logp(Level.WARNING, _c, _f,
                    "Null parameter is invalid. {0}", this.toString());
            throw NameTokenException.makeInvalidParamterException(
                    this, ExceptionCode.ExceptionCannotBeNull);
        }

        // validate the token
        if (!validate()) {
            _l.logp(Level.SEVERE, _c, _f,
                    "Current token failed validation. {0}", this.toString());
            throw NameTokenException.makeInternalValidationException(
                    this, ExceptionCode.ExceptionFatalInternalValidation);
        }

        if (!validateThat((NameTokenImpl) that)) {
            throw NameTokenException.makeInvalidOperationException(
                    this, ExceptionCode.ExceptionCannotAddToClosed);
        }

        // we cannot add anything to something that is closed
        if (!this.isOpen()) {
            _l.logp(Level.SEVERE, _c, _f,
                    "Cannot add anything to a closed Name token. {0}",
                    this.toString());
        }


        NameToken tokReturn = null;
        if (that.isAtomic()) {
            tokReturn = addAtomic((NameAtomImpl) that);
        } else if (that.isGroup()) {
            if (that.isOpen()) {
                tokReturn = addOpenGroup((NameGroupImpl) that);
            } else {
                tokReturn = addCloseGroup((NameGroupImpl) that);
            }
        } else if (that.isListN()) {
            tokReturn = addListN((NameListImpl) that);
        } else if (that.isList0()) {
            tokReturn = addList0((NameListImpl) that);
        } else {
            _l.logp(Level.SEVERE, _c, _f,
                    "Parameter does not admit to being Atomic, Group, nor List. This should be impossible. {0}",
                    this.toString());
            throw NameTokenException.makeInvalidParamterException(
                    this, ExceptionCode.ExceptionTokenInvalidCode);
        }

        return tokReturn;
    } // add()

    /**
     * @{ Group
     * Implementation add.
     *
     * By the time the implementation gets the add request the parameter
     * as been validated, and the current token (this) has also been
     * validated.
     */

    /**
     * Implementation add: Atomic.
     *
     * @param that Atomic token to be added.
     * @return New Top of Stack.
     * @throws NameTokenException
     */
    protected abstract NameToken addAtomic(NameAtomImpl that)
            throws NameTokenException;

    /**
     * Implementation add: Open Group.
     *
     * @param that Group token to be added.
     * @return New Top of Stack.
     * @throws NameTokenException
     */
    protected abstract NameToken addOpenGroup(NameGroupImpl that)
            throws NameTokenException;

    /**
     * Implementation add: Close Group.
     *
     * @param that Group token to initiate closing.
     * @return New Top of Stack.
     * @throws NameTokenException
     */
    protected abstract NameToken addCloseGroup(NameGroupImpl that)
            throws NameTokenException;

    /**
     * Implementation add: List level N (N>0).
     *
     * @param that List token to be added.
     * @return New Top of Stack.
     * @throws NameTokenException
     */
    protected abstract NameToken addListN(NameListImpl that)
            throws NameTokenException;

    /**
     * Implementation add: List level 0.
     *
     * @param that List token to be added.
     * @return New Top of Stack.
     * @throws NameTokenException
     */
    protected abstract NameToken addList0(NameListImpl that)
            throws NameTokenException;

    /**
     * @}
     */

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.name.NameToken#getCode()
     */
    public final TokenCode getCode() {
        return this.elCode;
    } // getCode()


    /**
     * Change the parent of this token to the specified token.
     *
     * @param p_parent
     */
    protected void setParent(NameTokenImpl p_parent) {
        this.parent = p_parent;
    } // setParent()

    protected NameTokenImpl getParent() {
        return this.parent;
    } // getParent()


    protected void clearParent() {
        this.parent = null;
    }

    protected boolean isParentEqual(NameTokenImpl p_token) {
        return (p_token == getParent());
    }


    /**
     * Validate the contents of this node.
     * If the contents are valid, a \c true is returned.
     * If there is a problem an error message is generated.
     *
     * There are multiple states for validation.
     *  - Validate the current token (this).
     *  - Validate the Parent of the current token.
     *  - Validate the Children of the current token.
     *
     * We perform all the validations so that the log is filled with potential
     * errors. Instead of just finding the first error and stopping.
     */
    protected final boolean validate() {
//        final String _f = "validate";

        boolean bValidated = true;

        // validate the current THIS
        bValidated &= this.validateThis();

        // validate the parent of THIS
        bValidated &= this.validateParent();

        // validate the children of THIS
        bValidated &= this.validateChildren();

        return bValidated;
    } // validate()


    /**
     * Validate the state of the current token.
     * @return \c true if valid.
     */
    protected abstract boolean validateThis();

    /**
     * Validate the parameter.
     *
     * @param that Parameter to be validated.
     * @return \c true if valid.
     */
    protected abstract boolean validateThat(NameTokenImpl that);

    /**
     * Validate the state of the current token's parent.
     * @return \c true if valid.
     */
    protected abstract boolean validateParent();

    /**
     * Validate the state of the current token's children.
     * @return \c true if valid.
     */
    protected abstract boolean validateChildren();


    protected final boolean isFixExpected() {
        return bFixExpected;
    } // isFixExpected()

    protected boolean shouldValidateChildren() {
        return bValidateChildren;
    }

} // class NameTokenImpl
