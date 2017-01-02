package com.example.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class SampleJSon {

	public static void main(String[] args) {
		 
		org.json.simple.JSONObject jsonObject=new org.json.simple.JSONObject();
		jsonObject.put("Object","String Object");

		HashMap<String,String> list = new HashMap<String,String>();
		            list.put("john","john");
		            list.put("mat","mat");
		            list.put("jason","jason");
		            list.put("matthew","matthew");

		            jsonObject.put("List",list);
		            
		            System.out.println("List---"+jsonObject);
	}

}
