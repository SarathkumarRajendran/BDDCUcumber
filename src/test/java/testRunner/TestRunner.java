package testRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import Managers.FileReaderManager;
import Managers.PageObjectManager;
import Utilities.Reports;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
features = "Feature"
,glue={"stepDefinition"}
,tags= {"@SmokeTest"}
//,dryRun=true
,plugin= {"com.cucumber.listener.ExtentCucumberFormatter:"}

)

public class TestRunner {

	public static File testResults;
	public static File testResults1;
	public static String userpath = System.getProperty("user.dir");
	
	//setup function to configure report path
	@BeforeClass
	public static void setup() {
	
		File filepath = createFolder();
		ExtentProperties extentproperties = ExtentProperties.INSTANCE;
		extentproperties.setReportPath(filepath + File.separator + "report.html");
				
	}
	
	//Function to create Folder structure for HTML report
	public static File createFolder() {
						
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String date = sdf.format(new Date()).replace(";","-").replace(":","-");
		testResults = new File(userpath + File.separator + "output" + File.separator + date );
		System.out.println(testResults);
		if(!testResults.exists()) {
			testResults.mkdirs();
			System.out.println("folder is created");
		}
		
		testResults1 = new File(testResults + File.separator + "screenshots");
		System.out.println(testResults);
		if(! testResults1.exists()) {
			testResults1.mkdirs();
			System.out.println("screenshot folder is created");
		}
		return testResults;
	}
	
	//Function to add system information to the report
	@AfterClass
	public static void setupAfter() {
		Reporter.loadXMLConfig(new File(userpath + File.separator + "configs/extent-config.xml"));
		Reporter.setSystemInfo("user name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		
	}
	
	
}
