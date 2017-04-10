/*!
 * @org Team Wilhelm
 * 
 * @author Brendan and Catherine
 * 
 * Last updated Feb 23rd 2017 by Brendan and Catherine
 * 
 * @brief ProfilePageUI		Used to display the logged in users information to them
 */

package com.vaadin.tutorial.eventstagram;

//import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;

//import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class ProfilePageUI extends FormLayout 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Label userNameLabel = new Label("Your username");
	Label userNameContent = new Label("");
	Label empty0 = new Label("");
	
	Label userInterestsLabel = new Label("Your interests");
	Label userInterestsContent = new Label("");
	Label empty1 = new Label("");
	
	Label locationLabel = new Label("You are here");
	Label empty2 = new Label("");
	
	
	
	Button changeInterestButton = new Button("Modify Interests");
	TextField interestsTextField = new TextField();
	Button submitNewInterestsButton = new Button("Submit Interests");
	
	Button changePasswordButton = new Button("Change Password");
	Button submitNewPasswordButton = new Button("Submit New Password");
	PasswordField oldPasswordField = new PasswordField("Old Password:");
	PasswordField newPasswordField = new PasswordField("New Password");
	PasswordField confirmNewPasswordField = new PasswordField("Confirm New Password:");
	
	ProfilePageUI()
	{
        configureComponents();
        buildLayout(0);
	}
	
	private void modifyUserInterests()
	{
		interestsTextField.setValue(userInterestsContent.getValue().toString());
		
		clearLayout();
		
		buildLayout(1);
	}
	
	private void changePasswordLayout()
	{
		clearLayout();
		buildLayout(2);
	}
	
	private void checkPasswordIntegrity(){
		System.out.println("Testing password integrity");
		if (compareNewPasswords() && compareNewtoOldPassword()){
			clearLayout();
			buildLayout(0);
			Notification.show("Password has been succesfully updated.", Type.TRAY_NOTIFICATION);
		}
	}
	
	//INCOMPLETE
	private boolean compareNewtoOldPassword() {
		if (!oldPasswordField.getValue().equals(getUI().currentUser.getPassword())){
			//////////////////////////////////////////////////////////////////
			System.err.println("New and Old Passwords do not match");
			System.err.println("Current Pass ="+getUI().currentUser.getPassword());
			System.err.println("New Pass ="+newPasswordField.getValue());
			//////////////////////////////////////////////////////////////////
			
			Notification.show("New and Old passwords do not match.", Type.TRAY_NOTIFICATION);
			return false;
		}
		//change password of currentUser and update database
		getUI().currentUser.setPassword(newPasswordField.getValue());
		getUI().userService.updatePassword(getUI().currentUser);

		return true;
	}

	private boolean compareNewPasswords(){
		if (!newPasswordField.getValue().equals(confirmNewPasswordField.getValue())){
			System.err.println("New password does not match");
			Notification.show("Passwords do not match.", Type.TRAY_NOTIFICATION);
			return false;
		}
		return true;
	}
	
	private void updateInterests()
	{
		getUI().currentUser.setInterests(interestsTextField.getValue());
		getUI().userService.updateInterests(getUI().currentUser); // commit changes to database
		userInterestsContent.setValue(getUI().currentUser.getInterests());
		
		clearLayout();
		
		buildLayout(0);
	}
	
	private void configureComponents()
	{		
		changeInterestButton.addClickListener(e -> modifyUserInterests());
		submitNewInterestsButton.addClickListener(e -> updateInterests());
		changePasswordButton.addClickListener(e -> changePasswordLayout());
		submitNewPasswordButton.addClickListener(e -> checkPasswordIntegrity());
	}
	
	private void buildLayout(int extraPieces)
	{
        setSizeUndefined();
        setMargin(true);

        if (extraPieces == 0)
        	addComponents(userNameLabel, userNameContent, empty0, userInterestsLabel, 
        				  userInterestsContent, changeInterestButton, empty1, locationLabel, empty2,
        			  	  changePasswordButton);
        
        if (extraPieces == 1)
        	addComponents(userNameLabel, userNameContent, empty0, userInterestsLabel, 
        				  interestsTextField, submitNewInterestsButton, empty1, locationLabel, 
        				  empty2, changePasswordButton);
        else if (extraPieces == 2)
        	addComponents(userNameLabel, userNameContent, empty0, userInterestsLabel, 
        				  userInterestsContent, changeInterestButton, empty1, locationLabel, empty2, 
  			  	  		  oldPasswordField, newPasswordField, confirmNewPasswordField, submitNewPasswordButton);
	}
	
	private void clearLayout()
	{
		removeAllComponents();
	}
	
    @Override
    public EventstagramUI getUI() {
        return (EventstagramUI) super.getUI();
    }
}