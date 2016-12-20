package com.ServiceNow.test;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ServiceNow.Utils.EmailUtility;
import com.ServiceNow.Utils.ReadAndWriteToJSON;
import com.ServiceNow.Utils.WebElements;
import com.ServiceNow.api.CreateUserDetails;
import com.ServiceNow.api.GetAction;
import com.ServiceNow.api.UserCreateApi;
import com.ServiceNow.beanfactory.BeanFactory;
import com.ServiceNow.beanfactory.PropertiesFactory;
import com.ServiceNow.setup.TestBase;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class StepDef extends TestBase  {

	private WebDriver driver = null;

	private WebDriverWait driverWait;
	// Properties object = null;

	String emailvalue;

	

	private static Logger logger = Logger.getLogger(StepDef.class);

	private Map<String, String> userdetailsmap;
	public Map<String, String> PropertiesMap;
	private BeanFactory beanfactory = new BeanFactory();
	private PropertiesFactory propertiesfactory;
	private WebElements webelements;
	private UserCreateApi userCreationApi;
	public static final String validData="allmandatory";
	public HashMap<String ,HashMap<String ,String>> serviceNowTestCase;
	public GetAction getaction;
	int i=1;
	
	@Before
	public void setUP() {
		if(i==1)
		//beanfactory = new BeanFactory();
		propertiesfactory = new PropertiesFactory();
		userdetailsmap = beanfactory.getUserDetails(); // testdata
		PropertiesMap = propertiesfactory.getObjectProperties(); // locators
		emailvalue = beanfactory.emailRandomValuesGeneration();
		new ReadAndWriteToJSON();
		userCreationApi = new UserCreateApi();
		long threadId = Thread.currentThread().getId();
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
		
	   
	}

	@Given("^I go to \"([^\"]*)\" on \"([^\"]*)\"$")
	public void I_go_to(String url, String Browser) throws HttpException, IOException {
		driver = openBrowser(Browser);
		driverWait = new WebDriverWait(driver, 10);
		new EmailUtility(driver, driverWait);
		webelements = new WebElements(driver, driverWait);
		System.out.println("I am going to " + url + " on " + Browser);
		logger.info("The driver value is " + Browser);
		logger.info("The url value is " + url);
		// webelements.openBrowser(sBROWSER);
		new HashMap<String ,HashMap<String ,String>> ();
		getaction= new GetAction();
		serviceNowTestCase= getaction.getRequest();
		webelements.navigateBrowser(serviceNowTestCase.get(url.split("#")[0]).get(url.split("#")[1]));
		//serviceNowTestCase.get(url.split("#")[0]).get(url.split("#")[1]);
	}

	@And("^I enter text into \"([^\"]*)\" as \"([^\"]*)\"$")
	public void I_enter_text_into(String objectprop, String text) throws Exception {
		
		//System.out.println("Webelemt" + webelements);
		//System.out.println("driverWait" + driverWait);
		//System.out.println("userdetailsmap.get(text)" + userdetailsmap.get(text));
	//	System.out.println("webelements.getpageTitle()" + webelements.getpageTitle());
		//String valuefrombean=beanfactory.getdata(text);
		if("step_2#u_field_name".equals(objectprop)){
		webelements.switchToFrame( driver);
		}
		Thread.sleep(5000);
		//webelements.enterText(PropertiesMap.get(objectprop), driverWait, userdetailsmap.get(text).toString());
		getaction= new GetAction();
		serviceNowTestCase= getaction.getRequest();
		System.out.println("-----%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% UserName-----"+serviceNowTestCase.get(objectprop.split("#")[0]).get(objectprop.split("#")[1]));
		
		System.out.println("-----%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Text-----"+serviceNowTestCase.get(text.split("#")[0]).get(text.split("#")[1]));

	 	webelements.enterText(serviceNowTestCase.get(objectprop.split("#")[0]).get(objectprop.split("#")[1]), driverWait,serviceNowTestCase.get(text.split("#")[0]).get(text.split("#")[1]));
	 	
	}

	@And("^I create user \"([^\"]*)\"$")

	public void I_Create_users(String users) {
		webelements.creatingusers(users);

	}

	@And("^I click on \"([^\"]*)\"$")
	public void I_click_on(String objectprop) throws HttpException, IOException {
		System.out.println("clicking on" + objectprop);
		webelements.click(propertiesfactory.getObjectProperties().get(objectprop));
		//webelements.click(propertiesfactory.getObjectProperties().get(objectprop));
		
	/*	getaction= new GetAction();
		serviceNowTestCase= getaction.getRequest();
		System.out.println("-----I Click on -----"+serviceNowTestCase.get(objectprop.split("#")[0]).get(objectprop.split("#")[1]));
		
		webelements.click(serviceNowTestCase.get(objectprop.split("#")[0]).get(objectprop.split("#")[1]));
		*/
	
	}
	
	@And("^I clicked \"([^\"]*)\"$")
			public void I_clicked(String objectprop) {
/*		webelements.clickUsingSwitch(propertiesfactory.getObjectProperties().get(objectprop), driverWait);
*/		System.out.println("clicking on" + objectprop);
   webelements.click_link(propertiesfactory.getObjectProperties().get(objectprop));
	
		}
			
	@And("^I want to switch window on \"([^\"]*)\"$")
	public void I_want_to_switch_window_on(String objectprop){
		webelements.shiftnewindow();
		System.out.println("I have switched to child window");
	}
	
	@And("^I get text for \"([^\"]*)\"$")
		public void I_get_text_for(String objectprop) throws Exception {
		System.out.println("Object displayed on screen" + objectprop);
		//PropertiesMap = propertiesfactory.getObjectProperties();
		//String stext=webelements.getTextfromscreen(PropertiesMap.get(objectprop));
		Thread.sleep(5000);
		PropertiesMap.put(objectprop, webelements.getTextfromscreen(PropertiesMap.get(objectprop)));
		//System.out.println("IS DISPLAYED----------"+PropertiesMap.get(objectprop));
		}
	
	// Amazon Application Specific
	@And("^I select Amazoncom \"([^\"]*)\"$")
	public void I_select_Amazoncom(String objectprop){
		System.out.println("clicking on" + objectprop);
		webelements.click( propertiesfactory.getObjectProperties().get(objectprop));
	}
	
	// Avenger Application Specific API
	static ArrayList<String> roleID;
	static HashMap<String ,String> responsedata;
	
	@And ("I create user from api \"([^\"]*)\"$")
	public void I_create_user_from_api(String userrole){
		System.out.println("I am in user creation API"+userrole);
		String authenToken =userCreationApi.doAuthorization();
		String acessToken=userCreationApi.doUserLoginApi(authenToken);
		CreateUserDetails createUserDetails = new CreateUserDetails();
		System.out.println("The role is"+userdetailsmap.get(userrole));
		roleID = userCreationApi.userRolesApi(acessToken,userdetailsmap.get(userrole));
		responsedata=userCreationApi.createUserAPI(roleID, validData, createUserDetails, createUserJSON(roleID.get(1)));
	}
	
	
	@And("I delete user from api \"([^\"]*)\"$")
	public void I_delete_user_from_api(ArrayList<String> acTokenAndUserId){
		System.out.println("I am in Delete user from API method");
		System.out.println("THe response data map is"+responsedata);
		userCreationApi.deleteUserApi(responsedata);
		
		
	}
	
	
	
	@And("I click enter on \"([^\"]*)\"$")
	public void I_click_enter_on(String objectprop){
		webelements.clickEnter(propertiesfactory.getObjectProperties().get(objectprop), driverWait);
	}
	
	@Then("^I Verify element displayed on Screen \"([^\"]*)\" is \"([^\"]*)\"$")
	public void I_Verify_element_displayed_on_Screen(String expected,String objectprop) throws InterruptedException{
		System.out.println("clicking on" + objectprop);
		PropertiesMap = propertiesfactory.getObjectProperties();
		/*System.out.println("The expected value sent is"+expected);
		System.out.println("object property is"+objectprop);
		System.out.println("Actual value"+webelements.isDisplayedWithoutException(PropertiesMap.get(objectprop)));*/
		//Assert.assertTrue(webelements.isDisplayedWithoutException(objectprop) == Boolean.getBoolean(expected));
		Thread.sleep(5000);
		Assert.assertEquals(expected,String.valueOf(webelements.isDisplayedWithoutException(PropertiesMap.get(objectprop))));
	}
	
	
	@Then("^Expected and Actual \"([^\"]*)\" as \"([^\"]*)\"$")
	public void Expected_And_Actual (String expectedResult, String ActualResult) {
		System.out.println("Expected" + expectedResult);
		System.out.println("Actual" + PropertiesMap.get(ActualResult));
		Assert.assertEquals(true,expectedResult.contains(PropertiesMap.get(ActualResult)));
		/*
		 * boolean result = webelements.isDisplayed(webElement) String
		 * actualResult = null;
		 * 
		 * if(result) actualResult="success"; else actualResult="failure";
		 * Assert.assertEquals(expectedResult, actualResult);
		 */
	}
	
	@Then("^Expected and Actual Is \"([^\"]*)\" as \"([^\"]*)\"$")
	public void Expected_And_Actual_Is (String expectedResult, String ActualResult) {
		System.out.println("Expected" + expectedResult);
		System.out.println("Actual" + PropertiesMap.get(ActualResult));
		Assert.assertEquals(expectedResult,ActualResult);
		/*
		 * boolean result = webelements.isDisplayed(webElement) String
		 * actualResult = null;
		 * 
		 * if(result) actualResult="success"; else actualResult="failure";
		 * Assert.assertEquals(expectedResult, actualResult);
		 */
	}

	@Then("^Expected and Actual \"([^\"]*)\" as \"([^\"]*)\" as \"([^\"]*)\"$"  )
	public void Expected_Actual(String expectedResult, String Actual, String i) {
		System.out.println("Expected" + expectedResult);
		System.out.println("Actual" + expectedResult);

		switch (Integer.parseInt(i)) {
		case INTEGER:
				Assert.assertEquals(Integer.valueOf(expectedResult), Integer.valueOf(Actual));
			break;
		case STRING:
			Assert.assertEquals(String.valueOf(expectedResult), String.valueOf(Actual));
			break;
		case DOUBLE:
			Assert.assertEquals(Double.valueOf(expectedResult),Double.valueOf(Actual));
			break;
		case FLOAT:
			Assert.assertEquals(Float.valueOf(expectedResult),Float.valueOf(Actual));
			break;
			
		default:
			
			break;
		}

		/*
		 * boolean result = webelements.isDisplayed(webElement) String
		 * actualResult = null;
		 * 
		 * if(result) actualResult="success"; else actualResult="failure";
		 * Assert.assertEquals(expectedResult, actualResult);
		 */
	}
	
	//Avenger Application Specific
	public JSONObject createUserJSON(String roleid) {
		JSONObject userDetailsJson = new JSONObject();
		userDetailsJson.put("email", userdetailsmap.get("avengerusercreationemail"));
		userDetailsJson.put("firstName", userdetailsmap.get("avengerusercreationfirstname"));
		userDetailsJson.put("language", userdetailsmap.get("avengeruserlanguage"));
		userDetailsJson.put("lastName",userdetailsmap.get("avengerusercreationlastname"));
		userDetailsJson.put("username", userdetailsmap.get("avengeruserusername"));
		JSONArray myArray = new JSONArray();
		myArray.add(roleid);
		userDetailsJson.put("roleIds", myArray);
		System.out.println("JOSN is " + userDetailsJson);
		return userDetailsJson;
	}

	
	
	@After
	public void embedScreenshot(Scenario scenario) {
		if(scenario.isFailed()) {
		try {
		scenario.write("Current Page URL is " + driver.getCurrentUrl());
		byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		
		scenario.embed(screenshot, "image/png");
		} 
		catch (WebDriverException somePlatformsDontSupportScreenshots) {
		System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		} 
		}
		webelements.browserQuit();
	}
}
