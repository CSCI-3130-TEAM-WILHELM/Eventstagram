package com.vaadin.tutorial.eventstagram;

//import java.util.ArrayList;

import com.vaadin.event.ShortcutAction;
import com.vaadin.tutorial.eventstagram.backend.OurLocation;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
//import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.TextField;

public class LocationForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	
    TextField venue = new TextField("Venue");
    TextField address = new TextField("address");
    TextField city = new TextField("city");
    Button submit = new Button("Submit", this::submit);
    Button cancel = new Button("Cancel", this::cancel);

    OurLocation ourLocation;
    
    BeanFieldGroup<OurLocation> formFieldBindings;
    
    public LocationForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        submit.setStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.setClickShortcut(ShortcutAction.KeyCode.ENTER);		//Allows form submission on ENTER
    	
        setVisible(true);											//Make all components visible
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        HorizontalLayout actions = new HorizontalLayout(submit, cancel);
        actions.setSpacing(true);

        addComponents(venue, address, city, actions);
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
        		if (city.getValue()=="") {
        			msg="Please enter a city for this venue.";
        		} else {
        			//Save the data
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
            venue.focus();
        }
        setVisible(ourLocation != null);
    }

    @Override
    public EventstagramUI getUI() {
        return (EventstagramUI) super.getUI();
    }

}
