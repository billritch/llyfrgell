package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class LooneyTunes.
 * @see llyfrgel.LooneyTunes
 * @author Hibernate Tools
 */
@Stateless
public class LooneyTunesHome
{

    private static final Log log = LogFactory.getLog(LooneyTunesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(LooneyTunes transientInstance)
    {
        log.debug("persisting LooneyTunes instance");
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

    public void remove(LooneyTunes persistentInstance)
    {
        log.debug("removing LooneyTunes instance");
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

    public LooneyTunes merge(LooneyTunes detachedInstance)
    {
        log.debug("merging LooneyTunes instance");
        try
        {
            LooneyTunes result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public LooneyTunes findById(Integer id)
    {
        log.debug("getting LooneyTunes instance with id: " + id);
        try
        {
            LooneyTunes instance = entityManager.find(LooneyTunes.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
