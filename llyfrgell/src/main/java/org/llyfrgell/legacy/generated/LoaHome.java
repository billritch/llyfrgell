package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Loa.
 * @see llyfrgel.Loa
 * @author Hibernate Tools
 */
@Stateless
public class LoaHome
{

    private static final Log log = LogFactory.getLog(LoaHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Loa transientInstance)
    {
        log.debug("persisting Loa instance");
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

    public void remove(Loa persistentInstance)
    {
        log.debug("removing Loa instance");
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

    public Loa merge(Loa detachedInstance)
    {
        log.debug("merging Loa instance");
        try
        {
            Loa result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Loa findById(Integer id)
    {
        log.debug("getting Loa instance with id: " + id);
        try
        {
            Loa instance = entityManager.find(Loa.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
