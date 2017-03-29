package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.Date;

//import com.vaadin.ui.Image;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "OurEvent")
public class OurEvent implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) 
	private long id=-1;							//Unique event ID
    
	private String title="";					//Title	
	private Date releaseDate;					//Date to be published
	private Date open ;							//Doors open Time
	private Date start;							//Event start Time
	private Date end;							//Event end Time
	private long locationId;					//Location ID
	private String description="";				//Event Description
//	private Image image;						//Image
	private int attending;						//number of people attending the event
	private int interested;						//number of people interested in the event

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title=title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDates(Date releaseDate) {
		this.releaseDate=releaseDate;
	}

	public Date getOpen() {
		return open;
	}

	public void setOpen(Date open) {
		this.open=open;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start=start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end=end;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId=locationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description=description;
	}

	public int getAttending() {
		return attending;
	}
	public void setAttending(int attending){
		this.attending=attending;
	}
	public int getInterested() {
		return interested;
	}
	public void setInterested(int interested){
		this.interested=interested;
	}
//public Image getImage()
//{
//	return image;
//}


//public void setImage(Image image)
//{
//	this.image=image;
//}

@Override
	public OurEvent clone() throws CloneNotSupportedException {
		try {
			return (OurEvent) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}

@Override
	public String toString() {
		return "Event{" + "id=" + id + ", Title = " + title + '}';
	}

}