package com.service.apis;

 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.service.config.ApiPaths;
import com.service.config.AppConfiguration;
 
 
public class ServiceNowAPIS {
	
	public String getTestDetails(String url,String path) throws ParseException, IOException  {
		
	       String responseBody=null;
 		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(/*https://dev23996.service-now.com/ */ 
               new AuthScope(new HttpHost(AppConfiguration.httpHost)),
                new UsernamePasswordCredentials(AppConfiguration.apiUserName, AppConfiguration.apiPWd));
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build(); 
 
        try {
        	System.out.println("base url is ---"+url+path);
            HttpGet httpget = new HttpGet(url+path);
            httpget.setHeader("Accept", "application/json");
            System.out.println("Executing request " + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
           
                  responseBody = EntityUtils.toString(response.getEntity());
                  System.out.println("----------------------------------------"+responseBody);
         
            }catch(Exception e){
            	
            }
            
        } finally {
     
            httpclient.close();
        }
		return responseBody;


 	}
 
	public void convertFromJSONToCSV(String jsonString,String jsonArray,String filename ){

		  JSONObject output;
      	File file=new File("D:\\PDF_Test\\"+filename+".csv");
	        try {
	        	  
	
	          if(filename.equals("TestSuite")){
	            output = new JSONObject(jsonString);
	            JSONArray docs = output.getJSONArray(jsonArray);	         	            
	            String csv = CDL.toString(docs);  
	            FileUtils.writeStringToFile(file, csv);
	        	   }
	      
	            if(filename.equals("TestCase")){
	            	output = new JSONObject(jsonString);
	            	 JSONArray docsarray = new JSONArray();	            	 
	            	 docsarray = output.getJSONArray(jsonArray);
		            for (int i = 0, len = docsarray.length(); i < len; i++) {
		                JSONObject obj = (JSONObject) docsarray.getJSONObject(i).remove("u_test_suite");
		                 
		            }
		            String csvtestcase = CDL.toString(docsarray);            
		            FileUtils.writeStringToFile(file, csvtestcase);
	            }
	            
	            if(filename.equals("TestSteps")){
	            	output = new JSONObject(jsonString);
	            	 JSONArray docsarray = new JSONArray();	            	 
	            	 docsarray = output.getJSONArray(jsonArray);
		            for (int i = 0, len = docsarray.length(); i < len; i++) {
		                JSONObject obj = (JSONObject) docsarray.getJSONObject(i).remove("u_test_case");
		                 
		            }
		            String csvtestcase = CDL.toString(docsarray);            
		            FileUtils.writeStringToFile(file, csvtestcase);
	            }
	          
	            System.out.println("Successfully created with file name");
	        } catch (JSONException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }        
	    }

 
	
	public static void main(String[] args) throws IOException, HttpException {
 
		
		//TestSuite CSV file Genaration 
		ServiceNowAPIS sapis=new ServiceNowAPIS();
		  String reponse=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSuites);
		String Filename="TestSuite";
		sapis.convertFromJSONToCSV(reponse,"result",Filename);
		 
		//TestCase CSV file Genaration 
		 
		String reponse_testcase=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testCases);
		String fileName_testCases="TestCase";
		sapis.convertFromJSONToCSV(reponse_testcase,"result",fileName_testCases);
		
		//TestCase CSV file Genaration 
		 
		 String reponse_testSteps=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSteps);
		 String fileName_testSteps="TestSteps";
		 sapis.convertFromJSONToCSV(reponse_testSteps,"result",fileName_testSteps);
		 
		 
	}
 	
}