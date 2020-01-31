package Managers;

import org.openqa.selenium.WebDriver;

import PageObjects.HomePage;
import SupportLibraries.ReusableLibrary;
import Utilities.Reports;

public class PageObjectManager {

	private WebDriver driver;
	private HomePage homePage;
	private Reports reports;
	private ReusableLibrary reusableLibrary;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	//returns the instance of HomePage(package: PageObjects)
	public HomePage getHomePage() {
		return(homePage==null)? homePage = new HomePage(driver) : homePage;
	}
	
	
		//returns the instance of Reports(package: Utilities)
		public Reports getreports() {
			return(reports==null)? reports = new Reports(driver) : reports;
		}
		
	
	//returns the instance of ReusableLibrary(package: SuppoerLibraries)
	public ReusableLibrary getReusableLibrary() {
		return(reusableLibrary==null)? reusableLibrary = new ReusableLibrary(driver) : reusableLibrary;
	}


}
