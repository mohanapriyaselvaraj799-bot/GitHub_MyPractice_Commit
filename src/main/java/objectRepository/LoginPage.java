package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtility.WebDriverUtility;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindAll({ @FindBy(id = "username"), @FindBy(name = "username") })
	private WebElement UN;

	@FindBy(id = "inputPassword")
	private WebElement PW;

	@FindBy(xpath = "//button[text()='Sign In']")
	private WebElement Loginbtn;

	public WebElement getUN() {
		return UN;
	}

	public WebElement getPW() {
		return PW;
	}

	public WebElement getLoginbtn() {
		return Loginbtn;
	}

	public void Login(String url, String username, String password, WebDriver driver) {
		WebDriverUtility wdu = new WebDriverUtility();
		driver.manage().window().maximize();
		driver.get(url);
		wdu.waitforPageLoad(driver);

		LoginPage lp = new LoginPage(driver);
		lp.getUN().sendKeys(username);
		lp.getPW().sendKeys(password);
		lp.getLoginbtn().click();
	}

}
