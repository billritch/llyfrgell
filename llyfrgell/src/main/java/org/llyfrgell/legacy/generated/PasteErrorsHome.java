package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class PasteErrors.
 * @see llyfrgel.PasteErrors
 * @author Hibernate Tools
 */
@Stateless
public class PasteErrorsHome
{

    private static final Log log = LogFactory.getLog(PasteErrorsHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(PasteErrors transientInstance)
    {
        log.debug("persisting PasteErrors instance");
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

    public void remove(PasteErrors persistentInstance)
    {
        log.debug("removing PasteErrors instance");
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

    public PasteErrors merge(PasteErrors detachedInstance)
    {
        log.debug("merging PasteErrors instance");
        try
        {
            PasteErrors result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public PasteErrors findById(String id)
    {
        log.debug("getting PasteErrors instance with id: " + id);
        try
        {
            PasteErrors instance = entityManager.find(PasteErrors.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
