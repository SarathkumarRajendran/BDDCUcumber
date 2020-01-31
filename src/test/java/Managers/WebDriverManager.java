package Managers;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import enums.DriverType;
import testRunner.TestRunner;

public class WebDriverManager {

	public WebDriver driver;
	public static DriverType driverType;
		String userpath1 = TestRunner.userpath;
		
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
	
	public WebDriverManager() {
		
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		
	}
	
	//returns the driver
	public WebDriver getDriver() {
		if(driver == null) driver = createLocalDriver();
		return driver;
	}
	
	//Initializing the driver
	private WebDriver createLocalDriver() {
		switch(driverType) {
		case FIREFOX: 
			System.setProperty(FIREFOX_DRIVER_PROPERTY, userpath1 + File.separator + "DriverFiles/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		break;
		
		case CHROME: 
			System.setProperty(CHROME_DRIVER_PROPERTY, userpath1 + File.separator + "DriverFiles/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		break;
		
		case INTERNETEXPLORER: 
			System.setProperty(IE_DRIVER_PROPERTY, userpath1 + File.separator + "DriverFiles/InternetExplorerdriver.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		break;
		}
		
		return driver;
	}
	
//	public WebDriver getWebDriver() {
//		return driver;
//	}
	
	//close driver
	public void closeDriver() {
		driver.quit();
	}
	
}
