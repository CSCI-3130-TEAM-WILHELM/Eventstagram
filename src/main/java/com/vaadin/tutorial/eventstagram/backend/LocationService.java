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
    static String[] cities = { "Halifax", "Halifax", "Halifax", "Halifax",
            "Dartmouth", "Dartmouth", "Halifax", "Halifax", "Halifax", "Dartmouth",
            "Bedford", "Halifax", "Dartmouth", "Halifax", "Halifax", "Halifax",
            "Halifax", "Halifax", "Dartmouth", "Halifax" };

    
    private static LocationService instance;

    public static LocationService createDemoService() {
        if (instance == null) {

            final LocationService locationService = new LocationService();

            for (int i = 0; i < 20; i++) {
                Location location = new Location();
                location.setVenue(venues[i]);
                location.setAddress(addresses[i]);
                location.setCity(cities[i]);
                locationService.save(location);
            }
            instance = locationService;
        }

        return instance;
    }

    private HashMap<Long, Location> locations = new HashMap<>();
    private long nextId = 0;

    //old header = public synchronized List<User> findAll(String stringFilter) {
    public synchronized ArrayList<Location> findAll(String stringFilter) {
        ArrayList<Location> arrayList = new ArrayList<Location>();
        for (Location location : locations.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || location.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(location.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(LocationService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Location>() {

            @Override
            public int compare(Location o1, Location o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return locations.size();
    }

    public synchronized void delete(Location value) {
        locations.remove(value.getId());
    }

    public synchronized void save(Location entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Location) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        locations.put(entry.getId(), entry);
    }

}
