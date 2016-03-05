package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class UnitAContents.
 * @see llyfrgel.UnitAContents
 * @author Hibernate Tools
 */
@Stateless
public class UnitAContentsHome
{

    private static final Log log = LogFactory.getLog(UnitAContentsHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(UnitAContents transientInstance)
    {
        log.debug("persisting UnitAContents instance");
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

    public void remove(UnitAContents persistentInstance)
    {
        log.debug("removing UnitAContents instance");
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

    public UnitAContents merge(UnitAContents detachedInstance)
    {
        log.debug("merging UnitAContents instance");
        try
        {
            UnitAContents result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public UnitAContents findById(UnitAContentsId id)
    {
        log.debug("getting UnitAContents instance with id: " + id);
        try
        {
            UnitAContents instance = entityManager
                    .find(UnitAContents.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
