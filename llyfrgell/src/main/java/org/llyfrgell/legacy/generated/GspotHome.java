package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Gspot.
 * @see llyfrgel.Gspot
 * @author Hibernate Tools
 */
@Stateless
public class GspotHome
{

    private static final Log log = LogFactory.getLog(GspotHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Gspot transientInstance)
    {
        log.debug("persisting Gspot instance");
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

    public void remove(Gspot persistentInstance)
    {
        log.debug("removing Gspot instance");
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

    public Gspot merge(Gspot detachedInstance)
    {
        log.debug("merging Gspot instance");
        try
        {
            Gspot result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Gspot findById(Integer id)
    {
        log.debug("getting Gspot instance with id: " + id);
        try
        {
            Gspot instance = entityManager.find(Gspot.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
