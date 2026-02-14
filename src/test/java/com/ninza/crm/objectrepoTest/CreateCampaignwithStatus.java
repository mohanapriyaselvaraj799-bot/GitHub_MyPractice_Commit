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

public class CreateCampaignwithStatus {
	
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
			
			
			LoginPage lp= new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			CampaignPage cp = new CampaignPage(driver);
			ProductPage pp= new ProductPage(driver);

			lp.Login(URL, UN, PW,driver);
			


			

			String campname = efu.toReadDatafromExcelFileUtility("Campaign", 4, 2);
			String target = efu.toReadDatafromExcelFileUtility("Campaign", 4, 3);
			String status = efu.toReadDatafromExcelFileUtility("Campaign", 4, 4);
			String randomstring = jfu.toReadRandomString();
			

			hp.getCampaignbtn().click();
			cp.getCampaignname().sendKeys(campname+randomstring);
			cp.getTarget().sendKeys(target);
			cp.getCamapignstatus().sendKeys(status);

			cp.getCreatecampbtn().submit();

			WebElement toastmsg = cp.getToastmsg();

			wdu.elementvisibility(driver, toastmsg);

			String msg = toastmsg.getText();
			if (msg.contains(campname)) {
				System.out.println("Campaign Created Successfully with Status");
			} else {
				System.out.println("Campaign not Created with status");
			}

			WebElement logoutButton = hp.getUsericon();
			wdu.mouseHoverOnWebElement(logoutButton, driver);

			hp.getLogoutbtn().click();
			
			driver.quit();

		}

	}
}

