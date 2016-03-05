package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Cut.
 * @see llyfrgel.Cut
 * @author Hibernate Tools
 */
@Stateless
public class CutHome
{

    private static final Log log = LogFactory.getLog(CutHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Cut transientInstance)
    {
        log.debug("persisting Cut instance");
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

    public void remove(Cut persistentInstance)
    {
        log.debug("removing Cut instance");
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

    public Cut merge(Cut detachedInstance)
    {
        log.debug("merging Cut instance");
        try
        {
            Cut result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Cut findById(Integer id)
    {
        log.debug("getting Cut instance with id: " + id);
        try
        {
            Cut instance = entityManager.find(Cut.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
