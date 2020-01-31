package stepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import Managers.FileReaderManager;
import Managers.PageObjectManager;
import Managers.WebDriverManager;
import PageObjects.HomePage;
import Utilities.Reports;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dataProvider.ConfigFileReader;


public class TestSteps {
	
	
	WebDriver driver;
	HomePage hmpg;
	Reports reports;
	PageObjectManager pageObjectManager;
	WebDriverManager webDriverManager;
	ConfigFileReader configFileReader = new ConfigFileReader();

	//Hooks to be executed at the start of each scenario for Initializing browser and object instances
	@Before ("@SmokeTest, @RegressionTest")
	public void beforeScenario() {
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		pageObjectManager = new PageObjectManager(driver);
		hmpg = pageObjectManager.getHomePage();
	}
	
	@Given("^I launches the news site$")
	public void user_launches_the_news_site() throws Throwable {
	   driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		//driver.get("http://spezicoe.wipro.com:81/opencart1/");
	   reports = pageObjectManager.getreports();
	   	String title = driver.getTitle();
		assertEquals("News site is not launched successfully", "News | The Guardian", title);		
		reports.addTestLog("News site is launched successfully");	
	 	   
	}

	@When("^I clicks on first news link$")
	public void user_clicks_on_Register_link() throws Throwable {
		 hmpg.user_clicks_news_first_link();
	}
	
	@Then("^I read the news headline$")
	public void i_read_the_news_headline() throws Throwable {
		 hmpg.user_reads_headline();
	}
	
	@Then("^I search news headline in some other sources$")
	public void i_search_news_headline_in_some_other_sources() throws Throwable {
		hmpg.user_search_news();
	}
	
	@Then("^I search some other sources with random news text$")
	public void i_search_other_sources_random() throws Throwable {
		hmpg.user_search_news_random();
	}

	@Then("^I verify the content by using highest matching percentage of headline words$")
	public void i_verify_the_content_is_valid_by_using_highest_matching_percentage_of_headline_words() throws Throwable {
		hmpg.check_news_content();
	}
	
	//Hooks to be executed at the end  of each scenario for taking screenshots in case of failure and to close browser
	@After ("@SmokeTest, @RegressionTest")
	public void afterScenario(Scenario scenario) {
		if(scenario.isFailed()) {
			reports.addTestLog("failed");
		}
		webDriverManager.closeDriver();
	}
	
	
}
