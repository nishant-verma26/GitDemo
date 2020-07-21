package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

//This is a client class to write all types of method required to be performed on API.
public class RestClient {

	
	//1. GET method (We just need one URL)
	// HttpClients is a class which is having methods createDefault which will create a http client. 
	public void get(String url) {
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);		//Create a get connection with the URL.
		try {
			CloseableHttpResponse httpresponse = httpclient.execute(httpget);		// hit the get URL.
			
			//a. Status Code
			int statuscode = httpresponse.getStatusLine().getStatusCode();
			System.out.println("The status code is : "+statuscode);
			
			//b. Response
			String response = EntityUtils.toString(httpresponse.getEntity());		// This will returns the response in form of string.
			System.out.println("The response is :"+response);
			
			JSONObject json = new JSONObject(response);
			System.out.println("The response in JSON format is :"+json);
			
			//c. Headers
			Header[] headers = httpresponse.getAllHeaders();		//covert array into hashmap just for easy maintanenace
			HashMap<String, String> allheaders = new HashMap<String, String>();
			for (Header header : headers) {
				allheaders.put(header.getName(), header.getValue());
			}
			
			System.out.println("The headers are :"+allheaders);
			
		} catch (ClientProtocolException e) {  		//httpresponse will have all the response which it returns.
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	//2.
}
