package com.payline.test;

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
		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("com.sun.net.ssl.checkRevocation", "false");
		WsdlProject project = new WsdlProject("src/test/resources/Jenkins-soapui-project.xml"); 
		TestSuite testSuite = project.getTestSuiteByName( "NR FDJ" ); 
		TestCase testCase = testSuite.getTestCaseByName( "T01" );

		TestRunner runner = testCase.run( new PropertiesMap(), false );
		Assert.assertEquals(Status.FINISHED, runner.getStatus());
	}
}
