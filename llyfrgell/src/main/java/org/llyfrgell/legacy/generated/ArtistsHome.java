package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Artists.
 * @see llyfrgel.Artists
 * @author Hibernate Tools
 */
@Stateless
public class ArtistsHome
{

    private static final Log log = LogFactory.getLog(ArtistsHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Artists transientInstance)
    {
        log.debug("persisting Artists instance");
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

    public void remove(Artists persistentInstance)
    {
        log.debug("removing Artists instance");
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

    public Artists merge(Artists detachedInstance)
    {
        log.debug("merging Artists instance");
        try
        {
            Artists result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Artists findById(Integer id)
    {
        log.debug("getting Artists instance with id: " + id);
        try
        {
            Artists instance = entityManager.find(Artists.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
