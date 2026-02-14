package com.ninza.crm.utilityTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignwithStatus {

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

			driver.findElement(By.cssSelector("i[class='material-icons']")).click();

			String campname = efu.toReadDatafromExcelFileUtility("Campaign", 4, 2);
			String targetsize = efu.toReadDatafromExcelFileUtility("Campaign", 4, 3);
			String status = efu.toReadDatafromExcelFileUtility("Campaign", 4, 4);

			String randomstring = jfu.toReadRandomString();

			driver.findElement(By.name("campaignName")).sendKeys(campname + randomstring);
			driver.findElement(By.name("targetSize")).sendKeys(targetsize);
			driver.findElement(By.name("campaignStatus")).sendKeys(status);

			driver.findElement(By.xpath("//button[text()='Create Campaign']")).submit();

			WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));

			wdu.elementvisibility(driver, toastmsg);

			String msg = toastmsg.getText();
			if (msg.contains(campname)) {
				System.out.println("Campaign" + campname + randomstring + "Created Successfully");
			} else {
				System.out.println("Campaign not Created");
			}

			WebElement logoutButton = driver.findElement(By.xpath("//div[@class='user-icon']"));
			wdu.mouseHoverOnWebElement(logoutButton, driver);

			driver.findElement(By.xpath("//div[text()='Logout ']")).click();

			driver.quit();

		}

	}
}
