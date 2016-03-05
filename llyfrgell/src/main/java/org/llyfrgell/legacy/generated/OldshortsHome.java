package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Oldshorts.
 * @see llyfrgel.Oldshorts
 * @author Hibernate Tools
 */
@Stateless
public class OldshortsHome
{

    private static final Log log = LogFactory.getLog(OldshortsHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Oldshorts transientInstance)
    {
        log.debug("persisting Oldshorts instance");
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

    public void remove(Oldshorts persistentInstance)
    {
        log.debug("removing Oldshorts instance");
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

    public Oldshorts merge(Oldshorts detachedInstance)
    {
        log.debug("merging Oldshorts instance");
        try
        {
            Oldshorts result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Oldshorts findById(OldshortsId id)
    {
        log.debug("getting Oldshorts instance with id: " + id);
        try
        {
            Oldshorts instance = entityManager.find(Oldshorts.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
