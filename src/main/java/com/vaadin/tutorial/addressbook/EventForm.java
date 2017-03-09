package com.vaadin.tutorial.addressbook;

import com.vaadin.event.ShortcutAction;
import com.vaadin.tutorial.addressbook.backend.Contact;
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

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */

public class EventForm extends FormLayout {
    Contact contact;
    private Label eventNameLabel = new Label(); 
    private Label eventDescriptionLabel = new Label();
    private Label eventDateLabel = new Label();
    private Label eventLocationLabel = new Label();
    private Label attendingCountLabel = new Label();
    private Label interestedCountLabel = new Label();    
    private Button attendingButton = new Button("Attending");
    private Button interestedButton = new Button("Interested");
    		
    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<Contact> formFieldBindings;

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
    	
    	eventNameLabel.setValue("Event Name");
    	eventDescriptionLabel.setValue("A real fun time");
    	eventDateLabel.setValue("February 29th 2017 at 7:00pm");
    	eventLocationLabel.setValue("Dalhousie University");
    	attendingCountLabel.setValue("0 people attending");
    	interestedCountLabel.setValue("0 people are interested");
        setVisible(false);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);
        HorizontalLayout actions = new HorizontalLayout(attendingButton, interestedButton);
        actions.setSpacing(true);
        addComponents(actions, eventNameLabel, eventDateLabel, eventLocationLabel, eventDescriptionLabel, attendingCountLabel, interestedCountLabel);
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
    	String attendingCountLabelText = attendingCountLabel.getValue();
    	
    	
    	String updatedCountText = updateCount(attendingCountLabelText, true, "\\s+");
    	attendingCountLabel.setValue(updatedCountText);
    		
    	
    	//if they were previously interested
    	if (!interestedButton.isEnabled())
    	{
    		interestedButton.setEnabled(true);
    		updatedCountText = updateCount(interestedCountLabel.getValue(), false, "\\s+");
    		interestedCountLabel.setValue(updatedCountText);
    	}

    	
    }
    
  //method to update user's interestedness
    private void interestedEvent()
    {
    	interestedButton.setEnabled(false);
    	String interestedCountLabelText = interestedCountLabel.getValue(); 	
    	String updatedCountText = updateCount(interestedCountLabelText, true, "\\s+");
    	interestedCountLabel.setValue(updatedCountText);   
    	
    	//if they were previously attending
    	if (!attendingButton.isEnabled())
    	{
    		attendingButton.setEnabled(true);
    		updatedCountText = updateCount(attendingCountLabel.getValue(), false, "\\s+");
    		attendingCountLabel.setValue(updatedCountText);
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
    
    
    
    void edit(Contact contact, boolean isLoggedin){
    	
    	if(!isLoggedin)
    	{
    		attendingButton.setEnabled(false);
    		interestedButton.setEnabled(false);
    	}
    	if (isLoggedin)
    	{
    		attendingButton.setEnabled(true);
    		interestedButton.setEnabled(true);
    	}
    
        this.contact = contact;
        if (contact != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(contact,
                    this);
            //event.focus();
        }
        setVisible(contact != null);
    }

    @Override
    public AddressbookUI getUI() {
        return (AddressbookUI) super.getUI();
    }

}
