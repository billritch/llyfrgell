package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProgramA.
 * @see llyfrgel.ProgramA
 * @author Hibernate Tools
 */
@Stateless
public class ProgramAHome
{

    private static final Log log = LogFactory.getLog(ProgramAHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(ProgramA transientInstance)
    {
        log.debug("persisting ProgramA instance");
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

    public void remove(ProgramA persistentInstance)
    {
        log.debug("removing ProgramA instance");
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

    public ProgramA merge(ProgramA detachedInstance)
    {
        log.debug("merging ProgramA instance");
        try
        {
            ProgramA result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ProgramA findById(Integer id)
    {
        log.debug("getting ProgramA instance with id: " + id);
        try
        {
            ProgramA instance = entityManager.find(ProgramA.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
