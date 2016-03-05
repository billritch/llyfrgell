package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Media.
 * @see llyfrgel.Media
 * @author Hibernate Tools
 */
@Stateless
public class MediaHome
{

    private static final Log log = LogFactory.getLog(MediaHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Media transientInstance)
    {
        log.debug("persisting Media instance");
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

    public void remove(Media persistentInstance)
    {
        log.debug("removing Media instance");
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

    public Media merge(Media detachedInstance)
    {
        log.debug("merging Media instance");
        try
        {
            Media result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Media findById(Integer id)
    {
        log.debug("getting Media instance with id: " + id);
        try
        {
            Media instance = entityManager.find(Media.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
