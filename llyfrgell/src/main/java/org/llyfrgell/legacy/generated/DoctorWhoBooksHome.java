package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class DoctorWhoBooks.
 * @see llyfrgel.DoctorWhoBooks
 * @author Hibernate Tools
 */
@Stateless
public class DoctorWhoBooksHome
{

    private static final Log log = LogFactory.getLog(DoctorWhoBooksHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(DoctorWhoBooks transientInstance)
    {
        log.debug("persisting DoctorWhoBooks instance");
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

    public void remove(DoctorWhoBooks persistentInstance)
    {
        log.debug("removing DoctorWhoBooks instance");
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

    public DoctorWhoBooks merge(DoctorWhoBooks detachedInstance)
    {
        log.debug("merging DoctorWhoBooks instance");
        try
        {
            DoctorWhoBooks result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public DoctorWhoBooks findById(Integer id)
    {
        log.debug("getting DoctorWhoBooks instance with id: " + id);
        try
        {
            DoctorWhoBooks instance = entityManager.find(DoctorWhoBooks.class,
                    id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
