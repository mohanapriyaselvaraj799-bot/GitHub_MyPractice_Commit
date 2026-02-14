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

public class CreateCampaign {
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
			
			LoginPage lp= new LoginPage(driver);
			HomePage hp = new HomePage(driver);
			CampaignPage cp = new CampaignPage(driver);
			ProductPage pp= new ProductPage(driver);
			

			lp.getUN().sendKeys(UN);
			lp.getPW().sendKeys(PW);
			lp.getLoginbtn().click();

			
			String campname = efu.toReadDatafromExcelFileUtility("Campaign", 1, 2);
			String randomstring = jfu.toReadRandomString();
			String target =efu.toReadDatafromExcelFileUtility("Campaign", 1, 3);

			
			hp.getCampaignbtn().click();
			cp.getCampaignname().sendKeys(campname+randomstring);
			cp.getTarget().sendKeys(target);

			cp.getCreatecampbtn().submit();

			WebElement toastmsg = cp.getToastmsg();
			
			wdu.elementvisibility(driver, toastmsg);
			String msg = toastmsg.getText();
			if (msg.contains(campname)) {
				System.out.println("Campaign Created Successfully");
			} else {
				System.out.println("Campaign not Created");
			}

			WebElement logoutButton = hp.getUsericon();
			
			wdu.mouseHoverOnWebElement(logoutButton, driver);
			
			hp.getLogoutbtn().click();

		}

	}
}



