package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sound.
 * @see llyfrgel.Sound
 * @author Hibernate Tools
 */
@Stateless
public class SoundHome
{

    private static final Log log = LogFactory.getLog(SoundHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Sound transientInstance)
    {
        log.debug("persisting Sound instance");
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

    public void remove(Sound persistentInstance)
    {
        log.debug("removing Sound instance");
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

    public Sound merge(Sound detachedInstance)
    {
        log.debug("merging Sound instance");
        try
        {
            Sound result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Sound findById(Integer id)
    {
        log.debug("getting Sound instance with id: " + id);
        try
        {
            Sound instance = entityManager.find(Sound.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
