package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:28 PM by Hibernate Tools 4.0.0

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Colour.
 * @see llyfrgel.Colour
 * @author Hibernate Tools
 */
@Stateless
public class ColourHome
{

    private static final Log log = LogFactory.getLog(ColourHome.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Colour transientInstance)
    {
        log.debug("persisting Colour instance");
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

    public void remove(Colour persistentInstance)
    {
        log.debug("removing Colour instance");
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

    public Colour merge(Colour detachedInstance)
    {
        log.debug("merging Colour instance");
        try
        {
            Colour result = entityManager.merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public Colour findById(Integer id)
    {
        log.debug("getting Colour instance with id: " + id);
        try
        {
            Colour instance = entityManager.find(Colour.class, id);
            log.debug("get successful");
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
}
