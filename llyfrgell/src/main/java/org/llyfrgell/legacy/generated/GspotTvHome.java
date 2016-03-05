package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class GspotTv.
 * @see llyfrgel.GspotTv
 * @author Hibernate Tools
 */
@Stateless
public class GspotTvHome
{

    private static final Log log = LogFactory.getLog(GspotTvHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(GspotTv transientInstance)
    {
        log.debug("persisting GspotTv instance");
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

    public void remove(GspotTv persistentInstance)
    {
        log.debug("removing GspotTv instance");
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

    public GspotTv merge(GspotTv detachedInstance)
    {
        log.debug("merging GspotTv instance");
        try
        {
            GspotTv result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public GspotTv findById(Integer id)
    {
        log.debug("getting GspotTv instance with id: " + id);
        try
        {
            GspotTv instance = entityManager.find(GspotTv.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
