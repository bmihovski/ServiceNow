package com.ServiceNow.hybrid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ServiceNow.Utils.Constants;
import com.ServiceNow.Utils.Xls_Reader;

public class KeyWords  {

	 
		
	public List<Map<String, String>> executeKeywords(String currentTestSutieExecutionString,String currentTestCaseExecution,Xls_Reader xls) throws InterruptedException{
		
		List<Map<String, String>> list = null;
		HashMap<String,String> testcaseDetails= new HashMap<String,String>();
    	testcaseDetails.put("tS", "TestSuite:");
		testcaseDetails.put("tc", "TestCases:");
		testcaseDetails.put("tStep", "TestSteps:");	
		
		LinkedHashMap<String,String> tSteps=new LinkedHashMap<String,String>();
		 
		LinkedHashMap<String,String> tStepsImages=new LinkedHashMap<String,String>();
		 
		
		GeneralKeywords app =new GeneralKeywords();
		
	 	//TestSuite Level Start
		int rowTestSuites=xls.getRowCount("TestSuite");
		for(int rTSNum=2;rTSNum<=rowTestSuites;rTSNum++){
			
			System.out.println("sys_id value ......"+xls.getCellData("TestSuite", "sys_id", rTSNum));
			System.out.println("u_active value ....."+xls.getCellData("TestSuite", "u_active", rTSNum));
		if(xls.getCellData("TestSuite", "sys_id", rTSNum).equals(currentTestSutieExecutionString)&&xls.getCellData("TestSuite", "u_active", rTSNum).equals("true")){
			testcaseDetails.put("tSdesc",xls.getCellData("TestSuite", "u_name", rTSNum));

			
		//TestCase Level Start
		int rowTestCase=xls.getRowCount("TestCase");
		for(int rTCNum=2;rTCNum<=rowTestCase;rTCNum++){
			if(xls.getCellData("TestCase", "sys_id", rTCNum).equals(currentTestCaseExecution)&&xls.getCellData("TestCase", "u_active", rTCNum).equals("true")){
				
				testcaseDetails.put("tCdesc",xls.getCellData("TestCase", "u_name", rTCNum));
				testcaseDetails.put("TestCaseResult", "Passed");
				
		//StepsLevel start...
  		int rows=xls.getRowCount("TestSteps");
  		int step=1;
		for(int rNum=2;rNum<=rows;rNum++){
	 
			String tcid=xls.getCellData("TestSteps", "u_test_case", rNum);
			String step_active=xls.getCellData("TestSteps", "u_active", rNum);
			if(tcid.equals(currentTestCaseExecution) && step_active.equals("true") ){
			String keyword=xls.getCellData("TestSteps", "u_step_type.u_name", rNum);
			String locator=xls.getCellData("TestSteps", "u_field_name", rNum);
			String data=xls.getCellData("TestSteps", "u_value", rNum);
			String u_number=xls.getCellData("TestSteps", "u_number", rNum);
			String u_name=xls.getCellData("TestSteps", "u_name", rNum);
			System.out.println(tcid+"----@-"+keyword+"--@-------"+locator+"--@----------"+data +"-@-------"+step_active);
				 
			tSteps.put("Step_"+step, xls.getCellData("TestSteps", "u_name", rNum));

		      if(keyword.equals("openURL")){
		 
				app.openURL(data);
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));

				step++;
		         }
			else if(keyword.equals("enterText")){
				app.enterText(locator, data );
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				step++;
			}
			else if(keyword.equals("click")){
				app.click(locator );
				tStepsImages.put("Step_"+step,app.takeScreenShot(u_name));
				step++;
			}
		}
			
		}
		//StepsLevel end...
			}else{
				
		///		TestCases Level Else condition
			}
		}//TestCase Level End
		}else{
			//TestSuite level Else condition
		}
		list=new ArrayList<Map<String, String>>();
		list.add(testcaseDetails);
		list.add(tSteps);
		list.add(tStepsImages);
		}//TestSuite Level End
		
		
		return list;
		
	}
	
}
