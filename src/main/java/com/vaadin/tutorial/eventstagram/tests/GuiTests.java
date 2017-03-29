package com.vaadin.tutorial.eventstagram.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;

import junit.framework.Assert;

public class GuiTests extends TestBenchTestCase
{
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);
	
	@Before
	public void setUp() throws Exception
	{
		setDriver(new ChromeDriver());
	}
	
	private void openTestUrl()
	{
		getDriver().get("http://localhost:8080");
	}
	
	@Test
	public void initialChecks()
	{
		openTestUrl();
		
		Assert.assertTrue(true);
	}
	
	
}
