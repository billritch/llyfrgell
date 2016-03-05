package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class GspotRecording.
 * @see llyfrgel.GspotRecording
 * @author Hibernate Tools
 */
@Stateless
public class GspotRecordingHome
{

    private static final Log log = LogFactory.getLog(GspotRecordingHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(GspotRecording transientInstance)
    {
        log.debug("persisting GspotRecording instance");
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

    public void remove(GspotRecording persistentInstance)
    {
        log.debug("removing GspotRecording instance");
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

    public GspotRecording merge(GspotRecording detachedInstance)
    {
        log.debug("merging GspotRecording instance");
        try
        {
            GspotRecording result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public GspotRecording findById(Integer id)
    {
        log.debug("getting GspotRecording instance with id: " + id);
        try
        {
            GspotRecording instance = entityManager.find(GspotRecording.class,
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
