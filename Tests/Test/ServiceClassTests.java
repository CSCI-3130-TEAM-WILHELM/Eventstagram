package Test;

import junit.framework.TestCase;

import org.junit.Test;

import com.vaadin.tutorial.eventstagram.backend.Contact;
import com.vaadin.tutorial.eventstagram.backend.ContactService;
import com.vaadin.tutorial.eventstagram.backend.LocationService;
import com.vaadin.tutorial.eventstagram.backend.OurEvent;
import com.vaadin.tutorial.eventstagram.backend.OurEventService;
import com.vaadin.tutorial.eventstagram.backend.User;
import com.vaadin.tutorial.eventstagram.backend.UserService;

import java.util.List;

import org.junit.Assert;

public class ServiceClassTests extends TestCase
{
	@Test
	public void testCountOfContactService()
	{
		ContactService contactService = new ContactService();
		
		long count = contactService.count();
		
		if (count > -1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void creatingNewInstance()
	{
		ContactService contactService = ContactService.createDemoService();
		
		if (contactService == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testGetListOfContacts()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		if (contacts == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testDeleteContactFromService()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		int originalSize = contacts.size();
		
		contactService.delete(contacts.get(0));
		
		contacts = contactService.findAll("");
		
		Assert.assertNotEquals(originalSize, contacts.size());
	}
	
	@Test
	public void testAddContactToService()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		int originalSize = contacts.size();
		
		Contact newContact = contacts.get(0);
		newContact.setId(null);
		
		contactService.save(newContact);
		
		contacts = contactService.findAll("");
		
		Assert.assertNotEquals(originalSize, contacts.size());
	}
	
	@Test
	public void testCountOfOurEventService()
	{
		OurEventService ourEventService = OurEventService.createDemoService();
		
		long count = ourEventService.count();
		
		if (count > -1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void creatingNewInstanceOurEvent()
	{
		OurEventService ourEventService = OurEventService.createDemoService();
		
		if (ourEventService == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testGetListOfOurEventService()
	{
		OurEventService ourEventService = OurEventService.createDemoService();
		
		List<OurEvent> ourEvents = ourEventService.findAll("");
		
		if (ourEvents == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testDeleteOurEventFromService()
	{
		OurEventService ourEventService = OurEventService.createDemoService();
		
		List<OurEvent> ourEvents = ourEventService.findAll("");
		
		int originalSize = ourEvents.size();
		
		ourEventService.delete(ourEvents.get(0));
		
		ourEvents = ourEventService.findAll("");
		
		Assert.assertNotEquals(originalSize, ourEvents.size());
	}
	
	@Test
	public void testAddOurEventToService()
	{
		OurEventService ourEventService = OurEventService.createDemoService();
		
		List<OurEvent> ourEvents = ourEventService.findAll("");
		
		int originalSize = ourEvents.size();
		
		OurEvent newOurEvent = ourEvents.get(0);
		newOurEvent.setId((long) 10000);
		
		ourEventService.save(newOurEvent);
		
		ourEvents = ourEventService.findAll("");
		
		Assert.assertNotEquals(originalSize, ourEvents.size());
	}
	
	@Test
	public void testCountOfLocationService()
	{
		LocationService locationService = LocationService.createDemoService();
		
		long count = locationService.count();
		
		if (count > -1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void creatingNewInstanceLocation()
	{
		LocationService contactService = LocationService.createDemoService();
		
		if (contactService == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testGetListOfLocations()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		if (contacts == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testDeleteLocationFromService()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		int originalSize = contacts.size();
		
		contactService.delete(contacts.get(0));
		
		contacts = contactService.findAll("");
		
		Assert.assertNotEquals(originalSize, contacts.size());
	}
	
	@Test
	public void testAddLocationToService()
	{
		ContactService contactService = ContactService.createDemoService();
		
		List<Contact> contacts = contactService.findAll("");
		
		int originalSize = contacts.size();
		
		Contact newContact = contacts.get(0);
		newContact.setId(null);
		
		contactService.save(newContact);
		
		contacts = contactService.findAll("");
		
		Assert.assertNotEquals(originalSize, contacts.size());
	}
	
	@Test
	public void testCountOfUserService()
	{
		UserService userService = UserService.createDemoService();
		
		long count = userService.count();
		
		if (count > -1)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void creatingNewInstanceUser()
	{
		UserService userService = UserService.createDemoService();
		
		if (userService == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testGetListOfUsers()
	{
		UserService userService = UserService.createDemoService();
		
		List<User> users = userService.findAll("");
		
		if (users == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	@Test
	public void testDeleteUserFromService()
	{
		UserService userService = UserService.createDemoService();
		
		List<User> users = userService.findAll("");
		
		int originalSize = users.size();
		
		userService.delete(users.get(0));
		
		users = userService.findAll("");
		
		Assert.assertNotEquals(originalSize, users.size());
	}
	
	@Test
	public void testAddUserToService()
	{
		UserService userService = UserService.createDemoService();
		
		List<User> users = userService.findAll("");
		
		int originalSize = users.size();
		
		User newUser = users.get(0);
		newUser.setId(null);
		
		userService.save(newUser);
		
		users = userService.findAll("");
		
		Assert.assertNotEquals(originalSize, users.size());
	}

}
