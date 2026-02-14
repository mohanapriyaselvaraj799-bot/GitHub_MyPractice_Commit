package com.ninza.crm.producttestngTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

@Listeners(listenerUtility.ListenerImplementation.class)
public class CreateProductClass extends BaseClass {
	
	//testing feature
	
	@Test(groups="regression")
	public void createProductTest() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(driver);
		ProductPage pp = new ProductPage(driver);
		CampaignPage cp = new CampaignPage(driver);

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
		wdu.selectDropdownByValue(category, driver, product_category);

		WebElement quantity1 = pp.getQuantity();
		quantity1.clear();
		quantity1.sendKeys(quantity);

		WebElement price1 = pp.getPrice();
		price1.clear();
		price1.sendKeys(price);

		WebElement vendor = pp.getVendor();
		wdu.selectDropdownByValue(vendor, driver, vendor_id);

		pp.getSubmitproductbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(driver, toastmsg);

		String msg = toastmsg.getText();
		if (msg.contains(productname)) {
			System.out.println("Product Created Successfully");
		} else {
			System.out.println("Product not Created");
		}

	}

}
