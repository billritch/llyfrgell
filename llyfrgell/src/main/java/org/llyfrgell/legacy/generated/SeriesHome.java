package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Series.
 * @see llyfrgel.Series
 * @author Hibernate Tools
 */
@Stateless
public class SeriesHome
{

    private static final Log log = LogFactory.getLog(SeriesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Series transientInstance)
    {
        log.debug("persisting Series instance");
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

    public void remove(Series persistentInstance)
    {
        log.debug("removing Series instance");
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

    public Series merge(Series detachedInstance)
    {
        log.debug("merging Series instance");
        try
        {
            Series result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Series findById(Integer id)
    {
        log.debug("getting Series instance with id: " + id);
        try
        {
            Series instance = entityManager.find(Series.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
