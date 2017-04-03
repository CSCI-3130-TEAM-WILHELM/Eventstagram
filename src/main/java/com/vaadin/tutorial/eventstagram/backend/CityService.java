package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CityService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventstagramDB");

    // Create dummy data by randomly combining first and last names
    static String[] cities = { "Halifax", "Halifax", "Halifax", "Halifax",
            "Dartmouth", "Dartmouth", "Halifax", "Halifax", "Halifax", "Dartmouth",
            "Bedford", "Halifax", "Dartmouth", "Halifax", "Halifax", "Halifax",
            "Halifax", "Halifax", "Dartmouth", "Halifax" };

    
    private static CityService instance;

    public static CityService createDemoService() {
        if (instance == null) {

            final CityService cityService = new CityService();

            for (int i = 0; i < 20; i++) {
                City city = new City();
                city.setName(cities[i]);
                city.setId((long) i);
                cityService.save(city);
            }
            instance = cityService;
        }

        return instance;
    }

    public synchronized List<City> findAll(String stringFilter) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(City.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public synchronized long count() {
       EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<City> rt = cq.from(City.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public synchronized void delete(City value) {
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

    public synchronized void save(City entry) {
        EntityManager em = getEntityManager();
        System.out.println("attempting to save" + entry.toString());
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
    public synchronized void update(City entry) {
        EntityManager em = getEntityManager();
        
        try {
            City city = em.find(City.class, entry.getId());
            em.getTransaction().begin();
            city.setName(entry.getName());
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
