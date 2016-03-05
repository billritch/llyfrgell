package org.llyfrgell.legacy.conversion;

import java.util.Date;

import org.llyfrgell.legacy.generated.Artists;
import org.llyfrgell.model.artist.person.Artist;
import org.llyfrgell.model.name.NameToken.NameTokenException;
import org.llyfrgell.model.name.NameTokenizer;
import org.llyfrgell.model.name.OneName;
import org.llyfrgell.model.name.impl.HonorificFieldParser;
import org.llyfrgell.model.name.impl.NameTokenizerImpl;
import org.llyfrgell.model.name.impl.SimpleNameParser;
import org.llyfrgell.model.name.impl.SurnameFirstFieldParser;


/**
 *
 * @author William Alan Ritch Jan 16, 2014
 *
 */
public class ArtistConverter extends UniqueConverter
{
    private NameTokenizer t;
    private SimpleNameParser p;

    public ArtistConverter() {
        t = new NameTokenizerImpl();
        p = new SimpleNameParser();
        p.addFieldParser(new SurnameFirstFieldParser());
        p.addFieldParser(new HonorificFieldParser());
    }

    public Artist convert(Artists p_legacy) {
        Artist artist = (Artist) super.convert_internal(p_legacy, new Artist());

        // convert artist name - there should be only one
        try {
            String strArtistName = fixName(p_legacy.getArtist());

            OneName name = (OneName) p.parse(t.parse(strArtistName));
            artist.setName(name);
        } catch (NameTokenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        // convert real name
        // List<OneName> lstRealNames = p.parse(p_legacy.getRealName());

        // convert short name
        String strShortName = p_legacy.getShortName();

        // convert duty number
        Integer l = p_legacy.getDutyNr();

        // convert born and died
        Date dtBorn = p_legacy.getBorn();
        Date dtDied = p_legacy.getDied();

        // convert country
        String strCountry = p_legacy.getCountry();

        return artist;
    } // convert()

    private String fixName(String name) {
        if (name.endsWith("; O.B.E.") || name.endsWith(";O.B.E.")) {
            int n = name.lastIndexOf(';');
            name = name.substring(0, n) + " (O.B.E.)";
        } else if (name.endsWith("; Jr.") || name.endsWith(";Jr.")) {
            int n = name.lastIndexOf(';');
            name = name.substring(0, n) + " (Jr.)";
        }
        return name;
    } // fixName()

} // ArtistConverter()
