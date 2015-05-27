package com.payline.qualif;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestWatchman;
import org.junit.runners.model.FrameworkMethod;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;


/**
 * 
 * @author Charlélie Bouvier - Monext 04/2015
 * Squelette pour test Selenium WebDriver.
 *
 */
public class Junit_webdriver_paylineTest {
	
	/**
	 * Règle qui enregistre une capture d'écran ainsi que le code source de la page en cas d'erreur sur un test.
	 */
	@Rule
	public ScreenShotAndSourceCodeRule screenShootRule =	new ScreenShotAndSourceCodeRule();

	private static WebDriver driver;
	private static WebDriver webdriver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

	/**
	 * Permet le choix du navigateur, commentez/décommentez selon le choix.
	 * Instancie un WebDriver associé à un navigateur et à une page web.
	 * @see https://code.google.com/p/selenium/wiki/InternetExplorerDriver
	 * @see https://code.google.com/p/selenium/wiki/ChromeDriver
	 * @see https://code.google.com/p/selenium/wiki/FirefoxDriver
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.ie.driver", "d:/POC/webdrivers/IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "d:/POC/webdrivers/chromedriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		//driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver = new Augmenter().augment(driver);
		baseUrl = "http://www.payline.com/index.php/fr/";
	}

	/**
	 * Suite de test.
	 * @throws Exception
	 */
	@Test
	public void testJunitWebdriverPayline() throws Exception {
		webdriver.get(baseUrl);
		webdriver.findElement(By.cssSelector("li.item-168 > a.mainParentBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-170 > a.subMenuBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-173 > a > span")).click();
		//The next one fails.
		//webdriver.findElement(By.cssSelector("li.item-183 > a.subMenuBtn > span")).click();
	}
	
	@Test
	public void testJunitWebdriverPayline1() throws Exception {
		webdriver.get(baseUrl);
		webdriver.findElement(By.cssSelector("li.item-168 > a.mainParentBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-170 > a.subMenuBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-173 > a > span")).click();
		//The next one fails.
		//webdriver.findElement(By.cssSelector("li.item-183 > a.subMenuBtn > span")).click();
	}
	
	@Test
	public void testJunitWebdriverPayline2() throws Exception {
		webdriver.get(baseUrl);
		webdriver.findElement(By.cssSelector("li.item-168 > a.mainParentBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-170 > a.subMenuBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-173 > a > span")).click();
		//The next one fails.
		//webdriver.findElement(By.cssSelector("li.item-183 > a.subMenuBtn > span")).click();
	}
	
	@Test
	public void testJunitWebdriverPayline3() throws Exception {
		webdriver.get(baseUrl);
		webdriver.findElement(By.cssSelector("li.item-168 > a.mainParentBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-170 > a.subMenuBtn > span")).click();
		webdriver.findElement(By.cssSelector("li.item-173 > a > span")).click();
		//The next one fails.
		//webdriver.findElement(By.cssSelector("li.item-183 > a.subMenuBtn > span")).click();
	}

	/**
	 * Fermeture de la fenêtre du navigateur.
	 * AfterClass parce que sinon impossible de faire de screenshot si le selenium est fermé avant.
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception {
		webdriver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	/**
	 * Auto-généré par Selenium IDE, sorte de macro
	 * @param by
	 * @return
	 */
	private boolean isElementPresent(By by) {
		try {
			webdriver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Auto-généré par Selenium IDE, sorte de macro
	 * @param by
	 * @return
	 */
	private boolean isAlertPresent() {
		try {
			webdriver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * Auto-généré par Selenium IDE, sorte de macro
	 * @param by
	 * @return
	 */
	private String closeAlertAndGetItsText() {
		try {
			Alert alert = webdriver.switchTo().alert();
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

	/**
	 * Classe de la règle qui effectue les captures en cas d'erreur.
	 * @author Charlélie Bouvier - Monext 04/2015
	 *
	 */
	public class ScreenShotAndSourceCodeRule extends TestWatchman {
		@Override
		public void failed(Throwable e, FrameworkMethod method) {
			try {
			String fileName = method.getName();
			File f = new File("target/surefire-reports/");
			f.mkdirs(); // Insure directory is there
			String source = webdriver.getPageSource();
			FileWriter fw;
			
				fw = new FileWriter(f.getAbsolutePath()+"/source-"+fileName+".html");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(source);
			bw.close();
			File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(
					OutputType.FILE);
			String scrFilename = "screenshot-"+fileName + ".png";
			File outputFile = new File("target/surefire-reports", scrFilename);
			FileUtils.copyFile(scrFile, outputFile);
			} catch (Exception ex) {
				// No need to crash the tests if the screenshot fails
				ex.printStackTrace();
			} finally{
				webdriver.quit();
			}
		}
	}
}
