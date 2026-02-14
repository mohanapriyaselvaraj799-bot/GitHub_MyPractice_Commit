package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
WebDriver driver;

public HomePage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignlink;
	
	@FindBy(linkText="Products")
	private WebElement productlink;
	
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement campaignbtn;
	
	@FindBy(xpath="//div[@class='user-icon']")
	private WebElement usericon;
	
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement logoutbtn;

	public WebElement getCampaignlink() {
		return campaignlink;
	}

	public WebElement getProductlink() {
		return productlink;
	}

	public WebElement getCampaignbtn() {
		return campaignbtn;
	}

	public WebElement getUsericon() {
		return usericon;
	}

	public WebElement getLogoutbtn() {
		return logoutbtn;
	}
	
	
	

}



