package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Duty.
 * @see llyfrgel.Duty
 * @author Hibernate Tools
 */
@Stateless
public class DutyHome
{

    private static final Log log = LogFactory.getLog(DutyHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Duty transientInstance)
    {
        log.debug("persisting Duty instance");
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

    public void remove(Duty persistentInstance)
    {
        log.debug("removing Duty instance");
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

    public Duty merge(Duty detachedInstance)
    {
        log.debug("merging Duty instance");
        try
        {
            Duty result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Duty findById(Integer id)
    {
        log.debug("getting Duty instance with id: " + id);
        try
        {
            Duty instance = entityManager.find(Duty.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
