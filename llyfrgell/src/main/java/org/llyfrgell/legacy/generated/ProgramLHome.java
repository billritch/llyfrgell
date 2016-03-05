package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ProgramL.
 * @see llyfrgel.ProgramL
 * @author Hibernate Tools
 */
@Stateless
public class ProgramLHome
{

    private static final Log log = LogFactory.getLog(ProgramLHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(ProgramL transientInstance)
    {
        log.debug("persisting ProgramL instance");
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

    public void remove(ProgramL persistentInstance)
    {
        log.debug("removing ProgramL instance");
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

    public ProgramL merge(ProgramL detachedInstance)
    {
        log.debug("merging ProgramL instance");
        try
        {
            ProgramL result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ProgramL findById(Integer id)
    {
        log.debug("getting ProgramL instance with id: " + id);
        try
        {
            ProgramL instance = entityManager.find(ProgramL.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
