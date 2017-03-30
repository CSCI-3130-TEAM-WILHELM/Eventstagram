package Tests;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TextFieldElement;

public class AppGuiTests extends TestBenchTestCase
{
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);
	
	@Before
	public void setUp() throws Exception
	{
		setDriver(new ChromeDriver());
	}
	
	@After
	public void tearDown() throws Exception
	{
		getDriver().quit();
	}
	
	private void openTestUrl()
	{
		getDriver().get("http://localhost:8080");
	}
	
	@Test
	public void initialChecks()
	{
		openTestUrl();
		
		Assert.assertTrue($(GridElement.class).exists());
	}
	

	@Test
	public void initialSetUpChecks()
	{
		openTestUrl();
		
		// Makes sure grid exists
		Assert.assertTrue($(GridElement.class).exists());
		
		GridElement grid = $(GridElement.class).first();
		
		Assert.assertEquals(grid.getCell(0, 0).getText(), "The Smashing Pumpkins");
		 
		// Checks main buttons are there
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(1, allButtons.size());
	
	}
	
	@Test
	public void succesfulLoginTest()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		
		loginButton.click();
		
		// amount of present buttons when logged in is clicked
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		allButtons.get(2).click();
		
		allButtons = $(ButtonElement.class).all();
		
		// therefore logged in because there are 3 buttons on the main page
		Assert.assertEquals(3, allButtons.size()); 
	}
	
	@Test
	public void failedLogin()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		
		loginButton.click();
		
		// amount of present buttons when logged in is clicked
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		
		// Using a wrong user name
		allTextFields.get(1).setValue("M");
		
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		
		// Using the wrong password
		passwordField.setValue("J");
		
		allButtons.get(2).click();
		
		allButtons = $(ButtonElement.class).all();
		
		// Therefore the log in has failed because there are 4 buttons on the page
		Assert.assertEquals(3, allButtons.size()); 
	}
	
	@Test
	public void cancelLogin()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// amount of present buttons when logged in is clicked
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());
		
		allButtons = $(ButtonElement.class).all();
		// the cancel button
		allButtons.get(2).click();
		
		allButtons = $(ButtonElement.class).all();
		
		// should be back to two buttons
		Assert.assertEquals(3, allButtons.size());
	}
	
	@Test
	public void logout()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		allButtons.get(2).click();
		
		allButtons = $(ButtonElement.class).all();
		
		// clicking the logout button
		allButtons.get(2).click();
		
		// only 2 buttons on the main page
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(1, allButtons.size());
	}
	
	@Test
	public void displayEventTesting()
	{
		openTestUrl();
		
		GridElement grid = $(GridElement.class).first();
		grid.getCell(0, 0).click();
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());
		
		List<LabelElement> allLabels = $(LabelElement.class).all();
		Assert.assertEquals(8, allLabels.size());
	}
	
	@Test
	public void attendingAnEvent()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// The steps to log in
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// Opening the event
		GridElement grid = $(GridElement.class).first();
		grid.getCell(0, 0).click();
		
		// Getting the new list of buttons
		allButtons = $(ButtonElement.class).all();
		
		// Getting the attending text
		List<LabelElement> allLabels = $(LabelElement.class).all();
		String firstAttendingText = allLabels.get(4).getText();
		
		// Clicking the attending button
		allButtons.get(3).click();
		
		Assert.assertNotEquals(firstAttendingText, allLabels.get(6).getText());
	}
	
	@Test
	public void interestedInAnEvent()
	{
		openTestUrl();
		
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// The steps to log in
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// Opening the event
		GridElement grid = $(GridElement.class).first();
		grid.getCell(0, 0).click();
		
		// Getting the new list of buttons
		allButtons = $(ButtonElement.class).all();
		
		// Getting the attending text
		List<LabelElement> allLabels = $(LabelElement.class).all();
		String firstAttendingText = allLabels.get(5).getText();
		
		// Clicking the attending button
		allButtons.get(4).click();
		
		Assert.assertNotEquals(firstAttendingText, allLabels.get(6).getText());
	}
	
	@Test
	public void openingProfilePage()
	{
		openTestUrl();

		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// opening profile page
		allButtons = $(ButtonElement.class).all();
		allButtons.get(1).click();
		
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(5, allButtons.size());
	}
}
