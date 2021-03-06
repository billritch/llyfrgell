package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Unit.
 * @see llyfrgel.Unit
 * @author Hibernate Tools
 */
@Stateless
public class UnitHome
{

    private static final Log log = LogFactory.getLog(UnitHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Unit transientInstance)
    {
        log.debug("persisting Unit instance");
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

    public void remove(Unit persistentInstance)
    {
        log.debug("removing Unit instance");
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

    public Unit merge(Unit detachedInstance)
    {
        log.debug("merging Unit instance");
        try
        {
            Unit result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Unit findById(Integer id)
    {
        log.debug("getting Unit instance with id: " + id);
        try
        {
            Unit instance = entityManager.find(Unit.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
