package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	WebDriver driver;
	
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//span[text()='Add Product']")
	private WebElement addProductbtn;
	
	@FindBy(name="productName")
	private WebElement productname;
	
	@FindBy(name="productCategory")
	private WebElement productcategory;
	
	@FindBy(name="quantity")
	private WebElement quantity;
	
	@FindBy(name="price")
	private WebElement price;
	
	@FindBy(name="vendorId")
	private WebElement vendor;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement submitproductbtn;

	public WebElement getAddProductbtn() {
		return addProductbtn;
	}

	public WebElement getProductname() {
		return productname;
	}

	public WebElement getProductcategory() {
		return productcategory;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public WebElement getVendor() {
		return vendor;
	}

	public WebElement getSubmitproductbtn() {
		return submitproductbtn;
	}
	
	
	

}

