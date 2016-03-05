package org.llyfrgell.model.artist;

import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.llyfrgell.model.Country;
import org.llyfrgell.model.name.Name;

/***
 * A Artist is a entity which is treated as if it were an individual.
 * In modern terms this will be a human being or a corporation.
 *
 * The major properties of person-hood are
 * Name
 * Kind of person
 * Birth date
 * Death date
 * Country (location or origin)
 * URLs of information about the person
 *
 * @author William Alan Ritch Oct 30, 2013
 */
public interface Person {

    // Name
    Name getName();

    Name getPrimaryName();

    // Kind of person
    PersonKind getKind();

    // Birth date
    Calendar getBirthDate();

    // Death date
    Calendar getDeathDate();

    // Country
    Country getCountry();

    // URLs
    List<URL> getUrls();


} // interface Artist
