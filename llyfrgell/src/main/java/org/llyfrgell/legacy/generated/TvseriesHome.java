package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tvseries.
 * @see llyfrgel.Tvseries
 * @author Hibernate Tools
 */
@Stateless
public class TvseriesHome
{

    private static final Log log = LogFactory.getLog(TvseriesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Tvseries transientInstance)
    {
        log.debug("persisting Tvseries instance");
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

    public void remove(Tvseries persistentInstance)
    {
        log.debug("removing Tvseries instance");
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

    public Tvseries merge(Tvseries detachedInstance)
    {
        log.debug("merging Tvseries instance");
        try
        {
            Tvseries result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Tvseries findById(Integer id)
    {
        log.debug("getting Tvseries instance with id: " + id);
        try
        {
            Tvseries instance = entityManager.find(Tvseries.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
