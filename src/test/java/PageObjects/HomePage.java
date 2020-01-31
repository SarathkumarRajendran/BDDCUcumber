package PageObjects;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Managers.PageObjectManager;
import Managers.WebDriverManager;
import SupportLibraries.ReusableLibrary;
import Utilities.Reports;

public class HomePage extends ReusableLibrary {

	WebDriver driver;
	Reports reports;
	PageObjectManager pageObjectManager;
		
	public static String news_headline;
	public static List<WebElement> searchResultsList;
	 LocalDate currentdate = LocalDate.now();
	 int currentDay = currentdate.getDayOfMonth();
     Month currentMonth = currentdate.getMonth();
     int currentYear = currentdate.getYear();
//     String date1 = currentdate + "-" + currentMonth + ""
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		pageObjectManager = new PageObjectManager(driver);
		reports = pageObjectManager.getreports();
	}

	//locating webElements
	@FindBy(how = How.XPATH, using = "//*[@id=\"31-january-2020\"]/div/div/div[1]/ul/li[1]/div/div/a")
	private WebElement news_first;
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'OK with that')]")
	private WebElement cookies_agree;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(@class,'content__headline')]")
	private WebElement news_heading;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Desktops')]")
	public WebElement lnk_Desktop;
	
	@FindBy(how = How.XPATH, using = "//input[@name='q']")
	public WebElement google_searchBox;
	
	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Desktops')]")
	public WebElement google_searchIcon;
	
	@FindBy(how = How.XPATH, using = "//div[@class='srg']/div//div/a/h3")
	public List<WebElement> google_searchResults; 
	
	
	/*
	 * Binding methods to above locators
	 * ---------------------------------
	 * ---------------------------------
	 */
	public void user_clicks_news_first_link() {
	      
		if(isElementPresent(cookies_agree)) {
			clickElement(cookies_agree, "cookies_agree");
		}
		
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		String news_title = (String) js.executeScript("return arguments[0].text;", news_first);
		System.out.println("news title is: " + news_title);
		
		JExecuterClick(news_first);
			
		String title = driver.getTitle();
		if(title.contains(news_title)) {
		reports.addTestLog("News link is opened successfully");	
		}
		else {
			reports.addTestLog("News link is not opened successfully");
		}
	}
	 
	
	public void user_reads_headline() {
		if(isElementPresent(news_heading)) {
			news_headline = news_heading.getText();
			System.out.println("news_headline is : " + news_headline);
			reports.addTestLog("News heading is captured successfully: " + news_headline);	
		}
		else {
		reports.addTestLog("News heading is not captured successfully");	
		}
	}
	
	public void user_search_news() {
		
		((JavascriptExecutor)driver).executeScript("window.open()");
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://google.com");
		google_searchBox.sendKeys(news_headline);
		google_searchBox.sendKeys(Keys.ENTER);
		
		searchResultsList = google_searchResults;
		int size = searchResultsList.size();
		System.out.println("size is : " + size);
		
		if(size>1) {
			reports.addTestLog("search results are retrieved successfully");
		}
		else {
			reports.addTestLog("search results are not retrieved successfully");
		}
	
	}
	
	public void user_search_news_random() {
		
		((JavascriptExecutor)driver).executeScript("window.open()");
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("http://google.com");
		google_searchBox.sendKeys("aaaaa bbbbbb ccccccc zzzzzz");
		google_searchBox.sendKeys(Keys.ENTER);
		
		searchResultsList = google_searchResults;
		int size = searchResultsList.size();
		System.out.println("size is : " + size);
		
		if(size>1) {
			reports.addTestLog("search results are retrieved successfully");
		}
		else {
			reports.addTestLog("search results are not retrieved successfully");
		}
	
	}
	
	public void check_news_content() {
		ArrayList<Float> matchingresult = new ArrayList<Float>();
		
		for(int i=0; i<searchResultsList.size(); i++) {
			System.out.println(searchResultsList.get(i).getText());
			matchingresult.add(stringMetric(searchResultsList.get(i).getText(),news_headline));
		}
		
		if(Collections.max(matchingresult)>=50) {
		
		List<WebElement> section = driver.findElements(By.xpath("//div[@class='bkWMgd']"));
		int sectionSize = section.size();
		System.out.println("section size is: " + sectionSize);
		int l=sectionSize;

		System.out.println("l position is: " + l);
		
		for(int j=1; j<=matchingresult.size(); j++) {
			System.out.println("new list size is: " + matchingresult.size());
			Float max1 = Collections.max(matchingresult);
			int index1 = matchingresult.indexOf(max1);
			System.out.println("Highest matching result index is : " + index1);
			int index2 = index1 + j;
			System.out.println("index for xpath is: " + index2);
			
			String urlText1 = driver.findElement(By.xpath("//*[@id=\"rso\"]/div["+l+"]/div/div["+index2+"]/div/div/div[1]/a/h3")).getText();
			System.out.println("google results matching url is : " + urlText1);
			
			if(urlText1.contains("theguardian")) {
				System.out.println("url is matched to guardian, so removing the result from index");
				matchingresult.remove(index1);
			}
			else {
				System.out.println("url is not matched to guardian, proceeding to open the news");
				WebElement link = driver.findElement(By.xpath("//*[@id=\"rso\"]/div["+l+"]/div/div["+index2+"]/div/div/div[1]/a/h3"));
				JExecuterClick(link);
				
				reports.addTestLog("News content is valid with maximum probability of: " +matchingresult.get(index1));
				break;
			}
		}}
		
				else {
					reports.addTestLog("News content is not valid with minimum probability of: " +Collections.max(matchingresult));
				}
				
		
//		if(driver.getPageSource().contains(news_headline))
//		{
//			reports.addTestLog("News content is valid");
//		}
//		else {
//			reports.addTestLog("News content is NOT valid");
//		}
		}
	
}
