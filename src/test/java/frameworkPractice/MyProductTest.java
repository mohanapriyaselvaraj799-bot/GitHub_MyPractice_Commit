package frameworkPractice;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTestPractice.DriverManager;
import baseTestPractice.MybaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.ProductPage;

@Listeners(listenerUtilityPractice.MyListenerImplementation.class)
public class MyProductTest extends MybaseClass {
	@Test
	public void createProductTest() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(DriverManager.getDriver());
		ProductPage pp = new ProductPage(DriverManager.getDriver());
		CampaignPage cp = new CampaignPage(DriverManager.getDriver());

		hp.getProductlink().click();

		// click AddProduct
		pp.getAddProductbtn().click();

		String productname = efu.toReadDatafromExcelFileUtility("Product", 1, 2);
		String quantity = efu.toReadDatafromExcelFileUtility("Product", 1, 3);
		String price = efu.toReadDatafromExcelFileUtility("Product", 1, 4);
		String product_category = efu.toReadDatafromExcelFileUtility("Product", 1, 5);
		String vendor_id = efu.toReadDatafromExcelFileUtility("Product", 1, 6);

		// Add RandomNum
		int randomNum = jfu.toReadRandomNumber();

		// add product
		pp.getProductname().sendKeys(productname + randomNum);
		WebElement category = pp.getProductcategory();
		wdu.selectDropdownByValue(category, DriverManager.getDriver(), product_category);

		WebElement quantity1 = pp.getQuantity();
		quantity1.clear();
		quantity1.sendKeys(quantity);

		WebElement price1 = pp.getPrice();
		price1.clear();
		price1.sendKeys(price);

		WebElement vendor = pp.getVendor();
		wdu.selectDropdownByValue(vendor, DriverManager.getDriver(), vendor_id);

		pp.getSubmitproductbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(DriverManager.getDriver(), toastmsg);

		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(productname));
		System.out.println("Product Created Successfully");
		
		}

	}



