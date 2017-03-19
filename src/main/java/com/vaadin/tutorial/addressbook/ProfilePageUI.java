/*!
 * @org Team Wilhelm
 * 
 * @author Brendan and Catherine
 * 
 * Last updated Feb 23rd 2017 by Brendan and Catherine
 * 
 * @brief ProfilePageUI		Used to display the logged in users information to them
 */

package com.vaadin.tutorial.addressbook;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;

import com.vaadin.event.ShortcutAction;
import com.vaadin.tutorial.addressbook.backend.Contact;
import com.vaadin.tutorial.addressbook.backend.User;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.ui.DateField;
import com.vaadin.v7.ui.TextField;

public class ProfilePageUI extends FormLayout 
{	
	Label userNameLabel = new Label("Your username");
	Label userNameContent = new Label("");
	Label userInterestsLabel = new Label("Your interests");
	Label locationLabel = new Label("You are here");
	
	
	
	Button changeInterestButton = new Button("Modify Interests");
	TextField interestsTextField = new TextField("My interests");
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
		interestsTextField.setValue(userInterestsLabel.getValue().toString());
		
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
	
	//Checking passwords 
	private boolean compareNewtoOldPassword() {
		if (!oldPasswordField.getValue().equals(getUI().currentUser.getPassword())){
			System.err.println("Old Password is not correct!");//print out the issue
			System.err.println("Current Pass ="+getUI().currentUser.getPassword()); // print out the current password
			System.err.println("New Pass ="+newPasswordField.getValue());//get the new password that user entered 
			Notification.show("Old passwords do not match with current passwords.", Type.TRAY_NOTIFICATION);
			return false;
		}
		//change password of currentUser object (DOES NOT CHANGE DATABASE OBJECT!!)
		getUI().currentUser.setPassword(newPasswordField.getValue());
		
		
		///////////////////////////////////// changing userservice database object
		getUI().userService.delete(getUI().currentUser);
		getUI().userService.save(getUI().currentUser);
		/////////////////////////////////////
		return true;
	}
	//check new passwords if they are matching
	private boolean compareNewPasswords(){
		if (!newPasswordField.getValue().equals(confirmNewPasswordField.getValue())){
			System.err.println("New password does not match");
			Notification.show("New passwords do not match.", Type.TRAY_NOTIFICATION);
			return false;
		}
		return true;
	}
	
	private void updateInterests()
	{
		userInterestsLabel.setValue(interestsTextField.getValue().toString());
		
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
        	addComponents(userNameLabel, userNameContent, userInterestsLabel, locationLabel, 
        			  	  changeInterestButton, changePasswordButton);
        
        if (extraPieces == 1)
        	addComponents(userNameLabel, userNameContent, userInterestsLabel, locationLabel, 
        			  	  changeInterestButton, changePasswordButton, changeInterestButton,
        			  	  interestsTextField, submitNewInterestsButton);
        else if (extraPieces == 2)
        	addComponents(userNameLabel, userNameContent, userInterestsLabel, locationLabel, 
  			  	  		  changeInterestButton, changePasswordButton, oldPasswordField,
  			  	  		  newPasswordField, confirmNewPasswordField, submitNewPasswordButton);
	}
	
	private void clearLayout()
	{
		removeAllComponents();
	}
	
    @Override
    public AddressbookUI getUI() {
        return (AddressbookUI) super.getUI();
    }
}
