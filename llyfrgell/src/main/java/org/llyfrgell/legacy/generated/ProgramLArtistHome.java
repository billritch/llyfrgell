package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProgramLArtist.
 * @see llyfrgel.ProgramLArtist
 * @author Hibernate Tools
 */
@Stateless
public class ProgramLArtistHome
{

    private static final Log log = LogFactory.getLog(ProgramLArtistHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(ProgramLArtist transientInstance)
    {
        log.debug("persisting ProgramLArtist instance");
        try
        {
            entityManager.persist(transientInstance);
            log.debug("persist successful");
        } catch (RuntimeException re)
        {
            log.error("persist failed", re);
            throw re;
        }
    }

    public void remove(ProgramLArtist persistentInstance)
    {
        log.debug("removing ProgramLArtist instance");
        try
        {
            entityManager.remove(persistentInstance);
            log.debug("remove successful");
        } catch (RuntimeException re)
        {
            log.error("remove failed", re);
            throw re;
        }
    }

    public ProgramLArtist merge(ProgramLArtist detachedInstance)
    {
        log.debug("merging ProgramLArtist instance");
        try
        {
            ProgramLArtist result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ProgramLArtist findById(Integer id)
    {
        log.debug("getting ProgramLArtist instance with id: " + id);
        try
        {
            ProgramLArtist instance = entityManager.find(ProgramLArtist.class,
                    id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
