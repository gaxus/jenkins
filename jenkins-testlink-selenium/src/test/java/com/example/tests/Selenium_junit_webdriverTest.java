package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.safaribooks.junitattachments.CaptureFile;
import com.safaribooks.junitattachments.RecordAttachmentRule;

public class Selenium_junit_webdriverTest{
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Rule
	public RecordAttachmentRule recordArtifactRule = new RecordAttachmentRule(this);

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.payline.com/index.php/fr";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testJunitWebdriverPayline() throws Exception {
		driver.get(baseUrl);
	//	driver.findElement(By.cssSelector("a.subMenuBtn > span")).click();
//		driver.findElement(By.cssSelector("li.item-173 > a > span")).click();
	//	driver.findElement(By.linkText("Starter Pack")).click();
	//	driver.findElement(By.cssSelector("li.item-182 > a.subMenuBtn > span")).click();
		// Warning: assertTextPresent may require manual changes
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*$"));
	}


	@CaptureFile(extension = "html")
	public String capturedDom = null;

	@CaptureFile(extension = "png")
	public byte[] capturePage = null;

	@After
	public void tearDown() throws Exception {
		if (driver != null) {
			// capture the dom
			capturedDom = driver.getPageSource();

			// capture a screenshot
			if (driver instanceof TakesScreenshot) {
				capturePage = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.BYTES);
			}

			driver.close();
		}
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
