package org.llyfrgell.model.artist;

/***
 * What kind of person is this \c Artist?
 *
 * Most artists are just people. Some are corporations.  This
 * enum identified which kind of artist we are.
 *
 * @author William Alan Ritch Oct 30, 2013
 */
public enum PersonKind
{
    Human,           // a real live human being
    Fictonal,        // a fiction person created by two or more people
    House,           // the house name for a line of books, etc.
    Group            // like a music group or a company or corporation.
} // enum PersonKind
