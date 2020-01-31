package Utilities;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import testRunner.TestRunner;

public class Reports {

	WebDriver driver;
	
	public Reports(WebDriver driver) {
		this.driver = driver;
	}
	
	//TestRunner runner = new TestRunner();
	
	
	//Reusable function to add step logs and screenshots to HTML report
	public void addTestLog(String stepLog) {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String date = sdf.format(new Date()).replace(";","-").replace(":","-");
		
		String screenshotName = driver.getTitle().replaceAll("\\W", " ");
		File filepath2 = TestRunner.testResults1;
		try {
			File sourcepath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(filepath2 + File.separator + screenshotName + "_"+ date + ".png");
			Files.copy(sourcepath, destinationPath);
			
			Reporter.addStepLog(stepLog);
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
		}catch(IOException e) {
			
		}
	}
	
//	public void add_log(String status, String message){
//		if(status.equals("PASS")) {
//				TestRunner.test.log(LogStatus.PASS, message);
//				//addTestLog(message);
//				
//
//		}
//		else if(status.equals("FAIL")){
//			TestRunner.test.log(LogStatus.FAIL, message);
//			//addTestLog(message);
//		}
//	}
	
	
//	public static String capture(WebDriver driver) throws IOException {
//		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
//		String date = sdf.format(new Date()).replace(";","-").replace(":","-");
//		
//		String screenshotName = driver.getTitle().replaceAll("\\W", " ");
//		File filepath2 = TestRunner.testResults1;
//			File sourcepath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			File destinationPath = new File(filepath2 + File.separator + screenshotName + "_"+ date + ".png");
//			Files.copy(sourcepath, destinationPath);
//		String errflpath = destinationPath.getAbsolutePath();
//		FileUtils.copyFile(sourcepath, destinationPath);
//		return errflpath;
//	}
}
