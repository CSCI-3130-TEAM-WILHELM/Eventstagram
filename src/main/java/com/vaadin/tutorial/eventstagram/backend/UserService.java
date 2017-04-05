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

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.

public class UserService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EventstagramDB");

    // Create dummy data by randomly combining first and last names
    static String[] usernames = { "Peter", "Aliee", "John","Mike", "Olivia",
            "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa",
            "Linda", "Timothy", "Daniel", "Brian", "George", "Scott",
            "Jennifer" };
    static String[] passwords = { "Smith", "Johnson", "Williams", "Jones",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Young", "King", "Robinson"};
    static boolean[] admins = {true, false,false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    
    private static UserService instance;
    

    public static UserService createDemoService() {

        if (instance == null) {

            final UserService userService = new UserService();

            for (int i = 0; i < 20; i++) {
                User user = new User();
                user.setUsername(usernames[i]);
                user.setPassword(passwords[i]);
                user.setAdmin(admins[i]);
                userService.save(user);
            }
            instance = userService;
        }

        return instance;
    }

    private HashMap<Long, User> users = new HashMap<>();
    private long nextId = 0;

    //old header = public synchronized List<User> findAll(String stringFilter) {
    
    public synchronized User find (User user){
    	EntityManager em = getEntityManager();
    	
    	//get user with matching username from database
    	User returnedUser=em.find(User.class, user.getUsername());
    	
    	//if it doesn't exist, return null
    	if(returnedUser == null){
    		System.err.println("User "+user.getUsername()+" was not found in database");
    		return null;
    	}
    	
    	return returnedUser;
    	
    		
    }
    /*
    public synchronized List<User> findAll(String stringFilter) {
    	 EntityManager em = getEntityManager();
         try {
             CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
             cq.select(cq.from(User.class));
             Query q = em.createQuery(cq);
             System.out.println(q.toString());
             return q.getResultList();
         } finally {
             em.close();
         }
    	
    	/*ArrayList<User> arrayList = new ArrayList<User>();
        for (User contact : users.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UserService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList; (*)/
    }*/

    public synchronized long count() {
    	EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OurLocation> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //not working, d
    /*public synchronized void delete(User value) {
    	EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(value.getUsername());
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }*/
    
    public synchronized void updatePassword(User value) {
    	EntityManager em = null;
        try {
            em = getEntityManager();
            User user=em.find(User.class, value.getUsername());
            em.getTransaction().begin();
            user.setPassword(value.getPassword());
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public synchronized void save(User entry) {
    	EntityManager em = getEntityManager();
        try {
        	
        	//check if user already exists
        	if(em.find(User.class, entry.getUsername()) == null){
        		System.out.println("Object added to database:"+entry.getUsername());
        		em.getTransaction().begin();
            	em.persist(entry);
            	em.getTransaction().commit();
        	}
        	else{
        		System.err.println("Object already exists in database.");
        	}
        	
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally{
            em.close();
        }
    }
    private EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

}
