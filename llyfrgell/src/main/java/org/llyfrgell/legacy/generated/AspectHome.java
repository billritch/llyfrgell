package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AspectRatio.
 * @see AspectRatio.Aspect
 * @author Hibernate Tools
 */
@Stateless
public class AspectHome
{

    private static final Log log = LogFactory.getLog(AspectHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(AspectRatio transientInstance)
    {
        log.debug("persisting AspectRatio instance");
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

    public void remove(AspectRatio persistentInstance)
    {
        log.debug("removing AspectRatio instance");
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

    public AspectRatio merge(AspectRatio detachedInstance)
    {
        log.debug("merging AspectRatio instance");
        try
        {
            AspectRatio result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public AspectRatio findById(Integer id)
    {
        log.debug("getting AspectRatio instance with id: " + id);
        try
        {
            AspectRatio instance = entityManager.find(AspectRatio.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
