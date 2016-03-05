package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Booktemp.
 * @see llyfrgel.Booktemp
 * @author Hibernate Tools
 */
@Stateless
public class BooktempHome
{

    private static final Log log = LogFactory.getLog(BooktempHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Booktemp transientInstance)
    {
        log.debug("persisting Booktemp instance");
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

    public void remove(Booktemp persistentInstance)
    {
        log.debug("removing Booktemp instance");
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

    public Booktemp merge(Booktemp detachedInstance)
    {
        log.debug("merging Booktemp instance");
        try
        {
            Booktemp result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Booktemp findById(Integer id)
    {
        log.debug("getting Booktemp instance with id: " + id);
        try
        {
            Booktemp instance = entityManager.find(Booktemp.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
