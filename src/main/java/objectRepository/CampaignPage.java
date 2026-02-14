package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	WebDriver driver;
	
	public CampaignPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(name="campaignName")
	private WebElement campaignname;
	
	@FindBy(name="targetSize")
	private WebElement target;
	
	@FindBy(name="campaignStatus")
	private WebElement camapignstatus;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expcloasedate;
	
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement createcampbtn;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath="//button[@aria-label='close']") //closing popup message
	private WebElement closemsg;

	public WebElement getCampaignname() {
		return campaignname;
	}

	public WebElement getTarget() {
		return target;
	}

	public WebElement getCamapignstatus() {
		return camapignstatus;
	}

	public WebElement getExpcloasedate() {
		return expcloasedate;
	}

	public WebElement getCreatecampbtn() {
		return createcampbtn;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getClosemsg() {
		return closemsg;
	}

	
	
}

