package com.ninza.crm.utilityTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateProduct {

	public static void main(String[] args) throws Throwable {

		PropertiesFileUtility pfu = new PropertiesFileUtility();
		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();
		WebDriverUtility wdu = new WebDriverUtility();

		String Browser = pfu.toReadDatafromPropertiesFile("browser");
		String Url = pfu.toReadDatafromPropertiesFile("url");
		String UN = pfu.toReadDatafromPropertiesFile("username");
		String PW = pfu.toReadDatafromPropertiesFile("password");
		WebDriver driver = null;

		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new EdgeDriver();
			driver.get(Url);
			driver.manage().window().maximize();

			wdu.waitforPageLoad(driver);

			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PW);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();

			// click Product
			driver.findElement(By.linkText("Products")).click();

			// click AddProduct
			driver.findElement(By.cssSelector("i[class='material-icons']")).click();

			String productname = efu.toReadDatafromExcelFileUtility("Product", 1, 2);
			String quantity = efu.toReadDatafromExcelFileUtility("Product", 1, 3);
			String price = efu.toReadDatafromExcelFileUtility("Product", 1, 4);
			String product_category = efu.toReadDatafromExcelFileUtility("Product", 1, 5);
			String vendor_id = efu.toReadDatafromExcelFileUtility("Product", 1, 6);

			// Add RandomNum
			int randomNum = jfu.toReadRandomNumber();

			// add product
			driver.findElement(By.name("productName")).sendKeys(productname + randomNum);
			WebElement category = driver.findElement(By.name("productCategory"));
			wdu.selectDropdownByValue(category, driver, product_category);

			WebElement quantity1 = driver.findElement(By.name("quantity"));
			quantity1.clear();
			quantity1.sendKeys(quantity);

			WebElement price1 = driver.findElement(By.name("price"));
			price1.clear();
			price1.sendKeys(price);

			WebElement vendor = driver.findElement(By.name("vendorId"));
			// wdu.selectDropdownByElement(vendor1, driver, vendor);
			wdu.selectDropdownByValue(vendor, driver, vendor_id);

			driver.findElement(By.xpath("//button[text()='Add']")).submit();

			WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));

			wdu.elementvisibility(driver, toastmsg);
			String msg = toastmsg.getText();
			if (msg.contains(productname)) {
				System.out.println("Product" + productname + randomNum + " Created Successfully");
			} else {
				System.out.println("Product not Created");
			}

			WebElement logoutButton = driver.findElement(By.xpath("//div[@class='user-icon']"));

			wdu.mouseHoverOnWebElement(logoutButton, driver);
			driver.findElement(By.xpath("//div[text()='Logout ']")).click();
			driver.quit();

		}
	}
}
