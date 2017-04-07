package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */
// Backend DTO class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
//

@Entity(name = "User")
public class User implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
    //private Long id;
	
    private String username = "";
    private String password = "";
    private boolean admin = false;
    
    private List<Long> interestedList = new ArrayList<Long>();
    private List<Long> attendingList = new ArrayList<Long>();

    /*public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }*/

    public String getUsername()
    {
        return username;
    }  
    public void addInterestedEvent(OurEvent event){
    	interestedList.add(event.getId());
    }
    public void addAttendingEvent(OurEvent event){
    	attendingList.add(event.getId());
    }
    public void removeInterestedEvent(OurEvent event){
    	interestedList.remove(event.getId());
    }
    public void removeAttendingEvent(OurEvent event){
    	attendingList.remove(event.getId());
    }
    public List<Long> getAttendingList(){
    	return attendingList;
    }
    public List<Long> getInterestedList(){
    	return interestedList;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public boolean getAdmin() {
    	return admin;
    }
    
    public void setAdmin(boolean admin){
    	this.admin = admin;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        try {
            return (User) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString()
    {
    	return "Contact{" + ", User = " + username + '}';
        //return "Contact{" + "id=" + id + ", User = " + username + '}';
    }

}
