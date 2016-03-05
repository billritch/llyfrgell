/**
 * PrefixTest.java Jul 25, 2014
 */
package org.llyfrgell.model.name.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author William Alan Ritch Jul 25, 2014
 *
 */
public class PrefixTest {

    private PrefixHandler handler;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        handler = new PrefixHandler();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimplePrefixes() {
       assertNotNull("Handler", handler);

       assertTrue("Mister", handler.isValid("Mr."));
       assertTrue("Doctor", handler.isValid("Dr."));
       assertTrue("Mistress", handler.isValid("Mrs."));
       assertTrue("Gobblety gook", handler.isValid("J a&8*."));
       assertFalse("Miss", handler.isValid("Miss"));
       assertFalse("dr.", handler.isValid("dr."));
    }

    @Test
    public void testBuiltInPrefix() {
        assertNotNull("Handler", handler);

        assertTrue("Sir", handler.isValid("Sir"));
        assertEquals("Sir normalized", "Sir", handler.normalize("sir"));
    }

    @Test
    public void testAddedPrefix() {
        assertNotNull("Handler", handler);

        handler.addAffix("prof", "professor");
        handler.addAffix("prof.", "professor");
        handler.addAffix("professor", "professor");

        assertTrue("Professor", handler.isValid("Professor"));
        assertTrue("Professor-prof", handler.isValid("Prof"));
        assertTrue("Professor-Prof.", handler.isValid("Prof."));
        assertTrue("Professor", handler.isValid("Professor"));
        assertEquals("Professor normalized-Prof", "professor", handler.normalize("prof"));
        assertEquals("Professor normalized-Prof.", "professor", handler.normalize("prof."));
    }
} // class PrefixTest
