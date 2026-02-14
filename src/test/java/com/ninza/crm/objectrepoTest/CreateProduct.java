package com.ninza.crm.objectrepoTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.ProductPage;

public class CreateProduct {

	public static void main(String[] args) throws Throwable {

		PropertiesFileUtility pfu = new PropertiesFileUtility();
		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();
		WebDriverUtility wdu = new WebDriverUtility();

		String BROWSER = pfu.toReadDatafromPropertiesFile("browser");
		String URL = pfu.toReadDatafromPropertiesFile("url");
		String UN = pfu.toReadDatafromPropertiesFile("username");
		String PW = pfu.toReadDatafromPropertiesFile("password");
		WebDriver driver = null;

		if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
			driver.get(URL);
			driver.manage().window().maximize();

			wdu.waitforPageLoad(driver);

			LoginPage lp = new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			CampaignPage cp = new CampaignPage(driver);
			ProductPage pp = new ProductPage(driver);

			lp.getUN().sendKeys(UN);
			lp.getPW().sendKeys(PW);
			lp.getLoginbtn().click();

			// click Product
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

			WebElement logoutButton = hp.getUsericon();

			wdu.mouseHoverOnWebElement(logoutButton, driver);
			hp.getLogoutbtn().click();
			driver.quit();

		}
	}
}
