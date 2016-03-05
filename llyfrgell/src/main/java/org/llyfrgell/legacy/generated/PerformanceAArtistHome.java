package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PerformanceAArtist.
 * @see llyfrgel.PerformanceAArtist
 * @author Hibernate Tools
 */
@Stateless
public class PerformanceAArtistHome
{

    private static final Log log = LogFactory
            .getLog(PerformanceAArtistHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(PerformanceAArtist transientInstance)
    {
        log.debug("persisting PerformanceAArtist instance");
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

    public void remove(PerformanceAArtist persistentInstance)
    {
        log.debug("removing PerformanceAArtist instance");
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

    public PerformanceAArtist merge(PerformanceAArtist detachedInstance)
    {
        log.debug("merging PerformanceAArtist instance");
        try
        {
            PerformanceAArtist result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public PerformanceAArtist findById(PerformanceAArtistId id)
    {
        log.debug("getting PerformanceAArtist instance with id: " + id);
        try
        {
            PerformanceAArtist instance = entityManager.find(
                    PerformanceAArtist.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
