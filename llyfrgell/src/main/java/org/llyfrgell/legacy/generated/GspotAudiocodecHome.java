package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class GspotAudiocodec.
 * @see llyfrgel.GspotAudiocodec
 * @author Hibernate Tools
 */
@Stateless
public class GspotAudiocodecHome
{

    private static final Log log = LogFactory.getLog(GspotAudiocodecHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(GspotAudiocodec transientInstance)
    {
        log.debug("persisting GspotAudiocodec instance");
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

    public void remove(GspotAudiocodec persistentInstance)
    {
        log.debug("removing GspotAudiocodec instance");
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

    public GspotAudiocodec merge(GspotAudiocodec detachedInstance)
    {
        log.debug("merging GspotAudiocodec instance");
        try
        {
            GspotAudiocodec result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public GspotAudiocodec findById(Integer id)
    {
        log.debug("getting GspotAudiocodec instance with id: " + id);
        try
        {
            GspotAudiocodec instance = entityManager.find(
                    GspotAudiocodec.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
