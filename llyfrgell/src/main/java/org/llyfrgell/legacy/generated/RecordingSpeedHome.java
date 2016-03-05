package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecordingSpeed.
 * @see llyfrgel.RecordingSpeed
 * @author Hibernate Tools
 */
@Stateless
public class RecordingSpeedHome
{

    private static final Log log = LogFactory.getLog(RecordingSpeedHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(RecordingSpeed transientInstance)
    {
        log.debug("persisting RecordingSpeed instance");
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

    public void remove(RecordingSpeed persistentInstance)
    {
        log.debug("removing RecordingSpeed instance");
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

    public RecordingSpeed merge(RecordingSpeed detachedInstance)
    {
        log.debug("merging RecordingSpeed instance");
        try
        {
            RecordingSpeed result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public RecordingSpeed findById(Integer id)
    {
        log.debug("getting RecordingSpeed instance with id: " + id);
        try
        {
            RecordingSpeed instance = entityManager.find(RecordingSpeed.class,
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
