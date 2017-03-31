package com.vaadin.tutorial.eventstagram;

//import java.util.ArrayList;

import com.vaadin.event.ShortcutAction;
import com.vaadin.tutorial.eventstagram.backend.OurLocation;
//import com.vaadin.tutorial.eventstagram.backend.LocationService;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
//import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.ui.TextField;

public class LocationForm extends FormLayout {

	private static final long serialVersionUID = 1L;
    private OurLocation ourLocation = new OurLocation();
	
    TextField venue = new TextField("Venue");
    TextField address = new TextField("Address");
    TextField city = new TextField("City");
    Button submit = new Button("Submit", this::submit);
    Button update = new Button("Update", this::update);
    Button cancel = new Button("Cancel", this::cancel);

    
    BeanFieldGroup<OurLocation> formFieldBindings;
    
    public LocationForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        submit.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	
        venue.setVisible(true);
        address.setVisible(true);
        city.setVisible(true);
        cancel.setVisible(true);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(submit, update,  cancel);
        actions.setSpacing(true);

        addComponents(venue, address, city, actions);
    }

    public void submit(Button.ClickEvent event){
		String msg = "";
        if (venue.getValue()==""){
        	msg="Please enter a name for this venue.";
        } else {
        	if (address.getValue()=="") {
        		msg="Please enter an address for this venue.";
        	} else {
        		if (city.getValue()=="") {
        			msg="Please enter a city for this venue.";
        		} else {
        	        try {
        	        	
        	            // Commit the fields from UI to DAO
//        	            formFieldBindings.commit();
        	            ourLocation.setVenue(this.venue.getValue());
        	            ourLocation.setAddress(this.address.getValue());
        	            ourLocation.setCity(this.city.getValue());
        	            this.setId(null);

        	            // Save DAO to backend with direct synchronous service API
        	            getUI().locationService.save(ourLocation);

        	            msg = String.format("Saved '%s %s %s'.", ourLocation.getVenue(),
        	                    ourLocation.getAddress(), ourLocation.getCity());
        	            Notification.show(msg, Type.TRAY_NOTIFICATION);
        	            this.setVisible(false);
        	            getUI().closeLocationButton.setVisible(true);
        	            getUI().newLocationButton.setVisible(true);
        	            getUI().refreshLocations();
        	        } catch (Exception e) {
        	            // Validation exceptions could be shown here
        	        }
        		}
        	}
        }
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }
    public void update(Button.ClickEvent event){
    	System.out.println("Update Pressed  id "+ ourLocation.getId());
		String msg = "";
        if (venue.getValue()==""){
        	msg="Please enter a name for this venue.";
        } else {
        	if (address.getValue()=="") {
        		msg="Please enter an address for this venue.";
        	} else {
        		if (city.getValue()=="") {
        			msg="Please enter a city for this venue.";
        		} else {
        	        try {
        	        	
        	            // Commit the fields from UI to DAO
        	            formFieldBindings.commit();
        	            ourLocation.setVenue(this.venue.getValue());
        	            ourLocation.setAddress(this.address.getValue());
        	            ourLocation.setCity(this.city.getValue());

        	            // Save DAO to backend with direct synchronous service API
        	            getUI().locationService.update(ourLocation);

        	            msg = String.format("Updated '%s %s %s'.", ourLocation.getVenue(),
        	                    ourLocation.getAddress(), ourLocation.getCity());
        	            Notification.show(msg, Type.TRAY_NOTIFICATION);
        	            getUI().refreshLocations();
        	        } catch (FieldGroup.CommitException e) {
        	            // Validation exceptions could be shown here
        	        }
        		}
        	}
        }
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }

    public void cancel(Button.ClickEvent event) {
    	System.out.println("Cancel Pressed");
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        closeLocationForm();
    }

    void closeLocationForm() {
    	clearLocationForm();
    	this.setVisible(false);
    	getUI().newLocationButton.setVisible(true);
    	getUI().closeLocationButton.setVisible(true);
    }

    void clearLocationForm() {
    	venue.setValue("");
    	address.setValue("");
    	city.setValue("");
    }

    void edit(OurLocation ourLocation) {
        this.ourLocation = ourLocation;
        if (ourLocation != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ourLocation,this);
            venue.setValue(this.ourLocation.getVenue());
            address.setValue(this.ourLocation.getAddress());
            city.setValue(this.ourLocation.getCity());
            System.out.println("Editing location id "+this.ourLocation.getId());
            venue.focus();
        }
        update.setVisible(true);
        submit.setVisible(false);
        this.setVisible(true);
    }

    @Override
    public EventstagramUI getUI() {
        return (EventstagramUI) super.getUI();
    }

}
