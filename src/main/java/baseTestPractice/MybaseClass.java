package baseTestPractice;


import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.HomePage;
import objectRepository.LoginPage;


public class MybaseClass {
	
	protected PropertiesFileUtility pfu;
	protected LoginPage lp;
	protected HomePage hp;
	protected WebDriverUtility wdu;

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("Db Open", true);
	}

	@AfterSuite
	public void afterSuite() {
		Reporter.log("Db close", true);
	}

	
	@BeforeClass
	@Parameters("BROWSER")
	public void beforeClass(@Optional("Chrome")String BROWSER) throws Throwable {
		WebDriver driver =null;
		pfu = new PropertiesFileUtility();
		//String BROWSER = pfu.toReadDatafromPropertiesFile("browser");
		if (BROWSER.equals("Chrome"))

		{
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> pref = new HashMap<>();
			pref.put("profile.password_manager_leak_detection", false);
			settings.setExperimentalOption("prefs", pref);
			driver = new ChromeDriver(settings);

		} else if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
			
		}
		DriverManager.setDriver(driver);
		
		Reporter.log("launching browser", true);
	}

	@AfterClass
	public void afterClass() {
		DriverManager.getDriver().quit();
		DriverManager.unload();
		Reporter.log("close browser", true);
	}

	@BeforeMethod
	public void beforeMethod() throws Throwable {
		lp = new LoginPage(DriverManager.getDriver());
		hp = new HomePage(DriverManager.getDriver());
		wdu = new WebDriverUtility();
		String URL = pfu.toReadDatafromPropertiesFile("url");
		String UN = pfu.toReadDatafromPropertiesFile("username");
		String PW = pfu.toReadDatafromPropertiesFile("password");
		DriverManager.getDriver().get(URL);
		DriverManager.getDriver().manage().window().maximize();
		wdu.waitforPageLoad(DriverManager.getDriver());
		lp.getUN().sendKeys(UN);
		lp.getPW().sendKeys(PW);
		lp.getLoginbtn().click();

		Reporter.log("login", true);
	}

	@AfterMethod
	public void afterMethod() {

		WebElement logoutButton = hp.getUsericon();
		wdu.mouseHoverOnWebElement(logoutButton, DriverManager.getDriver());
		hp.getLogoutbtn().click();
		Reporter.log("logout", true);
	}
}

