package baseTest;

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
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.HomePage;
import objectRepository.LoginPage;


public class BaseClass {
	protected WebDriver driver;
	public static WebDriver sdriver = null;
	protected PropertiesFileUtility pfu;
	protected LoginPage lp;
	protected HomePage hp;
	protected WebDriverUtility wdu;

	@BeforeSuite(groups = { "smoke", "regression" })
	public void beforeSuite() {
		Reporter.log("Db Open", true);
	}

	@AfterSuite(groups = { "smoke", "regression" })
	public void afterSuite() {
		Reporter.log("Db close", true);
	}

	// @Parameters("BROWSER")
	@BeforeClass(groups = { "smoke", "regression" })
	// public void beforeClass(String BROWSER) throws Throwable {
	public void beforeClass() throws Throwable {
		pfu = new PropertiesFileUtility();
		String BROWSER = pfu.toReadDatafromPropertiesFile("browser");
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
		sdriver=driver;
		Reporter.log("launching browser", true);
	}

	@AfterClass(groups = { "smoke", "regression" })
	public void afterClass() {
		driver.quit();
		Reporter.log("close browser", true);
	}

	@BeforeMethod(groups = { "smoke", "regression" })
	public void beforeMethod() throws Throwable {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		wdu = new WebDriverUtility();
		String URL = pfu.toReadDatafromPropertiesFile("url");
		String UN = pfu.toReadDatafromPropertiesFile("username");
		String PW = pfu.toReadDatafromPropertiesFile("password");
		driver.get(URL);
		driver.manage().window().maximize();
		wdu.waitforPageLoad(driver);
		lp.getUN().sendKeys(UN);
		lp.getPW().sendKeys(PW);
		lp.getLoginbtn().click();

		Reporter.log("login", true);
	}

	@AfterMethod(groups = { "smoke", "regression" })
	public void afterMethod() {

		WebElement logoutButton = hp.getUsericon();
		wdu.mouseHoverOnWebElement(logoutButton, driver);
		hp.getLogoutbtn().click();

		Reporter.log("logout", true);
	}

}
