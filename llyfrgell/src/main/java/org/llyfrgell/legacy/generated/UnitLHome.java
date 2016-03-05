package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UnitL.
 * @see llyfrgel.UnitL
 * @author Hibernate Tools
 */
@Stateless
public class UnitLHome
{

    private static final Log log = LogFactory.getLog(UnitLHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(UnitL transientInstance)
    {
        log.debug("persisting UnitL instance");
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

    public void remove(UnitL persistentInstance)
    {
        log.debug("removing UnitL instance");
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

    public UnitL merge(UnitL detachedInstance)
    {
        log.debug("merging UnitL instance");
        try
        {
            UnitL result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UnitL findById(Integer id)
    {
        log.debug("getting UnitL instance with id: " + id);
        try
        {
            UnitL instance = entityManager.find(UnitL.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
