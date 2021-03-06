package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class GspotTemp.
 * @see llyfrgel.GspotTemp
 * @author Hibernate Tools
 */
@Stateless
public class GspotTempHome
{

    private static final Log log = LogFactory.getLog(GspotTempHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(GspotTemp transientInstance)
    {
        log.debug("persisting GspotTemp instance");
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

    public void remove(GspotTemp persistentInstance)
    {
        log.debug("removing GspotTemp instance");
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

    public GspotTemp merge(GspotTemp detachedInstance)
    {
        log.debug("merging GspotTemp instance");
        try
        {
            GspotTemp result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public GspotTemp findById(Integer id)
    {
        log.debug("getting GspotTemp instance with id: " + id);
        try
        {
            GspotTemp instance = entityManager.find(GspotTemp.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
