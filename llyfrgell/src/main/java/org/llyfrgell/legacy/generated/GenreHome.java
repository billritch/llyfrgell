package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Genre.
 * @see llyfrgel.Genre
 * @author Hibernate Tools
 */
@Stateless
public class GenreHome
{

    private static final Log log = LogFactory.getLog(GenreHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Genre transientInstance)
    {
        log.debug("persisting Genre instance");
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

    public void remove(Genre persistentInstance)
    {
        log.debug("removing Genre instance");
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

    public Genre merge(Genre detachedInstance)
    {
        log.debug("merging Genre instance");
        try
        {
            Genre result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Genre findById(Integer id)
    {
        log.debug("getting Genre instance with id: " + id);
        try
        {
            Genre instance = entityManager.find(Genre.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
