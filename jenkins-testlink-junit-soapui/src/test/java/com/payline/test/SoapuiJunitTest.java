package com.payline.test;

import java.net.ProxySelector;
import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.model.testsuite.TestSuite;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Charlélie Bouvier - Monext 04/2015
 * Squelette pour test SoapUI
 *
 */
public class SoapuiJunitTest {

	/**
	 * 
	 * @throws Exception
	 * Récupère un projet xml SoapUI, crée une instance WsdlProject et execute chacun des tests.
	 */
	@Test
	public void testRunner() throws Exception 
	{
		ProxySelector proxy = ProxySelector.getDefault(); // On récupère le proxy courant.
		WsdlProject project = new WsdlProject("file:///"+System.getProperty("user.dir")+"/src/test/resources/project-t02-groovy.xml");
		ProxySelector.setDefault(proxy); //On set le proxy précédemment enregistré car l'instanciation d'un WsdlProject efface le proxy, et cause des problèmes avec FirefoxWebdriver. 
		List<TestSuite> testSuites = project.getTestSuiteList();
		for(TestSuite ts : testSuites){
			List<TestCase> testCases = ts.getTestCaseList();
			for(TestCase tc : testCases){
				TestRunner runner = tc.run(new PropertiesMap(), false);
				Assert.assertEquals(Status.FINISHED, runner.getStatus());
			}
		}
	}
}
