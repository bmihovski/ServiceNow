package com.ServiceNow.hybrid;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ServiceNow.api.ConstantsValue;

import junit.framework.Assert;

public class GeneralKeywords {

	
	
	WebDriver driver;
	public GeneralKeywords(){
		openBrowser("chrome");
		
	}
	public void openBrowser(String browerType){
			if(browerType.equals("firefox")){
				
				driver=new FirefoxDriver();
				
			}
			else if(browerType.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/neslavath/Desktop/Cucumber/Cucumber/chromedriver_win32/chromedriver.exe");
				 
				// Initialize browser
				  driver=new ChromeDriver();
			}
			else if(browerType.equals("ie")){
				
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
	}
	
	public void openURL(String url ) throws InterruptedException{
		Thread.sleep(5000);
		driver.get(url);
		 
		Thread.sleep(5000);
		 
	}
	public void click(String locator ) throws InterruptedException  {
		Thread.sleep(5000);
	    WebElement element=getElement(locator);
        element.click();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	public void enterText(String locator,String data ) throws InterruptedException{
		Thread.sleep(5000);
		if(locator.equals("user_name@id")){
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		         element.clear();
		         element.sendKeys(data);
		          
		}else if (locator.equals("sys_display.incident.caller_id@id")){
			Thread.sleep(5000);
			switchToFrame("#gsft_main");
		       WebElement element=getElement(locator);
		        // element.clear();
		         element.sendKeys(data);
		          
		}
		else{
			Thread.sleep(5000);
	       WebElement element=getElement(locator);
	         element.clear();
	         element.sendKeys(data);
	          
		}
  
	}
	
	public void verifyText(String locator,String exceptedText){
		
	}
	/***************** Utitity Function ************************/
	
	public WebElement getElement(String locator){
		WebElement element=null;
		try{
		 if(locator.endsWith("@id"))
			   element=driver.findElement(By.id(locator.split("@")[0]));
	
		 else  if(locator.endsWith("@name"))
			 element= driver.findElement(By.name(locator.split("@")[0]));
		 else  if(locator.endsWith("@xpath"))
			 element=driver.findElement(By.xpath(locator.split("@")[0]));
		 else  if(locator.endsWith("@className"))
			 element=driver.findElement(By.className(locator.split("@")[0]));
		 else  if(locator.endsWith("@linkText"))
			 element=driver.findElement(By.linkText(locator.split("@")[0]));
		 else  if(locator.endsWith("@tagName"))
			 element=driver.findElement(By.tagName(locator.split("@")[0]));
		 else  if(locator.endsWith("@cssSelector"))
			 element=driver.findElement(By.cssSelector(locator.split("@")[0]));
		 else  if(locator.endsWith("@partialLinkText"))
			 element=driver.findElement(By.partialLinkText(locator.split("@")[0]));
		}catch(Exception ex){
			Assert.fail("Failure in Element Extraction ..."+locator.split("@")[0]);
		}
		return element;
		
	}
	
	public void switchToFrame(String locator)
	{

		WebElement frame = driver.findElement(By.cssSelector(locator));
		driver.switchTo().frame(frame);
		 
		
	}
	
	public String takeScreenShot(String stepName) {
		File scrFile=null;
		try{
			scrFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File(ConstantsValue.filepathScreenshot+stepName+".png"));
		}catch(Exception exception){
			exception.getMessage();
		}
		return scrFile.toString();
	}
	
}
