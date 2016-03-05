package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tvbooks.
 * @see llyfrgel.Tvbooks
 * @author Hibernate Tools
 */
@Stateless
public class TvbooksHome
{

    private static final Log log = LogFactory.getLog(TvbooksHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Tvbooks transientInstance)
    {
        log.debug("persisting Tvbooks instance");
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

    public void remove(Tvbooks persistentInstance)
    {
        log.debug("removing Tvbooks instance");
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

    public Tvbooks merge(Tvbooks detachedInstance)
    {
        log.debug("merging Tvbooks instance");
        try
        {
            Tvbooks result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Tvbooks findById(Integer id)
    {
        log.debug("getting Tvbooks instance with id: " + id);
        try
        {
            Tvbooks instance = entityManager.find(Tvbooks.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
