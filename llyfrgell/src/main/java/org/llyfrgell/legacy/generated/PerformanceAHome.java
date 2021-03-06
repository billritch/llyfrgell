package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PerformanceA.
 * @see llyfrgel.PerformanceA
 * @author Hibernate Tools
 */
@Stateless
public class PerformanceAHome
{

    private static final Log log = LogFactory.getLog(PerformanceAHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(PerformanceA transientInstance)
    {
        log.debug("persisting PerformanceA instance");
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

    public void remove(PerformanceA persistentInstance)
    {
        log.debug("removing PerformanceA instance");
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

    public PerformanceA merge(PerformanceA detachedInstance)
    {
        log.debug("merging PerformanceA instance");
        try
        {
            PerformanceA result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public PerformanceA findById(Integer id)
    {
        log.debug("getting PerformanceA instance with id: " + id);
        try
        {
            PerformanceA instance = entityManager.find(PerformanceA.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
