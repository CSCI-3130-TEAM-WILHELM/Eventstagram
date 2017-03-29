package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

//import com.vaadin.tutorial.eventstagram.backend.Event;

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
public class OurEventService {
	
//	EntityManagerFactory emf = Persistence.createEntityManagerFactory("newPU");

    // Create dummy data by randomly combining first and last names
    static String[] eventnames = { "Grateful Dead", "Blackeyed Peas", "Metallica", "Led Zepplin", "The Beatles",
    							   "Green Day", "Blink-182", "Radiohead", "AC/DC", "Pearl Jam",
    							   "Megadeth", "Nickleback", "Blackstreet", "Limp Bizkit", "Steve Tyler",
    							   "Duran Duran", "Guns 'N Roses", "Sex Pistols", "U2", "The Smashing Pumpkins" };
    static String[] eventdescriptions = { "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nibh mauris, commodo non eros eget, auctor molestie nibh. Nullam quis dui viverra, interdum felis egestas, convallis elit.",
    									  "Aenean eget facilisis risus, sed maximus tortor. In quis rutrum lacus. Suspendisse elementum tristique lacus, sit amet ullamcorper sapien tempus et.",
    									  "Quisque ullamcorper orci nec quam volutpat, ut porttitor leo venenatis. Phasellus hendrerit semper purus, sit amet pulvinar leo scelerisque tempus.",
    									  "Etiam et nunc sapien. Phasellus quis commodo lectus. In auctor eros vel tempus rutrum. Ut ex purus, varius non luctus id, blandit sed lectus.",
                                          "Nunc cursus viverra tortor, quis ultrices nulla ultrices a. Duis sapien nunc, tincidunt eu elementum nec, tincidunt id leo.", 
                                          "Praesent et ipsum et dolor pretium mollis eu ac velit. Maecenas urna ante, eleifend ac est a, tempus finibus eros. ", 
                                          "Fusce id varius purus. Aliquam pharetra massa eros, id mattis quam pharetra quis. Cras est turpis, malesuada id ultrices eleifend, consequat ac lacus.", 
                                          "Mauris at efficitur nibh, quis egestas diam. Quisque nec justo non ligula convallis aliquet. Phasellus accumsan imperdiet orci sed tempus.", 
                                          "Pellentesque a ultrices mi. Nam commodo, mi vitae tincidunt consectetur, ipsum massa finibus nunc, sit amet aliquet urna metus id felis.", 
                                          "Sed sit amet mauris sit amet magna fringilla finibus id in metus. In pulvinar, arcu in accumsan laoreet, enim ex interdum leo, vitae lobortis urna dui in ipsum.",
                                          "Etiam pellentesque urna a congue commodo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis diam eget orci pretium pharetra. Sed in scelerisque urna. ", 
                                          "Curabitur quis magna sed quam fermentum tincidunt. Aenean efficitur ligula vitae elit auctor commodo. Integer congue nulla leo, quis tincidunt sem vehicula nec.", 
                                          "Sed eu diam ac massa pellentesque faucibus. Quisque dolor nulla, imperdiet ullamcorper metus quis, vehicula convallis nisl. Phasellus risus mauris, tristique id accumsan eu, rutrum nec purus.", 
                                          "Integer id ullamcorper dolor. Suspendisse in nunc metus. Integer varius augue nulla, nec mollis leo bibendum ut. Suspendisse potenti.", 
                                         "Sed sagittis neque odio, pretium consequat felis tincidunt eu. Nam mattis sem id dui condimentum vestibulum. Nunc et massa mi.", 
                                          "Vivamus eu leo luctus, faucibus magna in, cursus libero. Suspendisse ullamcorper, nunc a vestibulum tristique, purus orci mattis mauris, ut sagittis leo justo sit amet nisi.",
                                          "Sed id vulputate libero. Maecenas eu ullamcorper velit, et faucibus elit. Ut quis lobortis elit. Aenean porta id sapien egestas volutpat.", 
                                          "Cras lacinia, ex vitae pellentesque pharetra, felis tellus maximus eros, a ultrices arcu nisi nec lectus.", 
                                          "Vestibulum egestas, lectus sit amet molestie consectetur, nibh tellus cursus mauris, ut imperdiet neque purus vitae magna. Vestibulum sit amet urna eu libero facilisis tempor nec eu nulla.", 
                                          "Donec a nulla faucibus, maximus ligula nec, egestas augue. Proin pharetra ipsum sit amet ligula volutpat, et imperdiet nunc commodo." };

    private static OurEventService ourinstance;

    public static OurEventService createDemoService() {
        if (ourinstance == null) {

            final OurEventService ourEventService = new OurEventService();

            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 20; i++) {
                cal.set(Calendar.HOUR_OF_DAY, 18);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                OurEvent ourEvent = new OurEvent();
                ourEvent.setTitle(eventnames[i]);
                ourEvent.setOpen(cal.getTime());
                cal.add(Calendar.HOUR, 2);
                ourEvent.setStart(cal.getTime());
                cal.add(Calendar.HOUR, 4);
                ourEvent.setEnd(cal.getTime());
                ourEvent.setDescription(eventdescriptions[i]);
                ourEvent.setAttending(0);
                ourEvent.setInterested(0);
                ourEventService.save(ourEvent);
                cal.add(Calendar.DATE, 12);
            }
            ourinstance = ourEventService;
        }

        return ourinstance;
    }

    private HashMap<Long, OurEvent> events = new HashMap<>();
    private long nextId = 0;

//    public synchronized List<? extends com.vaadin.ui.Component.Event> findAll(String stringFilter) {
    public synchronized List<OurEvent> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (OurEvent ourEvent : events.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || ourEvent.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(ourEvent.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(OurEventService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<OurEvent>() {

            @Override
            public int compare(OurEvent o1, OurEvent o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return events.size();
    }

    public synchronized void delete(OurEvent value) {
        events.remove(value.getId());
    }

    public synchronized void save(OurEvent entry) {
        if (entry.getId() == -1) {
            entry.setId(nextId++);
        }
        try {
            entry = (OurEvent) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        events.put(entry.getId(), entry);
    }


}
