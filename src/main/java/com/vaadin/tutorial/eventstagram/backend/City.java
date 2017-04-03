package com.vaadin.tutorial.eventstagram.backend;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "City")
public class City implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToMany
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name = "";

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "City{" + "id=" + id + ", name = " + name + "}";
    }

}
