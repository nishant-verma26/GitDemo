package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.base.TestBase;
import com.qa.client.RestClient2;
import com.qa.util.RestAPIUtil;

public class GetAPITests2 extends TestBase{

	TestBase testBase;
	RestClient2 restClient2;
	String uri;
	CloseableHttpResponse httpresponse;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		uri = prop.getProperty("URL")+prop.getProperty("SERVICEURL");

	}
	
	@Test(priority = 1)
	public void geturlWithoutHeader() throws ParseException, IOException {
		restClient2 = new RestClient2();
		httpresponse = restClient2.get(uri);	
		
		

		//a. Status Code
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is : "+statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200,"Status code is not :"+RESPONSE_STATUS_CODE_200+".");
		
		//b. Response
		String response = EntityUtils.toString(httpresponse.getEntity());		// This will returns the response in form of string.
		System.out.println("The response is :"+response);
		
		JSONObject json = new JSONObject(response);
		System.out.println("The response in JSON format is :"+json);
		
		//Single value assertion.
		String s = RestAPIUtil.getValueByJSONPath(json,"per_page");
		System.out.println("The value in json is :"+s);
		Assert.assertEquals(s, "3");
		
		
		//Get value from JSONArray
		String arrval = RestAPIUtil.getValueByJSONPath(json, "/data[0]/last_name");
		System.out.println("The value from JSON array is :"+arrval);
		
		//c. Headers
		Header[] headers = httpresponse.getAllHeaders();		//covert array into hashmap just for easy maintanenace
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headers) {
			allheaders.put(header.getName(), header.getValue());
		}
		
		System.out.println("The headers are :"+allheaders);
		
	}


	
	@Test(priority = 2)
	public void geturlWithHeader() throws ParseException, IOException {
		restClient2 = new RestClient2();
		httpresponse = restClient2.get(uri);	
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		headermap.put("username", "Test123");
		

		//a. Status Code
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is : "+statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200,"Status code is not :"+RESPONSE_STATUS_CODE_200+".");
		
		//b. Response
		String response = EntityUtils.toString(httpresponse.getEntity());		// This will returns the response in form of string.
		System.out.println("The response is :"+response);
		
		JSONObject json = new JSONObject(response);
		System.out.println("The response in JSON format is :"+json);
		
		//Single value assertion.
		String s = RestAPIUtil.getValueByJSONPath(json,"per_page");
		System.out.println("The value in json is :"+s);
		Assert.assertEquals(s, "3");
		
		//Get value from JSONArray
		String arrval = RestAPIUtil.getValueByJSONPath(json, "/data[0]/last_name");
		System.out.println("The value from JSON array is :"+arrval);
		
		//c. Headers
		Header[] headers = httpresponse.getAllHeaders();		//covert array into hashmap just for easy maintanenace
		HashMap<String, String> allheaders = new HashMap<String, String>();
		for (Header header : headers) {
			allheaders.put(header.getName(), header.getValue());
		}
		System.out.println("The headers are :"+allheaders);
	}

}
