package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Recording.
 * @see llyfrgel.Recording
 * @author Hibernate Tools
 */
@Stateless
public class RecordingHome
{

    private static final Log log = LogFactory.getLog(RecordingHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Recording transientInstance)
    {
        log.debug("persisting Recording instance");
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

    public void remove(Recording persistentInstance)
    {
        log.debug("removing Recording instance");
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

    public Recording merge(Recording detachedInstance)
    {
        log.debug("merging Recording instance");
        try
        {
            Recording result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Recording findById(Integer id)
    {
        log.debug("getting Recording instance with id: " + id);
        try
        {
            Recording instance = entityManager.find(Recording.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
