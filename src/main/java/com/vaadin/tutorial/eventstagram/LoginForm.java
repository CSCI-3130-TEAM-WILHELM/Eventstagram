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

public class LoginForm extends FormLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Button save = new Button("Login", this::save);
    Button cancel = new Button("Cancel", this::cancel);
    TextField username = new TextField("Username");
    PasswordField password = new PasswordField("Password");
    Button submit = new Button("Submit", this::submit);
    Button createAccount = new Button ("Create Account", this::createAccount);

    User user;
    
    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<User> formFieldBindings;
    
    public LoginForm() {
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

        addComponents(actions, username, password, submit, createAccount);
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
        	clearLoginForm();
        }
        //assuming username is presented
        else{
        	User emptyUser = new User();
        	emptyUser.setUsername(username.getValue());
        	//emptyUser.setPassword(password.getValue());
        	//////////////////////
        	//emptyUser.setAdmin(true);
        	//////////////////////
        	User returnedUser = getUI().userService.find(emptyUser);
        	if(returnedUser==null){
        		msg = "Invalid username or password.";
        		//System.err.println("Database didn't have specified user, but could it save?:");
        		//getUI().userService.save(emptyUser);
        	}
        	//assuming username exists
        	else {
        		//password matches database user password
        		if (returnedUser.getPassword().equals(password.getValue())){
        			msg = "Hello "+username.getValue()+".";
        			getUI().profilePageUI.userNameContent.setValue(username.getValue()); //Set the profile page username
        			getUI().showingLoginButton=!getUI().showingLoginButton;              //Swap the showing login button value
        			getUI().loginButton.setVisible(getUI().showingLoginButton);          //Hide the login button
        			getUI().logoutButton.setVisible(!getUI().showingLoginButton);        //Show the logout button
        			getUI().profilePageButton.setVisible(!getUI().showingLoginButton);   //Show the profile button
        			getUI().showingLoginForm=!getUI().showingLoginForm;                  //Swap the login form value
        			getUI().loginForm.setVisible(getUI().showingLoginForm);              //Hide the login form
        			getUI().newEvent.setVisible(!getUI().showingLoginButton); 			 //Show the new Event button
        			getUI().manageLocationsButton.setVisible(returnedUser.getAdmin()); 	//Set the visibility of the manage locations button to the admin status
        			getUI().currentUser=returnedUser;									//Give current user object to parent
        			getUI().profilePageUI.userInterestsContent.setValue(returnedUser.getInterests()); //Set the profile page user interests
        		}
        		//username and password do not match
        		else{
        			msg = "Invalid username or password.";
        			////////////////////////////////////////
        			//System.out.println("DBPass = "+returnedUser.getPassword()+"\nEntered Pass = "+password.getValue());
					///////////////////////////////////////
        		}
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
        closeLoginForm();
    }
    public void createAccount(Button.ClickEvent event) {
    	System.out.println("Create Account button pressed");
        // Place to call business logic.
        Notification.show("Creating account", Type.TRAY_NOTIFICATION);
        closeLoginForm();
        getUI().openCreateAccountPage();
    }
    void closeLoginForm() {
    	clearLoginForm();
    	this.setVisible(false);
    	getUI().showingLoginForm=false;
    }
    void clearLoginForm() {
    	username.setValue("");
    	password.setValue("");
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
