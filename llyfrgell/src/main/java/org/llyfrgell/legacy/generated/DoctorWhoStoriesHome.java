package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class DoctorWhoStories.
 * @see llyfrgel.DoctorWhoStories
 * @author Hibernate Tools
 */
@Stateless
public class DoctorWhoStoriesHome
{

    private static final Log log = LogFactory
            .getLog(DoctorWhoStoriesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(DoctorWhoStories transientInstance)
    {
        log.debug("persisting DoctorWhoStories instance");
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

    public void remove(DoctorWhoStories persistentInstance)
    {
        log.debug("removing DoctorWhoStories instance");
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

    public DoctorWhoStories merge(DoctorWhoStories detachedInstance)
    {
        log.debug("merging DoctorWhoStories instance");
        try
        {
            DoctorWhoStories result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public DoctorWhoStories findById(Integer id)
    {
        log.debug("getting DoctorWhoStories instance with id: " + id);
        try
        {
            DoctorWhoStories instance = entityManager.find(
                    DoctorWhoStories.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
