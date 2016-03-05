/**
 * LegacyDaoImpl.java Nov 13, 2013
 */
package org.llyfrgell.model.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.llyfrgell.legacy.generated.Artists;
import org.llyfrgell.legacy.generated.AspectRatio;
import org.llyfrgell.legacy.generated.Category;
import org.llyfrgell.legacy.generated.Colour;
import org.llyfrgell.legacy.generated.Cut;
import org.llyfrgell.legacy.generated.Duty;
import org.llyfrgell.legacy.generated.Genre;
import org.llyfrgell.legacy.generated.Label;
import org.llyfrgell.legacy.generated.Language;
import org.llyfrgell.legacy.generated.Media;
import org.llyfrgell.legacy.generated.RecordingFormat;
import org.llyfrgell.legacy.generated.RecordingSpeed;
import org.llyfrgell.legacy.generated.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author William Alan Ritch Nov 13, 2013
 *
 */
@Repository
public class LegacyDaoImpl implements
        LegacyDao
{

    @Autowired
    private SessionFactory sessionFactory;


    public LegacyDaoImpl() {

    }


    /* (non-Javadoc)
     * @see llyfrgel.model.dao.LegacyDao#listAspect()
     */
    public List<AspectRatio> listAspect()
    {
        @SuppressWarnings("unchecked")
        List<AspectRatio> list = this.sessionFactory.getCurrentSession().
        createCriteria(AspectRatio.class).list();
        return list;
    } // AspectRatio()

    public List<Category> listCategory()
    {
        @SuppressWarnings("unchecked")
        List<Category> list = this.sessionFactory.getCurrentSession().
                createCriteria(Category.class).list();
        return list;
    } // listCategory()

    /* (non-Javadoc)
     * @see llyfrgel.model.dao.LegacyDao#listColor()
     */
    public List<Colour> listColor()
    {
        @SuppressWarnings("unchecked")
        List<Colour> list = this.sessionFactory.getCurrentSession().
                createCriteria(Colour.class).list();
        return list;
    } // listColor()

    public List<Cut> listCut()
    {
        @SuppressWarnings("unchecked")
        List<Cut> list = this.sessionFactory.getCurrentSession().
                createCriteria(Cut.class).list();
        return list;
    } // listCut()

    public List<Duty> listDuty()
    {
        @SuppressWarnings("unchecked")
        List<Duty> list = this.sessionFactory.getCurrentSession().
                createCriteria(Duty.class).list();
        return list;
    } // listDuty()

    public List<Genre> listGenre()
    {
        @SuppressWarnings("unchecked")
        List<Genre> list = this.sessionFactory.getCurrentSession().
                createCriteria(Genre.class).list();
        return list;
    } // listGenre()

    public List<Label> listLabel()
    {
        @SuppressWarnings("unchecked")
        List<Label> list = this.sessionFactory.getCurrentSession().
                createCriteria(Label.class).list();
        return list;
    } // listLabel()

    public List<Language> listLanguage()
    {
        @SuppressWarnings("unchecked")
        List<Language> list = this.sessionFactory.getCurrentSession().
                createCriteria(Language.class).list();
        return list;
    } // listLanguage()

    public List<Media> listMedia()
    {
        @SuppressWarnings("unchecked")
        List<Media> list = this.sessionFactory.getCurrentSession().
                createCriteria(Media.class).list();
        return list;
    } // listMedia()

    public List<RecordingFormat> listRecordingFormat()
    {
        @SuppressWarnings("unchecked")
        List<RecordingFormat> list = this.sessionFactory.getCurrentSession().
                createCriteria(RecordingFormat.class).list();
        return list;
    } // listRecordingFormat()

    public List<RecordingSpeed> listRecordingSpeed()
    {
        @SuppressWarnings("unchecked")
        List<RecordingSpeed> list = this.sessionFactory.getCurrentSession().
                createCriteria(RecordingSpeed.class).list();
        return list;
    } // listRecordingSpeed()

    public List<Sound> listSound()
    {
        @SuppressWarnings("unchecked")
        List<Sound> list = this.sessionFactory.getCurrentSession().
                createCriteria(Sound.class).list();
        return list;
    } // listSound()

    public List<Artists> listArtists()
    {
        @SuppressWarnings("unchecked")
        List<Artists> list = this.sessionFactory.getCurrentSession().
                createCriteria(Artists.class).list();
        return list;
    } // listSound()


    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

}
