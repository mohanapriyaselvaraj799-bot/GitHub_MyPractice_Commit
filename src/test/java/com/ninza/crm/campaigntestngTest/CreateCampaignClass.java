package com.ninza.crm.campaigntestngTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

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
import org.testng.Assert;
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
public class CreateCampaignClass extends BaseClass {

	@Test(groups = "smoke")
	public void createCampaignTest() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		// HomePage hp = new HomePage(driver);
		CampaignPage cp = new CampaignPage(driver);

		String campname = efu.toReadDatafromExcelFileUtility("Campaign", 1, 2);
		String randomstring = jfu.toReadRandomString();
		String target = efu.toReadDatafromExcelFileUtility("Campaign", 1, 3);

		hp.getCampaignbtn().click();
		cp.getCampaignname().sendKeys(campname+randomstring);
		cp.getTarget().sendKeys(target);

		cp.getCreatecampbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("Campaign Created Successfully");
	}

	@Test(groups = "smoke")
	public void createCampaignwithExpectedDate() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(driver);
		CampaignPage cp = new CampaignPage(driver);

		String campname = efu.toReadDatafromExcelFileUtility("Campaign", 7, 2);
		String target = efu.toReadDatafromExcelFileUtility("Campaign", 7, 3);
		String status = efu.toReadDatafromExcelFileUtility("Campaign", 7, 4);

		String randomstring = jfu.toReadRandomString();

		hp.getCampaignbtn().click();
		cp.getCampaignname().sendKeys(campname+randomstring);
		cp.getTarget().sendKeys(target);
		cp.getCamapignstatus().sendKeys(status);

		String expdate = efu.toReadDatafromExcelFileUtility("Campaign", 7, 5);
		int amount = Integer.parseInt(expdate);
		String expdate1 = jfu.togetRequiredDate(amount);

		WebElement ExpDate = cp.getExpcloasedate();
		ExpDate.sendKeys(expdate1);

		cp.getCreatecampbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(driver, toastmsg);
		String msg = toastmsg.getText();

		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("Campaign Created Successfully");

	}

	@Test(groups = "smoke")
	public void createCampaignwithStatus() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(driver);
		CampaignPage cp = new CampaignPage(driver);

		String campname = efu.toReadDatafromExcelFileUtility("Campaign", 4, 2);
		String target = efu.toReadDatafromExcelFileUtility("Campaign", 4, 3);
		String status = efu.toReadDatafromExcelFileUtility("Campaign", 4, 4);
		String randomstring = jfu.toReadRandomString();

		hp.getCampaignbtn().click();
		cp.getCampaignname().sendKeys(campname + randomstring);
		cp.getTarget().sendKeys(target);
		cp.getCamapignstatus().sendKeys(status);

		cp.getCreatecampbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(driver, toastmsg);

		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("Campaign Created Successfully");

	}

}
