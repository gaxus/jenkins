package com.example.tests;

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class Selenium_junit4_remote_controlTest {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.payline.com/");
		selenium.start();
	}

	@Test
	public void testSelenium_junit4_remote_control() throws Exception {
		try{
			selenium.open("/index.php/fr/");
			selenium.click("css=a.subMenuBtn > span");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=li.item-173 > a > span");
			selenium.waitForPageToLoad("30000");
			assertTrue(selenium.isTextPresent("qzfjzejfzhefzhfeh"));
			selenium.click("css=li.item-176 > a.subMenuBtn > span");
			selenium.waitForPageToLoad("30000");
			selenium.click("css=li.item-181.first > a.subMenuBtn > span");
			selenium.waitForPageToLoad("30000");
		} catch(Exception e){
			selenium.captureScreenshot(System.getProperty("user.dir")+"/jenkins-testlink-selenium/target/test-attachments/screenshot.png");
		}
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
