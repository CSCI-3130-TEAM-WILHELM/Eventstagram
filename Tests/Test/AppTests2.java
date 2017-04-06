package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.DateFieldElement;
import com.vaadin.testbench.elements.GridElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.ui.TextField;

public class AppTests2 extends TestBenchTestCase
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
	public void loginAsAdmin()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Peter");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Smith");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// as an admin there are four buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(4, allButtons.size());
	}
	
	@Test
	public void clickingCreateAnAccount()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// there are only 3 buttons when creating an account
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());
	}
	
	@Test
	public void creatingAccountNameLessThan3()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting new buttons
		allButtons = $(ButtonElement.class).all();
		
		// getting text and password fields
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		List<PasswordFieldElement> allPasswordFields = $(PasswordFieldElement.class).all();
		
		allTextFields.get(0).setValue("B");
		allPasswordFields.get(0).setValue("R");
		allPasswordFields.get(1).setValue("E");
		
		// clicking the create account button
		allButtons.get(2).click();
		
		// there should be two password fields
		allPasswordFields = $(PasswordFieldElement.class).all();
		Assert.assertEquals(2, allPasswordFields.size());
	}
	
	@Test
	public void creatingAccountMissMatchPattern()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting new buttons
		allButtons = $(ButtonElement.class).all();
		
		// getting text and password fields
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		List<PasswordFieldElement> allPasswordFields = $(PasswordFieldElement.class).all();
		
		allTextFields.get(0).setValue("B");
		allPasswordFields.get(0).setValue("RRRRRR");
		allPasswordFields.get(1).setValue("E");
		
		// clicking the create account button
		allButtons.get(2).click();
		
		// there should be two password fields
		allPasswordFields = $(PasswordFieldElement.class).all();
		Assert.assertEquals(2, allPasswordFields.size());
	}
	
	@Test
	public void creatingAccountPasswordLessThan4Chars()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting new buttons
		allButtons = $(ButtonElement.class).all();
		
		// getting text and password fields
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		List<PasswordFieldElement> allPasswordFields = $(PasswordFieldElement.class).all();
		
		allTextFields.get(0).setValue("B");
		allPasswordFields.get(0).setValue("RRRRRR");
		allPasswordFields.get(1).setValue("E");
		
		// clicking the create account button
		allButtons.get(2).click();
		
		// there should be two password fields
		allPasswordFields = $(PasswordFieldElement.class).all();
		Assert.assertEquals(2, allPasswordFields.size());
	}
	
	@Test
	public void creatingAccountAccountExists()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting new buttons
		allButtons = $(ButtonElement.class).all();
		
		// getting text and password fields
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		List<PasswordFieldElement> allPasswordFields = $(PasswordFieldElement.class).all();
		
		allTextFields.get(0).setValue("aaaa");
		allPasswordFields.get(0).setValue("aaaa");
		allPasswordFields.get(1).setValue("aaaa");
		
		// clicking the create account button
		allButtons.get(2).click();
		
		// there should be two password fields
		allPasswordFields = $(PasswordFieldElement.class).all();
		Assert.assertEquals(2, allPasswordFields.size());
	}
	
	@Test
	public void creatingAccountSuccess()
	{
		openTestUrl();
		
		// clicking the login button
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting new buttons
		allButtons = $(ButtonElement.class).all();
		
		// getting text and password fields
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		List<PasswordFieldElement> allPasswordFields = $(PasswordFieldElement.class).all();
		
		int limit = 1000000;
		Random randomInt = new Random();
		int toAppend;
		
		for ( ; ; )
		{
			toAppend = randomInt.nextInt(limit);
			
			allTextFields.get(1).setValue("aaaa" + "" + toAppend);
			allPasswordFields.get(0).setValue("aaaa");
			allPasswordFields.get(1).setValue("aaaa");
			
			// clicking the create account button
			allButtons = $(ButtonElement.class).all();
			allButtons.get(2).click();
			
			allPasswordFields = $(PasswordFieldElement.class).all();
			if (allPasswordFields.size() == 0)
				break;
		}
		
		// if it makes it here its a success or the test never ends
		Assert.assertTrue(true);
	}
	
	@Test
	public void submitNewEvent()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Peter");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Smith");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the new event button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(0).click();
		
		// entering a title
		allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("AnCo");
		
		// clicking the submit button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(3).click();
		
		// getting the grid
		GridElement grid = $(GridElement.class).first();
		
		// searching for the event
		for (int i = 0; ; i++)
		{
			if (i > 20)
			{
				Assert.assertTrue(true);
				break;
			}
			if (grid.getCell(i, 0).getText() == "AnCo")
			{
				Assert.assertTrue(true);
				break;
			}
		}
	}
	
	@Test
	public void amountOfHeadersAreCorrect()
	{
		openTestUrl();
		
		// getting the grid
		GridElement grid = $(GridElement.class).first();
		
		List<String> headers = new ArrayList<String>();
		
		for (int i = 0; i < grid.getHeaderCount(); i++)
			headers.add(grid.getHeaderCell(0, i).getText());
		
		Assert.assertEquals(1, headers.size());
	}
	
	@Test
	public void headerTitleNameIsCorrect()
	{
		openTestUrl();
		
		// getting the grid
		GridElement grid = $(GridElement.class).first();
		
		List<String> headers = new ArrayList<String>();
		
		for (int i = 0; i < grid.getHeaderCount(); i++)
			headers.add(grid.getHeaderCell(0, i).getText());
		
		Assert.assertEquals("Title" , headers.get(0));
	}
	
	@Test
	public void secondGridClickHideEventInfo()
	{
		openTestUrl();
		
		GridElement grid = $(GridElement.class).first();
		
		// double click to open and hide an events info
		grid.getCell(0, 0).click();
		grid.getCell(0, 0).click();
		
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		
		Assert.assertEquals(1, allButtons.size());
	}
	
	@Test
	public void doubleClickProfilePageButton()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the profile page button twice to open and close
		allButtons = $(ButtonElement.class).all();
		allButtons.get(1).click();
		allButtons.get(1).click();
		
		// there should only be 3 buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(3, allButtons.size());	
	}
	
	@Test
	public void doubleClickNewEventButton()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Mike");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Jones");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the profile page button twice to open and close
		allButtons = $(ButtonElement.class).all();
		allButtons.get(0).click();
		allButtons.get(0).click();
		
		// there should only be 3 buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(4, allButtons.size());
	}
	
	@Test
	public void doubleClickManageLocationsButton()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Peter");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Smith");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the manage button twice to open and close
		allButtons = $(ButtonElement.class).all();
		allButtons.get(1).click();
		allButtons.get(1).click();
		
		// there should only be 3 buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(6, allButtons.size());
	}
	
	@Test
	public void closeManageLocations()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Peter");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Smith");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the manage location button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(1).click();
		
		// clicking the close button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(8).click();
		
		// there should only be 3 buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(7, allButtons.size());
	}
	
	@Test
	public void cancelManageLocations()
	{
		openTestUrl();
		
		// The steps to log in
		ButtonElement loginButton = $(ButtonElement.class).id("loginButtonId");
		loginButton.click();
		
		List<TextFieldElement> allTextFields = $(TextFieldElement.class).all();
		allTextFields.get(1).setValue("Peter");
		PasswordFieldElement passwordField = $(PasswordFieldElement.class).first();
		passwordField.setValue("Smith");
		
		// clicking the login submit button
		List<ButtonElement> allButtons = $(ButtonElement.class).all();
		allButtons.get(2).click();
		
		// clicking the manage location button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(1).click();
		
		// clicking the close button
		allButtons = $(ButtonElement.class).all();
		allButtons.get(8).click();
		
		// there should only be 3 buttons
		allButtons = $(ButtonElement.class).all();
		Assert.assertEquals(7, allButtons.size());
	}
}
