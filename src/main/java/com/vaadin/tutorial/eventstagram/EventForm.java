package com.vaadin.tutorial.eventstagram;

import com.vaadin.event.ShortcutAction;
//import com.vaadin.tutorial.eventstagram.backend.Contact;
import com.vaadin.tutorial.eventstagram.backend.OurEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.TextArea;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */

public class EventForm extends FormLayout {

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
    private Button attendingButton = new Button("Attending");
    private Button interestedButton = new Button("Interested");
    		
    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<OurEvent> formFieldBindings;

    public EventForm() {
        configureComponents();
        buildLayout();
    }

    private void configureComponents() {
        /*
         * Highlight primary actions.
         *
         * With Vaadin built-in styles you can highlight the primary save button
         * and give it a keyboard shortcut for a better UX.
         */
    	attendingButton.addClickListener(e -> attendingEvent());
    	interestedButton.addClickListener(e -> interestedEvent());
    	title.setWidth("100%");
    	description.setWidth("100%");
    	start.setResolution(DateField.RESOLUTION_MIN);
    	end.setResolution(DateField.RESOLUTION_MIN);
    	open.setResolution(DateField.RESOLUTION_MIN);
    	eventLocationLabel.setValue("Dalhousie University");
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);
        HorizontalLayout actions = new HorizontalLayout(attendingButton, interestedButton);
        actions.setSpacing(true);
        HorizontalLayout dates = new HorizontalLayout(start, eventStart, end, eventEnd, open, eventOpen);
        dates.setSpacing(true);

        addComponents(actions, title, eventTitle, description, eventDescription, dates, attendingCountLabel, interestedCountLabel);
    }

    /*
     * Use any JVM language.
     *
     * Vaadin supports all languages supported by Java Virtual Machine 1.6+.
     * This allows you to program user interface in Java 8, Scala, Groovy or any
     * other language you choose. The new languages give you very powerful tools
     * for organizing your code as you choose. For example, you can implement
     * the listener methods in your compositions or in separate controller
     * classes and receive to various Vaadin component events, like button
     * clicks. Or keep it simple and compact with Lambda expressions.
     */
    
    
    
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
//    	String attendingCountLabelText = attendingCountLabel.getValue();
    	
    	
//    	String updatedCountText = updateCount(attendingCountLabelText, true, "\\s+");
//    	attendingCountLabel.setValue(updatedCountText);
    		
    	
    	//if they were previously interested
    	if (!interestedButton.isEnabled())
    	{
    		interestedButton.setEnabled(true);
    		ourEvent.setInterested(ourEvent.getInterested()-1);
    		interestedCountLabel.setValue(ourEvent.getInterested() + " people interested");
//    		updatedCountText = updateCount(interestedCountLabel.getValue(), false, "\\s+");
//    		interestedCountLabel.setValue(updatedCountText);
    	}

    	
    }
    
  //method to update user's interestedness
    private void interestedEvent()
    {
    	interestedButton.setEnabled(false);
    	ourEvent.setInterested(ourEvent.getInterested()+1);
    	interestedCountLabel.setValue(ourEvent.getInterested()+" people interested");
//    	String interestedCountLabelText = interestedCountLabel.getValue(); 	
//    	String updatedCountText = updateCount(interestedCountLabelText, true, "\\s+");
//    	interestedCountLabel.setValue(updatedCountText);   
    	
    	//if they were previously attending
    	if (!attendingButton.isEnabled())
    	{
    		attendingButton.setEnabled(true);
    		ourEvent.setAttending(ourEvent.getAttending()-1);
    		attendingCountLabel.setValue(ourEvent.getAttending()+" people attending");
//    		updatedCountText = updateCount(attendingCountLabel.getValue(), false, "\\s+");
//    		attendingCountLabel.setValue(updatedCountText);
    	}
    	
    }
    
    private String updateCount(String text, boolean isIncrement, String toSplit)
    {
    	String [] splitUpText = text.split(toSplit);
    	int newCount = Integer.parseInt(splitUpText[0]);
    	
    	if (isIncrement)
    	{
    		newCount++;
    	}
    	else
    	{
    		newCount--;
    	}
    	
    	StringBuilder stringBuilder = new StringBuilder();
    	stringBuilder.append(newCount);
    	
    	for (int i = 1; i < splitUpText.length; i++)
    	{
    		stringBuilder.append(" " + splitUpText[i]);
    	}

    	return stringBuilder.toString();
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
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(ourEvent,
                    this);
            //event.focus();
            start.setValue(ourEvent.getStart());
            end.setValue(ourEvent.getEnd());
            open.setValue(ourEvent.getOpen());
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
