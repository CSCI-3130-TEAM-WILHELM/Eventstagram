package com.vaadin.tutorial.eventstagram.backend;

import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class LocationService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventstagramDB");

    // Create dummy data by randomly combining first and last names
    static String[] venues = { "Peter", "Aliee", "John", "Mike", "Olivia",
            "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa",
            "Linda", "Timothy", "Daniel", "Brian", "George", "Scott",
            "Jennifer" };
    static String[] addresses = { "Smith", "Johnson", "Williams", "Jones",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Young", "King", "Robinson" };
    
    private static LocationService instance;
    
    public static LocationService createDemoService() {

        if (instance == null) {

            final LocationService locationService = new LocationService();
            
            instance = locationService;
        }

        return instance;
    }

    public synchronized List<OurLocation> findAll(String stringFilter) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OurLocation.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public synchronized List<OurLocation> getAll (){
    	EntityManager em = getEntityManager();
    	List<OurLocation> results = em
                .createQuery("Select a from OurLocation a", OurLocation.class)
                .getResultList();
    	return results;
    }

    public synchronized long count() {
       EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
            Root<OurLocation> rt = cq.from(OurLocation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public synchronized void delete(OurLocation value) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(value);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public synchronized void save(OurLocation entry) {
        EntityManager em = getEntityManager();
        try {
          
            em.getTransaction().begin();
            em.persist(entry);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }finally
        {
            em.close();
        }
    }
    public synchronized void update(OurLocation entry) {
        EntityManager em = getEntityManager();
        
        try {
            OurLocation ourLocation = em.find(OurLocation.class, entry.getId());
            em.getTransaction().begin();
            ourLocation.setVenue(entry.getVenue());
            ourLocation.setAddress(entry.getAddress());
            ourLocation.setCity(entry.getCity());
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }finally
        {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

}
