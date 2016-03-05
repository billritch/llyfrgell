package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Temp.
 * @see llyfrgel.Temp
 * @author Hibernate Tools
 */
@Stateless
public class TempHome
{

    private static final Log log = LogFactory.getLog(TempHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Temp transientInstance)
    {
        log.debug("persisting Temp instance");
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

    public void remove(Temp persistentInstance)
    {
        log.debug("removing Temp instance");
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

    public Temp merge(Temp detachedInstance)
    {
        log.debug("merging Temp instance");
        try
        {
            Temp result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Temp findById(TempId id)
    {
        log.debug("getting Temp instance with id: " + id);
        try
        {
            Temp instance = entityManager.find(Temp.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
