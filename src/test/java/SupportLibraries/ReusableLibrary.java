package SupportLibraries;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import Managers.PageObjectManager;
import Managers.WebDriverManager;
import Utilities.Reports;

import org.simmetrics.StringMetric;
import org.simmetrics.StringMetricBuilder;
import org.simmetrics.StringMetrics;

public class ReusableLibrary  {

	WebDriver driver;

	
	public ReusableLibrary(WebDriver driver) {
		this.driver = driver;

	}
	
	//Reusable function to wait till the element exists using Explicit wait
	public void waitTillElementExists(WebElement WebElement, String Name){
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		        wait.until(ExpectedConditions.elementToBeClickable(WebElement));
	           // reports.addTestLog(Name + " Element is available");
		       // Reporter.addStepLog(Name + " Element is available");
	        	
	}
	
	//Reusable function to verify the visibility of element
	public boolean isElementVisible(WebElement webElement) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        try {
        	
              if (webElement.isDisplayed()) {
                return true;
            }
            return false;
        } catch(Exception e) { 
        	System.out.println("Exception - " +e.getMessage());
        	e.printStackTrace();
        	return false; 
        }
    }
		  
	//Reusable function to verify that the element is present
	public boolean isElementPresent(WebElement webelement) {

	         driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	                
			       try {
					          if (((webelement))!=null) { 
					        	 return true; 
					        	 }
					          return false;
			        } 
			       catch(Exception e) { 
			        	System.out.println("Exception - " +e.getMessage());
			        	e.printStackTrace();
			        	return false;
			        	}
				    
	 }  
	
	//Reusable function to pass an input to the text box
	public void inputValue(WebElement webElement,String input, String msgValue){
				webElement.isEnabled();
				webElement.clear();
				webElement.sendKeys(input);	
				Reporter.addStepLog(msgValue + " is entered successfully");
							
	}
	
	//Reusable function to click the element
	public void clickElement(WebElement webElement, String value){
				waitTillElementExists(webElement,value);
				webElement.click();	
				Reporter.addStepLog(value + " is clicked successfully");
				
	}
	
	//Reusable function to wait for particular time using Implicit wait
	public void driverWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	
	//Reusable function to verify that the element is present
	public void verifyElementPresent(WebElement webElement, String value){
		if(webElement.isDisplayed()) {
		Reporter.addStepLog(value + " is displayed successfully");
		}
		else {
			Reporter.addStepLog(value + " is not displayed successfully");
		}
	}
	
	//Reusable function to get and verify the field values
	public void verifyFields(WebElement webElement,String Name){
		if(isElementPresent(webElement)){
			String value=webElement.getText();	
			Reporter.addStepLog(""+Name+" field is displayed with the value "+value+" successfully");
		}else{
			Reporter.addStepLog(""+Name+" field is not displayed with the value successfully");
		}
		}	
	
	//Reusable function to scroll to particular element using javascript executor
	public void pageScroll(WebElement webElement)
	{
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    WebElement ElementToScroll=webElement;
	    jse.executeScript("arguments[0].scrollIntoView()",ElementToScroll );
			
	}
	
	//Reusable function to click an element using javascript executor
	public void JExecuterClick(WebElement webElement)
	{
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    WebElement ElementToScroll=webElement;
	    jse.executeScript("arguments[0].click()",ElementToScroll );
			
		
	}
	
	//Reusable function to verify and accept an alert 
	public boolean isAlertPresent() {
		boolean foundAlert = false;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
        wait.until(ExpectedConditions.alertIsPresent());
        foundAlert = true;
		}catch (TimeoutException eTo){
			foundAlert = false;
			
		}
		    return foundAlert;
	}
	
	//Reusable function to click an element using actions class 
	public void actionsClick(WebElement webElement, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        
		new Actions(driver).moveToElement(webElement).click(webElement).build().perform();
		
		Reporter.addStepLog(value+" is clicked successfully");
	}
	
	
	public float stringMetric(String str1, String str2) {
		StringMetric metric = StringMetrics.cosineSimilarity();
		float result = metric.compare(str1, str2)*100;
		System.out.println("comparison pewrcentage is: " + result);
		return result;
		
	}
}
