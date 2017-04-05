package com.vaadin.tutorial.eventstagram;

import java.util.List;
import java.util.ArrayList;

import com.vaadin.event.ShortcutAction;
import com.vaadin.tutorial.eventstagram.backend.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.TextField;

/* Create custom UI Components.
 *
 * Create your own Vaadin components by inheritance and composition.
 * This is a form component inherited from VerticalLayout. Use
 * Use BeanFieldGroup to bind data fields from DTO to UI fields.
 * Similarly named field by naming convention or customized
 * with @PropertyId annotation.
 */

//CHANGES 2

public class CreateAccountForm extends FormLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Button save = new Button("Login", this::save);
    Button cancel = new Button("Cancel", this::cancel);
    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");
    PasswordField confirmPassword = new PasswordField("Confirm Password");
    Button submit = new Button("Submit", this::submit);

    User user;
    
    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<User> formFieldBindings;
    
    public CreateAccountForm() {
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
        submit.setStyleName(ValoTheme.BUTTON_PRIMARY);
        submit.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    	
        setVisible(true);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);

        //*add login
        HorizontalLayout actions = new HorizontalLayout(cancel);
        actions.setSpacing(true);

        addComponents(actions, username, password, confirmPassword, submit);
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

    public void submit(Button.ClickEvent event){
    	//System.out.println("Submit Pressed");
		
		String msg = "";

        // Save DAO to backend with direct synchronous service API
        
        if (username.getValue()==""){
        	msg="Please enter a proper username.";
        }
        else if (username.getValue().length()<3){
        	msg="Please enter a username greater than 3 characters";
        }
        //assuming username is presented
        else{
        	User emptyUser = new User();
        	emptyUser.setUsername(username.getValue());
        	emptyUser.setAdmin(false);
        	
        	User returnedUser = getUI().userService.find(emptyUser);
        	
        	//username is valid
        	if(returnedUser==null){
        		//ensure password is of some length length
        		if (password.getValue().length()<4)
        			msg="please enter a password larger than 4 characters";	
        		//ensure passwords are the same
        		else{

	        		if (!password.getValue().equals(confirmPassword.getValue()))
	        			msg="Passwords do not match";

	        		else {
	        			emptyUser.setPassword(password.getValue());
	        			getUI().userService.save(emptyUser);
	        			msg = "User Created, please login using your credentials";
	        			closeForm();
	            	}
        		}
        		//System.err.println("Database didn't have specified user, but could it save?:");
        		//getUI().userService.save(emptyUser);
        	}
        	//assuming username exists
        	else {
        		msg = "Username already in use.";
        	}
        }
        
        Notification.show(msg, Type.TRAY_NOTIFICATION);
        getUI().eventForm.attendingButton.setEnabled(true);
        getUI().eventForm.interestedButton.setEnabled(true);
        getUI().eventForm.loginWarning.setVisible(false);

    }
    public void cancel(Button.ClickEvent event) {
    	System.out.println("Cancel Pressed");
        // Place to call business logic.
        Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
        closeForm();
    }
    void closeForm() {
    	clearForm();
    	this.setVisible(false);
    	getUI().createAccountForm.setVisible(false);
    }
    void clearForm() {
    	username.setValue("");
    	password.setValue("");
    	confirmPassword.setValue("");
    }

    void edit(User user) {
        this.user = user;
        if (user != null) {
            // Bind the properties of the contact POJO to fields in this form
            formFieldBindings = BeanFieldGroup.bindFieldsBuffered(user,this);
            username.focus();
        }
        setVisible(user != null);
    }

    @Override
    public EventstagramUI getUI() {
        return (EventstagramUI) super.getUI();
    }

}
