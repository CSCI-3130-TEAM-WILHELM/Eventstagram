package com.vaadin.tutorial.addressbook.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.

//CHANGES 1


public class EventService {

    // Create dummy data by randomly combining first and last names
    static String[] eventnames = { "Grateful Dead", "Blackeyed Peas", "Metallica", "Led Zepplin", "The Beatles",
    							   "Green Day", "Blink-182", "Radiohead", "AC/DC", "Pearl Jam",
    							   "Megadeth", "Nickleback", "Blackstreet", "Limp Bizkit", "Steve Tyler",
    							   "Duran Duran", "Guns 'N Roses", "Sex Pistols", "U2", "The Smashing Pumpkins" };
    static String[] lnames = { "Smith", "Johnson", "Williams", "Jones",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Young", "King", "Robinson" };

    private static EventService instance;

    public static EventService createDemoService() {
        if (instance == null) {

            final EventService eventService = new EventService();

            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 20; i++) {
                Event event = new Event();
                event.setTitle(eventnames[i]);
                event.setStart(cal.getTime());
                event.setEnd(cal.getTime());
                eventService.save(event);
            }
            instance = eventService;
        }

        return instance;
    }

    private HashMap<Long, Event> events = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Contact> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Event event : events.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || event.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(event.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(EventService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Event>() {

            @Override
            public int compare(Event o1, Event o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return events.size();
    }

    public synchronized void delete(Event value) {
        events.remove(value.getId());
    }

    public synchronized void save(Event entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Event) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        events.put(entry.getId(), entry);
    }


}
