package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UnitA.
 * @see llyfrgel.UnitA
 * @author Hibernate Tools
 */
@Stateless
public class UnitAHome
{

    private static final Log log = LogFactory.getLog(UnitAHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(UnitA transientInstance)
    {
        log.debug("persisting UnitA instance");
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

    public void remove(UnitA persistentInstance)
    {
        log.debug("removing UnitA instance");
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

    public UnitA merge(UnitA detachedInstance)
    {
        log.debug("merging UnitA instance");
        try
        {
            UnitA result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UnitA findById(Integer id)
    {
        log.debug("getting UnitA instance with id: " + id);
        try
        {
            UnitA instance = entityManager.find(UnitA.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
