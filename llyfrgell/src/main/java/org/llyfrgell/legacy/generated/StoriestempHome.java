package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Storiestemp.
 * @see llyfrgel.Storiestemp
 * @author Hibernate Tools
 */
@Stateless
public class StoriestempHome
{

    private static final Log log = LogFactory.getLog(StoriestempHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Storiestemp transientInstance)
    {
        log.debug("persisting Storiestemp instance");
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

    public void remove(Storiestemp persistentInstance)
    {
        log.debug("removing Storiestemp instance");
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

    public Storiestemp merge(Storiestemp detachedInstance)
    {
        log.debug("merging Storiestemp instance");
        try
        {
            Storiestemp result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Storiestemp findById(Integer id)
    {
        log.debug("getting Storiestemp instance with id: " + id);
        try
        {
            Storiestemp instance = entityManager.find(Storiestemp.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
