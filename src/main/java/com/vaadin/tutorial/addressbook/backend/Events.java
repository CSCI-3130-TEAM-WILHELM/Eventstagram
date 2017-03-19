package com.vaadin.tutorial.addressbook.backend;

import org.apache.commons.beanutils.BeanUtils;

import com.vaadin.ui.Image;

import java.io.Serializable;
import java.util.Date;

public class Events implements Serializable, Cloneable {

private int id;   
private String title;
private Date releaseDate;
private Date open ;
private Date start;
private int locationId;
private String description;
private Image image;


public Events()
{
	
}

public int getId()
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

public int getLocationId()
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

public void setId(int id)
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

public void setLocationId(int locationId)
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


}
