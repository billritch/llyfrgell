package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RecordingFormat.
 * @see llyfrgel.RecordingFormat
 * @author Hibernate Tools
 */
@Stateless
public class RecordingFormatHome
{

    private static final Log log = LogFactory.getLog(RecordingFormatHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(RecordingFormat transientInstance)
    {
        log.debug("persisting RecordingFormat instance");
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

    public void remove(RecordingFormat persistentInstance)
    {
        log.debug("removing RecordingFormat instance");
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

    public RecordingFormat merge(RecordingFormat detachedInstance)
    {
        log.debug("merging RecordingFormat instance");
        try
        {
            RecordingFormat result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public RecordingFormat findById(Integer id)
    {
        log.debug("getting RecordingFormat instance with id: " + id);
        try
        {
            RecordingFormat instance = entityManager.find(
                    RecordingFormat.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
