package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProgramAArtist.
 * @see llyfrgel.ProgramAArtist
 * @author Hibernate Tools
 */
@Stateless
public class ProgramAArtistHome
{

    private static final Log log = LogFactory.getLog(ProgramAArtistHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(ProgramAArtist transientInstance)
    {
        log.debug("persisting ProgramAArtist instance");
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

    public void remove(ProgramAArtist persistentInstance)
    {
        log.debug("removing ProgramAArtist instance");
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

    public ProgramAArtist merge(ProgramAArtist detachedInstance)
    {
        log.debug("merging ProgramAArtist instance");
        try
        {
            ProgramAArtist result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ProgramAArtist findById(Integer id)
    {
        log.debug("getting ProgramAArtist instance with id: " + id);
        try
        {
            ProgramAArtist instance = entityManager.find(ProgramAArtist.class,
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
