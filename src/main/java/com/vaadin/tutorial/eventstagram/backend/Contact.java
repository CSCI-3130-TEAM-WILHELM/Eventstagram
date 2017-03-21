package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.Id;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */
// Backend DTO class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.

//For information on @entity and @Id and stuff, visit http://www.objectdb.com/java/jpa/entity/types
//

//@Entity
public class Contact implements Serializable, Cloneable {

//	@Id
    private Long id;
    private String event = "";

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getEvent()
    {
        return event;
    }

    public void setEvent(String event)
    {
        this.event = event;
    }

    @Override
    public Contact clone() throws CloneNotSupportedException {
        try {
            return (Contact) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString()
    {
        return "Contact{" + "id=" + id + ", event = " + event + '}';
    }

}
