package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class ContentsL.
 * @see llyfrgel.ContentsL
 * @author Hibernate Tools
 */
@Stateless
public class ContentsLHome
{

    private static final Log log = LogFactory.getLog(ContentsLHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(ContentsL transientInstance)
    {
        log.debug("persisting ContentsL instance");
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

    public void remove(ContentsL persistentInstance)
    {
        log.debug("removing ContentsL instance");
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

    public ContentsL merge(ContentsL detachedInstance)
    {
        log.debug("merging ContentsL instance");
        try
        {
            ContentsL result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public ContentsL findById(Integer id)
    {
        log.debug("getting ContentsL instance with id: " + id);
        try
        {
            ContentsL instance = entityManager.find(ContentsL.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
