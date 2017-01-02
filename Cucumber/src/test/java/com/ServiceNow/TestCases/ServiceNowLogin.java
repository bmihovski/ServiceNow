package com.ServiceNow.TestCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ServiceNow.Utils.Xls_Reader;
import com.ServiceNow.api.ConstantsValue;
import com.ServiceNow.hybrid.KeyWords;
import com.service.apis.ServiceNowAPIS;
import com.service.config.ApiPaths;
import com.service.config.AppConfiguration;
import com.test.utils.ConvertFromCSVtoXlsx;
import com.test.utils.FinalPDFReport;


public class ServiceNowLogin {
	private String sTestcaseName;
	
	@BeforeClass
	public void initilazation() throws ParseException, IOException{

		//TestSuite CSV file Genaration 
		ServiceNowAPIS sapis=new ServiceNowAPIS();
 		String reponse=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSuites,ConstantsValue.displayTestSuiteparmaeters);
		String fileName_testSuite="TestSuite";
		sapis.convertFromJSONToCSV(reponse,"result",fileName_testSuite);
	 
		//TestCase CSV file Genaration   
		String reponse_testcase=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testCases,ConstantsValue.displayTestCaseparmaeters);
		String fileName_testCases="TestCase";
		sapis.convertFromJSONToCSV(reponse_testcase,"result",fileName_testCases);
		
		//TestCase CSV file Genaration 
		 String reponse_testSteps=sapis.getTestDetails(AppConfiguration.baseUrl,ApiPaths.testSteps,ConstantsValue.displayTestStepparameters);
		 String fileName_testSteps="TestSteps";
		 sapis.convertFromJSONToCSV(reponse_testSteps,"result",fileName_testSteps);
	 
		 ConvertFromCSVtoXlsx cfs=new ConvertFromCSVtoXlsx();
			List<String> filepathList=new ArrayList<String>();
			filepathList.add(ConstantsValue.filepath+fileName_testSuite+".csv");
			filepathList.add(ConstantsValue.filepath+fileName_testCases+".csv");
			filepathList.add(ConstantsValue.filepath+fileName_testSteps+".csv");
			
			
			
			  int size = filepathList.size();
			   XSSFWorkbook workBook = new XSSFWorkbook();
		        for (int i = 0; i < size; i++) {
		            cfs.csvToXLSX(workBook,filepathList.get(i).toString(), "sheet" + i + 1);
		        }
			
		}
		
	
	@BeforeMethod
	public void start(){
 
	}
	
  @Test(description="TC_001_ServiceNow Login TestCases")    
	public void TC_001_ServiceNow_Login_TestCases() throws ParseException, IOException, InterruptedException{
	  
	  sTestcaseName = new Object() {}.getClass().getEnclosingMethod().getName();
	  
		Xls_Reader  xls=new Xls_Reader(ConstantsValue.TestDataFile);
		KeyWords app=new KeyWords();
		 List<Map<String, String>> listresult=app.executeKeywords("49b09ce3db23220075277befbf961929","f8d0dc27db23220075277befbf9619ce", xls);
		 System.out.println("Result testcase parameter--->"+listresult);
		 listresult.get(0).put("TestCaseResult", "Passed");
			FinalPDFReport f=new FinalPDFReport();
			f.createResultPDF(listresult.get(0),  listresult.get(1),   listresult.get(2) ,sTestcaseName);
			System.out.println("PDF generated");
			
	}
 @Test(description="Crreate Incident in ServiceNow app")
	public void incidentCreation() throws InterruptedException{
		Xls_Reader  xls=new Xls_Reader(ConstantsValue.TestDataFile);
		KeyWords app=new KeyWords();
		List<Map<String, String>> listresult=app.executeKeywords("49b09ce3db23220075277befbf961929","584fa230db33220075277befbf96198c", xls);

		
	}
	
	//@Test
	public void updateIncident(){
		Xls_Reader  xls=new Xls_Reader(ConstantsValue.TestDataFile);
		KeyWords app=new KeyWords();
		//serviceNowLogin();
	//	app.executeKeywords("584fa230db33220075277befbf96198c", xls);
	}
	//@Test
	public void reateChange(){
		Xls_Reader  xls=new Xls_Reader(ConstantsValue.TestDataFile);
		KeyWords app=new KeyWords();
	//	serviceNowLogin();
	//	app.executeKeywords("584fa230db33220075277befbf96198c", xls);
		
	}
	@AfterTest
	public void generatePDFReport(){
		 
	}
}
