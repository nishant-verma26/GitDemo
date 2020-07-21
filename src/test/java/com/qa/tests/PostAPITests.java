package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient2;
import com.qa.user.UserData;

public class PostAPITests extends TestBase{

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
	public void postUrl() throws JsonGenerationException, JsonMappingException, IOException{
		restClient2 = new RestClient2();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type","Application/Json");

		//A user.java class will be created equivalent to the payload and then convert java class into JSON as payload accepts json.
		// (Marshelling : Convert java class to JSON Object). This is done using utility named as jackson API.

		ObjectMapper mapper = new ObjectMapper();					// Object of mapper class
		UserData user = new UserData("Codename1","Codejob1");		// Object of user class.

		//Object to json conversion
		mapper.writeValue(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\data\\users.json"), user); 

		//Object to json in string
		String userjsonstring = mapper.writeValueAsString(user);
		System.out.println(userjsonstring);

		httpresponse = restClient2.post(uri, userjsonstring, headermap);

		//a. Status Code
		int statuscode = httpresponse.getStatusLine().getStatusCode();
		System.out.println("The status code is : "+statuscode);

		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_201,"Status code is not :"+RESPONSE_STATUS_CODE_201+".");

		//b. Response
		String response = EntityUtils.toString(httpresponse.getEntity(),"UTF-8");		// This will returns the response in form of string.
		System.out.println("The response is :"+response);

		JSONObject json = new JSONObject(response);
		System.out.println("The response in JSON format is :"+json);

		UserData userresObj = mapper.readValue(response,UserData.class);		//Unmarshelling. JSON Object to JAVA object
		System.out.println(userresObj);
		
		Assert.assertEquals(user.getName().equals(userresObj.getName()),true);
		Assert.assertEquals(user.getJob().equals(userresObj.getJob()),true);
		
		System.out.println(user.getName().equals(userresObj.getName()));
		System.out.println(user.getJob().equals(userresObj.getJob()));
		System.out.println(userresObj.getId());
		System.out.println(userresObj.getCreatedAt());
	}


}
