package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UnitA2.
 * @see llyfrgel.UnitA2
 * @author Hibernate Tools
 */
@Stateless
public class UnitA2Home
{

    private static final Log log = LogFactory.getLog(UnitA2Home.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(UnitA2 transientInstance)
    {
        log.debug("persisting UnitA2 instance");
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

    public void remove(UnitA2 persistentInstance)
    {
        log.debug("removing UnitA2 instance");
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

    public UnitA2 merge(UnitA2 detachedInstance)
    {
        log.debug("merging UnitA2 instance");
        try
        {
            UnitA2 result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UnitA2 findById(Integer id)
    {
        log.debug("getting UnitA2 instance with id: " + id);
        try
        {
            UnitA2 instance = entityManager.find(UnitA2.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
