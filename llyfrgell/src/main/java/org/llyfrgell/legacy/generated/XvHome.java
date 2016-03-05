package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Xv.
 * @see llyfrgel.Xv
 * @author Hibernate Tools
 */
@Stateless
public class XvHome
{

    private static final Log log = LogFactory.getLog(XvHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Xv transientInstance)
    {
        log.debug("persisting Xv instance");
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

    public void remove(Xv persistentInstance)
    {
        log.debug("removing Xv instance");
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

    public Xv merge(Xv detachedInstance)
    {
        log.debug("merging Xv instance");
        try
        {
            Xv result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Xv findById(Integer id)
    {
        log.debug("getting Xv instance with id: " + id);
        try
        {
            Xv instance = entityManager.find(Xv.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
