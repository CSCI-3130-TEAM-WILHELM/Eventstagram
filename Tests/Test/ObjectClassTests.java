package Test;

import org.junit.Test;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.tutorial.eventstagram.backend.Contact;
import com.vaadin.tutorial.eventstagram.backend.OurEvent;
import com.vaadin.tutorial.eventstagram.backend.OurLocation;
import com.vaadin.tutorial.eventstagram.backend.User;

import junit.framework.TestCase;

public class ObjectClassTests extends TestCase
{
	@Test
	public void testSettingContactId()
	{
		Long id = (long) 45;
		
		Contact contact = new Contact();
		contact.setId(id);
		
		Assert.assertEquals(id, contact.getId());
	}
	
	@Test
	public void testSettingContactEvent()
	{
		String event = "airshow";
		
		Contact contact = new Contact();
		contact.setEvent(event);
		
		Assert.assertEquals(event, contact.getEvent());
	}
	
	@Test
	public void testCloningContact()
	{
		Long id = (long) 45;
		String event = "airshow";
		
		Contact contact = new Contact();
		contact.setId(id);
		contact.setEvent(event);
		
		Contact clonedContact;
		
		try
		{
			clonedContact = contact.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			clonedContact = null;
		}
		
		Assert.assertEquals(contact.toString(), clonedContact.toString());
	}
	
	@Test
	public void testMakingContact()
	{
		Long id = (long) 45;
		String event = "airshow";
		
		Contact contact = new Contact();
		contact.setId(id);
		contact.setEvent(event);
		
		Assert.assertEquals("Contact{" + "id=" + id + ", event = " + event + "}", contact.toString());
	}
	
	@Test
	public void testSettingUserId()
	{
	    Long id = (long) 45;
	    
	    User user = new User();
	    user.setId(id);
	    
	    Assert.assertEquals(id, user.getId());
	}
	
	public void testSettingUsername()
	{
		String username = "B";
	    
	    User user = new User();
	    user.setUsername(username);
	    
	    Assert.assertEquals(username, user.getUsername());
	}
	
	public void testSettingUserPassword()
	{
	    String password = "R";
	    
	    User user = new User();
	    user.setPassword(password);
	    
	    Assert.assertEquals(password, user.getPassword());
	}
	
	public void testSettingUserAdmin()
	{
	    boolean isAdmin = true;
	    
	    User user = new User();
	    user.setAdmin(isAdmin);
	    
	    Assert.assertTrue(user.getAdmin());
	}
	
	@Test
	public void testCloningUser()
	{
	    Long id = (long) 45;
		String username = "B";
	    String password = "R";
	    boolean isAdmin = false;
		
	    User user = new User();
	    user.setId(id);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setAdmin(isAdmin);
		
		User clonedUser;
		
		try
		{
			clonedUser = user.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			clonedUser = null;
		}
		
		Assert.assertEquals(user.toString(), clonedUser.toString());
	}
	
	@Test
	public void testMakingUser()
	{
	    Long id = (long) 45;
		String username = "B";
	    String password = "R";
	    boolean isAdmin = false;
	    
	    User user = new User();
	    user.setId(id);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setAdmin(isAdmin);
	    
	    Assert.assertEquals("Contact{" + "id=" + id + ", User = " + username + '}', user.toString());
	}
	
	@Test
	public void testMakingAdminUser()
	{
	    Long id = (long) 45;
		String username = "B";
	    String password = "R";
	    boolean isAdmin = true;
	    
	    User user = new User();
	    user.setId(id);
	    user.setUsername(username);
	    user.setPassword(password);
	    user.setAdmin(isAdmin);
	    
	    Assert.assertEquals("Contact{" + "id=" + id + ", User = " + username + "}", user.toString());
	}
	
	@Test
	public void testIdOurEvent()
	{
		long id = 45;
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setId(id);
		
		Assert.assertEquals(Long.valueOf(id), ourEvent.getId());
	}
	
	@Test
	public void testTitleOurEvent()
	{
		String title = "B";	
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setTitle(title);
		
		Assert.assertEquals(title, ourEvent.getTitle());
	}
	
	@Test
	public void testReleaseDateOurEvent()
	{	
		String date = " 2011-01-18 00:00:00.0";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date finishedDate;
		
		try
		{
			finishedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			finishedDate = null;
		} 
		
		Date releaseDate = finishedDate;
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setReleaseDates(releaseDate);
		
		Assert.assertEquals(releaseDate, ourEvent.getReleaseDate());
	}
	
	@Test
	public void testOpenDateOurEvent()
	{
		String date = " 2011-01-18 00:00:00.0";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date finishedDate;
		
		try
		{
			finishedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			finishedDate = null;
		} 
			
		Date open = finishedDate;				
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setOpen(open);

		Assert.assertEquals(open, ourEvent.getOpen());
	}
	
	@Test
	public void testEndDateOurEvent()
	{
		
		String date = " 2011-01-18 00:00:00.0";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date finishedDate;
		
		try
		{
			finishedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			finishedDate = null;
		} 
		
		Date end = finishedDate;			
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setEnd(end);
		
		Assert.assertEquals(end, ourEvent.getEnd());
	}
	
	@Test
	public void testLocationIdOurEvent()
	{			
		long locationId = 46;			
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setLocationId(locationId);
		
		Assert.assertEquals(locationId, ourEvent.getLocationId());
	}
	
	@Test
	public void testDescriptionOurEvent()
	{
		String description = "R";	
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setDescription(description);
		
		Assert.assertEquals(description, ourEvent.getDescription());
	}
	
	@Test
	public void testAttendingCountOurEvent()
	{
		int attending = 66;					
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setAttending(attending);
		
		Assert.assertEquals(attending, ourEvent.getAttending());
	}
	
	@Test
	public void testInterestedCountOurEvent()
	{				
		int interested = 16;
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setInterested(interested);
		
		Assert.assertEquals(interested, ourEvent.getInterested());
	}

	@Test
	public void testCloningOurEvent()
	{
		long id = 45;
		String title = "B";	
		
		String date = " 2011-01-18 00:00:00.0";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date finishedDate;
		
		try
		{
			finishedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			finishedDate = null;
		} 
		
		Date releaseDate = finishedDate; 		
		Date open = finishedDate;				
		Date start = finishedDate;			
		Date end = finishedDate;			
		long locationId = 46;			
		String description = "R";	
		int attending = 66;					
		int interested = 16;
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setId(id);
		ourEvent.setTitle(title);
		ourEvent.setReleaseDates(releaseDate);
		ourEvent.setOpen(open);
		ourEvent.setEnd(end);
		ourEvent.setLocationId(locationId);
		ourEvent.setDescription(description);
		ourEvent.setAttending(attending);
		ourEvent.setInterested(interested);
		
		OurEvent clonedOurEvent;
		
		try
		{
			clonedOurEvent = ourEvent.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			clonedOurEvent = null;
		}
		
		Assert.assertEquals(ourEvent.toString(), clonedOurEvent.toString());
	}
	
	@Test
	public void testMakingOurEvent()
	{
		long id = 45;
		String title = "B";	
		
		String date = " 2011-01-18 00:00:00.0";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
		Date finishedDate;
		
		try
		{
			finishedDate = dateFormat.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			finishedDate = null;
		} 
		
		Date releaseDate = finishedDate; 		
		Date open = finishedDate;				
		Date start = finishedDate;			
		Date end = finishedDate;			
		long locationId = 46;			
		String description = "R";	
		int attending = 66;					
		int interested = 16;
		
		OurEvent ourEvent = new OurEvent();
		ourEvent.setId(id);
		ourEvent.setTitle(title);
		ourEvent.setReleaseDates(releaseDate);
		ourEvent.setOpen(open);
		ourEvent.setEnd(end);
		ourEvent.setLocationId(locationId);
		ourEvent.setDescription(description);
		ourEvent.setAttending(attending);
		ourEvent.setInterested(interested);
		
		Assert.assertEquals("Event{" + "id=" + id + ", Title = " + title + '}', ourEvent.toString());
	}
	
	@Test
	public void testIdOurLocatiion()
	{
	    long id = 45;
	    
	    OurLocation ourLocation = new OurLocation();
	    ourLocation.setId(id);
	    
	    Assert.assertEquals(Long.valueOf(id), ourLocation.getId());
	}
	
	@Test
	public void testVenueOurLocatiion()
	{
		String venue = "B";
	    
	    OurLocation ourLocation = new OurLocation();
	    ourLocation.setVenue(venue);
	    
	    Assert.assertEquals(venue, ourLocation.toString());
	}
	
	@Test
	public void testAddressOurLocatiion()
	{
		String address = "R";
	    
	    OurLocation ourLocation = new OurLocation();
	    ourLocation.setAddress(address);
	    
	    Assert.assertEquals(address, ourLocation.getAddress());
	}
	
	@Test
	public void testCitiesOurLocatiion()
	{
	    String city = "E";
	    
	    OurLocation ourLocation = new OurLocation();
	    ourLocation.setCity(city);
	    
	    Assert.assertEquals(city, ourLocation.getCity());
	}
	
	@Test
	public void testMakingOurLocatiion()
	{
	    long id = 45;
		String venue = "B";
		String address = "R";
	    String city = "E";
	    
	    OurLocation ourLocation = new OurLocation();
	    ourLocation.setId(id);
	    ourLocation.setVenue(venue);
	    ourLocation.setAddress(address);
	    ourLocation.setCity(city);
	    
	    Assert.assertEquals("OurLocation{" + "id=" + id + ", venue = " + venue + ", address = " + address + ", city = " + city + "}", ourLocation.toString());
	}
}
