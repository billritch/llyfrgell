package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Id0.
 * @see llyfrgel.Id0
 * @author Hibernate Tools
 */
@Stateless
public class Id0Home
{

    private static final Log log = LogFactory.getLog(Id0Home.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Id0 transientInstance)
    {
        log.debug("persisting Id0 instance");
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

    public void remove(Id0 persistentInstance)
    {
        log.debug("removing Id0 instance");
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

    public Id0 merge(Id0 detachedInstance)
    {
        log.debug("merging Id0 instance");
        try
        {
            Id0 result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Id0 findById(Id0Id id)
    {
        log.debug("getting Id0 instance with id: " + id);
        try
        {
            Id0 instance = entityManager.find(Id0.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
