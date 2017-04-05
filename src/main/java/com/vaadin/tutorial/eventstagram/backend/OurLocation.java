package com.vaadin.tutorial.eventstagram.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.vaadin.tutorial.eventstagram.backend.City;
import javax.persistence.ManyToOne;


@Entity(name = "Location")
public class OurLocation implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	
	private String venue = "";
	private String address = "";
	
	@ManyToOne
	private City city;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getVenue()
    {
        return venue;
    }

    public void setVenue(String venue)
    {
        this.venue = venue;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

/*    @Override
    public OurLocation clone() throws CloneNotSupportedException {
        try {
            return (OurLocation) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
*/
    @Override
    public String toString()
    {
        return "OurLocation{" + "id=" + id + ", venue = " + venue + ", address = " + address + ", city = " + city + "}";
    }

}
