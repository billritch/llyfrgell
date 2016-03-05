package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Freddythepig.
 * @see llyfrgel.Freddythepig
 * @author Hibernate Tools
 */
@Stateless
public class FreddythepigHome
{

    private static final Log log = LogFactory.getLog(FreddythepigHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Freddythepig transientInstance)
    {
        log.debug("persisting Freddythepig instance");
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

    public void remove(Freddythepig persistentInstance)
    {
        log.debug("removing Freddythepig instance");
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

    public Freddythepig merge(Freddythepig detachedInstance)
    {
        log.debug("merging Freddythepig instance");
        try
        {
            Freddythepig result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Freddythepig findById(FreddythepigId id)
    {
        log.debug("getting Freddythepig instance with id: " + id);
        try
        {
            Freddythepig instance = entityManager.find(Freddythepig.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
