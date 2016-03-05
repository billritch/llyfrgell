package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class DoctorWhoUpdates.
 * @see llyfrgel.DoctorWhoUpdates
 * @author Hibernate Tools
 */
@Stateless
public class DoctorWhoUpdatesHome
{

    private static final Log log = LogFactory
            .getLog(DoctorWhoUpdatesHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(DoctorWhoUpdates transientInstance)
    {
        log.debug("persisting DoctorWhoUpdates instance");
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

    public void remove(DoctorWhoUpdates persistentInstance)
    {
        log.debug("removing DoctorWhoUpdates instance");
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

    public DoctorWhoUpdates merge(DoctorWhoUpdates detachedInstance)
    {
        log.debug("merging DoctorWhoUpdates instance");
        try
        {
            DoctorWhoUpdates result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public DoctorWhoUpdates findById(Integer id)
    {
        log.debug("getting DoctorWhoUpdates instance with id: " + id);
        try
        {
            DoctorWhoUpdates instance = entityManager.find(
                    DoctorWhoUpdates.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
