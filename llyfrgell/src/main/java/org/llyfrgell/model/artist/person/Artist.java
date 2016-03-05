package org.llyfrgell.model.artist.person;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.llyfrgell.model.Country;
import org.llyfrgell.model.UniqueBase;
import org.llyfrgell.model.artist.Person;
import org.llyfrgell.model.artist.PersonKind;
import org.llyfrgell.model.name.Name;

public class Artist
    extends UniqueBase
    implements Person {

    private final static String XML_NAME = "artist";
    private final static String XML_LIST_NAME = "artist_list";

    private Name name;
    private Name primaryName;
    private PersonKind kind;
    private Calendar calBirth;
    private Calendar calDeath;
    private List<URL> urls;
    private Country country;

    public Artist()
    {
        super(0, XML_NAME, XML_LIST_NAME);
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getName()
     */
    public Name getName() {
        return name;
    }

    public void setName(Name p_name) {
        name = p_name;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getPrimaryName()
     */
    public Name getPrimaryName() {
        return primaryName;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getKind()
     */
    public PersonKind getKind() {
        return kind;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getBirthDate()
     */
    public Calendar getBirthDate() {
        return calBirth;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getDeathDate()
     */
    public Calendar getDeathDate() {
        return calDeath;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getCountry()
     */
    public Country getCountry() {
        return country;
    }

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.Person#getUrls()
     */
    public List<URL> getUrls() {
        return urls;
    }
} // class Person
