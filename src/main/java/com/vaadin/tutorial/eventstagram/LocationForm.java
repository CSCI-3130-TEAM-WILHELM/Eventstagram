package com.vaadin.tutorial.eventstagram;

//import java.util.ArrayList;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.data.DataSource;
import com.vaadin.tutorial.eventstagram.backend.OurLocation;
import com.vaadin.tutorial.eventstagram.backend.City;
import com.vaadin.tutorial.eventstagram.backend.OurEvent;
//import com.vaadin.tutorial.eventstagram.backend.LocationService;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
//import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.ui.TextField;
//import com.vaadin.ui.ComboBox;

public class LocationForm extends FormLayout {

	private static final long serialVersionUID = 1L;
    private OurLocation ourLocation = new OurLocation();
    private ComboBox<City> city;
	
    TextField venue = new TextField("Venue");
    TextField address = new TextField("Address");
    Button submit = new Button("Submit", this::submit);
    Button update = new Button("Update", this::update);
    Button cancel = new Button("Cancel", this::cancel);
    Grid cityList = new Grid();	
    
    BeanFieldGroup<OurLocation> formFieldBindings;
    
    public LocationForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        submit.setStyleName(ValoTheme.BUTTON_PRIMARY);
    	
        venue.setVisible(true);
        address.setVisible(true);
        city = new ComboBox<City>("City");
        
        city.setVisible(true);

        cityList.setContainerDataSource(new BeanItemContainer<>(City.class));
        cityList.setSelectionMode(Grid.SelectionMode.SINGLE);
        refreshCities();
        cancel.setVisible(true);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(submit, update,  cancel);
        actions.setSpacing(true);

        addComponents(venue, address, city, actions, cityList);
    }

    public void submit(Button.ClickEvent event){
    	System.out.println("Submit Pressed");
		String msg = "";
        if (venue.getValue()==""){
        	msg="Please enter a name for this venue.";
        } else {
        	if (address.getValue()=="") {
        		msg="Please enter an address for this venue.";
        	} else {
//        		if (city.getValue()=="") {
//        			msg="Please enter a city for this venue.";
//        		} else {
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
        	            getUI().refreshLocations();
        	            this.setVisible(false);
        	            getUI().closeLocationButton.setVisible(true);
        	            getUI().newLocationButton.setVisible(true);
        	        } catch (Exception e) {
        	            // Validation exceptions could be shown here
        	        }
//        		}
        	}
        }
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }
    void refreshCities() {
//    	cityList.setContainerDataSource(new BeanItemContainer<>(City.class, getUI().cityService.findAll("")));
    }
    
    public void update(Button.ClickEvent event){
		String msg = "";
        if (venue.getValue()==""){
        	msg="Please enter a name for this venue.";
        } else {
        	if (address.getValue()=="") {
        		msg="Please enter an address for this venue.";
        	} else {
//        		if (city.getValue()=="") {
//        			msg="Please enter a city for this venue.";
//        		} else {
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
        	            clearLocationForm();
        	            closeLocationForm();
        	        } catch (FieldGroup.CommitException e) {
        	            // Validation exceptions could be shown here
        	        }
//        		}
        	}
        }
        Notification.show(msg, Type.TRAY_NOTIFICATION);
    }

    public void cancel(Button.ClickEvent event) {
    	System.out.println("Cancel Pressed");
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
    	clearLocationForm();
        closeLocationForm();
    }

    void closeLocationForm() {
    	this.setVisible(false);
    	getUI().locationForm.setVisible(false);
    	getUI().newLocationButton.setVisible(true);
    	getUI().closeLocationButton.setVisible(true);
    	getUI().locationForm.cancel.setVisible(false);
    	getUI().refreshLocations();
    }

    void clearLocationForm() {
    	venue.setValue("");
    	address.setValue("");
    	city.setSelectedItem(null);
    	if(ourLocation!=null){
    		ourLocation.setId(null);
    	}
    }

    void edit(OurLocation ourLocation) {
        this.ourLocation = ourLocation;
        if (ourLocation != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ourLocation,this);
            venue.setValue(this.ourLocation.getVenue());
            address.setValue(this.ourLocation.getAddress());
//            city.setSelectedItem(this.ourLocation.getCity());
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
