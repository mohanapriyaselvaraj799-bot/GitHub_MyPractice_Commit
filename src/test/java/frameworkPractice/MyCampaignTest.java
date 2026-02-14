package frameworkPractice;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseTestPractice.DriverManager;
import baseTestPractice.MybaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CampaignPage;
import objectRepository.HomePage;

@Listeners(listenerUtilityPractice.MyListenerImplementation.class)
public class MyCampaignTest extends MybaseClass{
	@Test
	public void createCampaignTest() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();
		wdu = new WebDriverUtility();
        CampaignPage cp = new CampaignPage(DriverManager.getDriver());

		String campname = efu.toReadDatafromExcelFileUtility("Campaign", 1, 2);
		String randomstring = jfu.toReadRandomString();
		String target = efu.toReadDatafromExcelFileUtility("Campaign", 1, 3);

		hp.getCampaignbtn().click();
		cp.getCampaignname().sendKeys(campname+randomstring);
		cp.getTarget().sendKeys(target);

		cp.getCreatecampbtn().submit();

		WebElement toastmsg = cp.getToastmsg();

		wdu.elementvisibility(DriverManager.getDriver(), toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("1st Campaign Created Successfully");
	}

	@Test
	public void createCampaignwithExpectedDate() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(DriverManager.getDriver());
		CampaignPage cp = new CampaignPage(DriverManager.getDriver());

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

		wdu.elementvisibility(DriverManager.getDriver(), toastmsg);
		String msg = toastmsg.getText();

		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("Campaign Created with Expexted Date Successfully");

	}

	@Test
	public void createCampaignwithStatus() throws Throwable {

		ExcelFileUtility efu = new ExcelFileUtility();
		JavaUtility jfu = new JavaUtility();

		HomePage hp = new HomePage(DriverManager.getDriver());
		CampaignPage cp = new CampaignPage(DriverManager.getDriver());

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

		wdu.elementvisibility(DriverManager.getDriver(), toastmsg);

		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname+randomstring));

		System.out.println("Campaign Created with Status Successfully");

	}

}

