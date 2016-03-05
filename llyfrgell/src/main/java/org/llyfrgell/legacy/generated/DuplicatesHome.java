package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Duplicates.
 * @see llyfrgel.Duplicates
 * @author Hibernate Tools
 */
@Stateless
public class DuplicatesHome
{

    private static final Log log = LogFactory.getLog(DuplicatesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Duplicates transientInstance)
    {
        log.debug("persisting Duplicates instance");
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

    public void remove(Duplicates persistentInstance)
    {
        log.debug("removing Duplicates instance");
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

    public Duplicates merge(Duplicates detachedInstance)
    {
        log.debug("merging Duplicates instance");
        try
        {
            Duplicates result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Duplicates findById(DuplicatesId id)
    {
        log.debug("getting Duplicates instance with id: " + id);
        try
        {
            Duplicates instance = entityManager.find(Duplicates.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
