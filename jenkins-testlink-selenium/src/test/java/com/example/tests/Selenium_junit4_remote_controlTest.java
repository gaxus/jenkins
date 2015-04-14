package com.example.tests;

import com.thoughtworks.selenium.*;

import org.apache.commons.codec.binary.Base64;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.regex.Pattern;

public class Selenium_junit4_remote_controlTest {
	@SuppressWarnings("deprecation")
	private static Selenium selenium;
	
	@Rule
	public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	@BeforeClass
	public static void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.payline.com/");
		selenium.start();
	}

	@Test
	public void testSelenium_junit4_remote_control() throws Exception {
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
	}

	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}
	
	class ScreenshotTestRule implements MethodRule {
	    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
	        return new Statement() {
	            @Override
	            public void evaluate() throws Throwable {
	                try {
	                    statement.evaluate();
	                } catch (Throwable t) {
	                    captureScreenshot(frameworkMethod.getName());
	                    throw t; // rethrow to allow the failure to be reported to JUnit
	                }
	            }

	            public void captureScreenshot(String fileName) {
	                try {
	                    File f = new File("target/surefire-reports/");
	                    f.mkdirs(); // Insure directory is there
	                    String source = selenium.getHtmlSource();
	                    FileWriter fw = new FileWriter(f.getAbsolutePath()+"/source-"+fileName+".html");
	                    BufferedWriter bw = new BufferedWriter(fw);
	                    bw.write(source);
	                    bw.close();
	                	selenium.captureEntirePageScreenshot(f.getAbsolutePath()+"/screenshot-"+fileName+".png", "");
	                } catch (Exception e) {
	                	e.printStackTrace();
	                    // No need to crash the tests if the screenshot fails
	                }
	            }
	        };
	    }
	}
}
