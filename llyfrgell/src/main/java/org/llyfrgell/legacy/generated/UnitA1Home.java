package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UnitA1.
 * @see llyfrgel.UnitA1
 * @author Hibernate Tools
 */
@Stateless
public class UnitA1Home
{

    private static final Log log = LogFactory.getLog(UnitA1Home.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(UnitA1 transientInstance)
    {
        log.debug("persisting UnitA1 instance");
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

    public void remove(UnitA1 persistentInstance)
    {
        log.debug("removing UnitA1 instance");
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

    public UnitA1 merge(UnitA1 detachedInstance)
    {
        log.debug("merging UnitA1 instance");
        try
        {
            UnitA1 result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UnitA1 findById(int id)
    {
        log.debug("getting UnitA1 instance with id: " + id);
        try
        {
            UnitA1 instance = entityManager.find(UnitA1.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
