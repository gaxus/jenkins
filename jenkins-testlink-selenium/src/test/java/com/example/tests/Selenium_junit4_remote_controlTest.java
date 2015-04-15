package com.example.tests;

import com.thoughtworks.selenium.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * 
 * @author Charlélie Bouvier - Monext 04/2015
 * Squelette pour test Selenium Remote Control.
 *
 */
public class Selenium_junit4_remote_controlTest {
	private static Selenium selenium;

	/**
	 * Règle qui enregistre une capture d'écran ainsi que le code source de la page en cas d'erreur sur un test.
	 */
	@Rule
	public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	/**
	 * Instanciation de Selenium, connexion au serveur Selenium-RC et ouverture d'une fenêtre de navigateur.
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.payline.com/");
		selenium.start();
	}

	/**
	 * Suite de tests.
	 * @throws Exception
	 */
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

	/**
	 * Fermeture de la fenêtre du navigateur.
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDown() throws Exception {
		selenium.stop();
	}

	/**
	 * Classe de la règle qui effectue les captures en cas d'erreur.
	 * @author Charlélie Bouvier - Monext 04/2015
	 *
	 */
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
