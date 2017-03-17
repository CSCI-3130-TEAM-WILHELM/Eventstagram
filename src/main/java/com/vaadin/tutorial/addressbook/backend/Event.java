package com.vaadin.tutorial.addressbook.backend;

import org.apache.commons.beanutils.BeanUtils;

import com.vaadin.ui.Image;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event implements Serializable, Cloneable {

	
@Id
private long id;
//Unique event ID
private String title;						// Title	
private Date releaseDate;					// Date to be published
private Date open ;							//Doors open Time
private Date start;							//Event start Time
private long locationId;					//Location ID
private String description;					//Event Description
private Image image;						//Image


public Event()
{
	
}

public long getId()
{
	return id;
}

public String getTitle()
{
	return title;
}

public Date getReleaseDate()
{
	return releaseDate;
}

public Date getOpen()
{
	return open;
}

public Date getStart()
{
	return start;
}

public long getLocationId()
{
	return locationId;
}

public String getDescription()
{
	return description;
}

public Image getImage()
{
	return image;
}

public void setId(long id)
{
	this.id=id;
}

public void setTitle(String title)
{
	this.title=title;
}

public void setReleaseDates(Date releaseDate)
{
	this.releaseDate=releaseDate;
}

public void setOpen(Date open)
{
	this.open=open;
}

public void setStart(Date start)
{
	this.start=start;
}

public void setLocationId(long locationId)
{
	this.locationId=locationId;
}

public void setDescription(String description)
{
	this.description=description;
}

public void setImage(Image image)
{
	this.image=image;
}

@Override
public String toString()
{
    return "Event{" + "id=" + id + ", Title = " + title + '}';
}

}
