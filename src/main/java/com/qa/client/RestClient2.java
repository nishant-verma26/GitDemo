package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

//This is a client class to write all types of method required to be performed on API.
public class RestClient2 {


	//1. GET method (With URL only and no header)
	// HttpClients is a class which is having methods createDefault which will create a http client. 
	public CloseableHttpResponse get(String url) {
		CloseableHttpResponse httpresponse=null;
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);		//Create a get connection with the URL.
		try {
			httpresponse = httpclient.execute(httpget);		// hit the get URL.

		} catch (ClientProtocolException e) {  		//httpresponse will have all the response which it returns.
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpresponse;

	}

	//2. GET method (With URL only and header)
	public CloseableHttpResponse get(String url, HashMap<String,String> headermap) {
		CloseableHttpResponse httpresponse=null;
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);		
		try {
			for (Map.Entry<String,String> entry : headermap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			httpresponse = httpclient.execute(httpget);

		} catch (ClientProtocolException e) {  		
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpresponse;

	}


	//3. POST method
	public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headermap) {
		CloseableHttpResponse httpresponse=null;
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);

		for (Map.Entry<String,String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(),entry.getValue());
		}

		try {
			httppost.setEntity(new StringEntity(entityString));				//SetEntity used for payload
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			httpresponse = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return httpresponse;			
	}
}
