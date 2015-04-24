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

public class SoapuiJunitTest {

	@Test
	public void testRunner() throws Exception 
	{
		//WsdlProject project = new WsdlProject("src/test/resources/Jenkins-soapui-project.xml");
		ProxySelector proxy = ProxySelector.getDefault();
		WsdlProject project = new WsdlProject("src/test/resources/project-t02-groovy.xml");
		ProxySelector.setDefault(proxy);
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
