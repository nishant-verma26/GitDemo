package com.qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITests extends TestBase{

	TestBase testBase;
	RestClient restClient;
	String uri;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		uri = prop.getProperty("URL")+prop.getProperty("SERVICEURL");

	}
	
	@Test
	public void geturl() {
		restClient = new RestClient();
		restClient.get(uri);	
	}


}
