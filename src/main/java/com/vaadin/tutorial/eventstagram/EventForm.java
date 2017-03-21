package com.vaadin.tutorial.eventstagram;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.tutorial.eventstagram.backend.OurEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.ui.DateField;
import com.vaadin.ui.TextField;
//import com.vaadin.ui.TextArea;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */

public class EventForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	OurEvent ourEvent;
    Label eventTitle = new Label();
    Label eventDescription = new Label();
    TextField title = new TextField("Title");
    TextField description = new TextField("Description");
    Label eventStart = new Label("Start: ");
    DateField start = new DateField("Start:");
    Label eventEnd = new Label("End: ");
    DateField end = new DateField("End:");
    Label eventOpen = new Label("Doors Open: ");
    DateField open = new DateField("Doors Open:");
    private Label eventLocationLabel = new Label();
    private Label attendingCountLabel = new Label();
    private Label interestedCountLabel = new Label();    
    Button attendingButton = new Button("Attending");
    Button interestedButton = new Button("Interested");
    Label loginWarning = new Label("You must be logged in to use these features.");
    		
    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<OurEvent> formFieldBindings;

    public EventForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
    	attendingButton.addClickListener(e -> attendingEvent());
    	interestedButton.addClickListener(e -> interestedEvent());
    	title.setWidth("100%");
    	description.setWidth("100%");
    	start.setResolution(Resolution.MINUTE);
    	end.setResolution(Resolution.MINUTE);
    	open.setResolution(Resolution.MINUTE);
    	eventLocationLabel.setValue("Dalhousie University");
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);
        HorizontalLayout actions = new HorizontalLayout(attendingButton, interestedButton, loginWarning);
        actions.setSpacing(true);
        HorizontalLayout dates = new HorizontalLayout(start, eventStart, end, eventEnd, open, eventOpen);
        dates.setSpacing(true);

        addComponents(actions, title, eventTitle, description, eventDescription, dates, attendingCountLabel, interestedCountLabel);
    }

    
    /*
    public void save(Button.ClickEvent event) {
        try {
            // Commit the fields from UI to DAO
            formFieldBindings.commit();

            // Save DAO to backend with direct synchronous service API
            getUI().service.save(contact);

            String msg = String.format("Saved '%s'.", contact.getEvent());
            Notification.show(msg, Type.TRAY_NOTIFICATION);
            getUI().refreshContacts();
        } catch (FieldGroup.CommitException e) {
            // Validation exceptions could be shown here
        }
    }

    public void cancel(Button.ClickEvent event) {
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        getUI().contactList.select(null);
    }
*/ 
  //method to update user's attendance
    private void attendingEvent()
    {
    	attendingButton.setEnabled(false);
    	ourEvent.setAttending(ourEvent.getAttending()+1);
    	attendingCountLabel.setValue(ourEvent.getAttending() + " people attending");    		
    	
    	//if they were previously interested
    	if (!interestedButton.isEnabled())
    	{
    		interestedButton.setEnabled(true);
    		ourEvent.setInterested(ourEvent.getInterested()-1);
    		interestedCountLabel.setValue(ourEvent.getInterested() + " people interested");
    	}    	
    }
    
  //method to update user's interestedness
    private void interestedEvent()
    {
    	interestedButton.setEnabled(false);
    	ourEvent.setInterested(ourEvent.getInterested()+1);
    	interestedCountLabel.setValue(ourEvent.getInterested()+" people interested");
    	
    	//if they were previously attending
    	if (!attendingButton.isEnabled())
    	{
    		attendingButton.setEnabled(true);
    		ourEvent.setAttending(ourEvent.getAttending()-1);
    		attendingCountLabel.setValue(ourEvent.getAttending()+" people attending");
    	}
    	
    }    
    
    void edit(OurEvent ourEvent){
		title.setVisible(true);
		eventTitle.setVisible(false);
		description.setVisible(true);
		eventDescription.setVisible(false);
		attendingButton.getParent().setVisible(false);
		start.setVisible(true);
        eventStart.setVisible(false);
		end.setVisible(true);
        eventEnd.setVisible(false);
		open.setVisible(true);
        eventOpen.setVisible(false);
        attendingCountLabel.setVisible(false);
    	interestedCountLabel.setVisible(false);
    
        this.ourEvent = ourEvent;
        if (ourEvent != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ourEvent, this);
            start.setData(ourEvent.getStart());
            end.setData(ourEvent.getEnd());
            open.setData(ourEvent.getOpen());
            eventEnd.setValue("End: "+ourEvent.getEnd()+"    ");
            eventOpen.setValue("Doors Open: "+ourEvent.getOpen()+"     ");
            eventTitle.setValue(ourEvent.getTitle());
            eventDescription.setValue(ourEvent.getDescription());
        }
        setVisible(ourEvent != null);
    }
    void display(OurEvent ourEvent, boolean isLoggedin){
		title.setVisible(false);
		eventTitle.setVisible(true);
		description.setVisible(false);
		eventDescription.setVisible(true);
		start.setVisible(false);
		eventStart.setVisible(true);
		end.setVisible(false);
		eventEnd.setVisible(true);
		open.setVisible(false);
		eventOpen.setVisible(true);
		attendingButton.getParent().setVisible(true);
		attendingButton.setEnabled(isLoggedin);
		interestedButton.setEnabled(isLoggedin);
		loginWarning.setVisible(!isLoggedin);
        attendingCountLabel.setVisible(true);
    	interestedCountLabel.setVisible(true);
    
        this.ourEvent = ourEvent;
        if (ourEvent != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ourEvent,
                    this);
            //event.focus();
            eventStart.setValue("Start: "+ourEvent.getStart()+"     ");
            eventEnd.setValue("End: "+ourEvent.getEnd()+"    ");
            eventOpen.setValue("Doors Open: "+ourEvent.getOpen()+"     ");
            eventTitle.setValue(ourEvent.getTitle());
            eventDescription.setValue(ourEvent.getDescription());
            attendingCountLabel.setValue(ourEvent.getAttending()+" people attending");
        	interestedCountLabel.setValue(ourEvent.getInterested()+" people are interested");
        }
        setVisible(ourEvent != null);
    }

    @Override
    public EventstagramUI getUI() {
        return (EventstagramUI) super.getUI();
    }
}
