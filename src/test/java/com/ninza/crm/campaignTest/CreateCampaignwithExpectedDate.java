package com.ninza.crm.campaignTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithExpectedDate {
	public static void main(String[] args) throws IOException {
		FileInputStream fis1 = new FileInputStream("./src/main/resources/CommonData.properties");
		Properties p = new Properties();
		p.load(fis1);
		String Browser = p.getProperty("browser");
		String URL = p.getProperty("url");
		String UN = p.getProperty("username");
		String PW = p.getProperty("password");

		WebDriver driver;
		if (Browser.equals("Chrome")) {
			driver = new ChromeDriver();

		} else {
			driver = new EdgeDriver();
			driver.get(URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PW);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();

			String randomstring = UUID.randomUUID().toString().replace("-", "").replaceAll("[0-9]", "").substring(0, 4);

			Date d = new Date();
			SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy");
			String currentdate = d1.format(d);

			driver.findElement(By.cssSelector("i[class='material-icons']")).click();

			FileInputStream fis2 = new FileInputStream("./src/test/resources/TestScriptNinzaCRM.xlsx");
			Workbook wb = WorkbookFactory.create(fis2);

			String campaignname = wb.getSheet("Campaign").getRow(7).getCell(2).getStringCellValue() + randomstring;
			String targetsize = wb.getSheet("Campaign").getRow(7).getCell(3).getStringCellValue();
			String status = wb.getSheet("Campaign").getRow(7).getCell(4).getStringCellValue();
			String expdate = wb.getSheet("Campaign").getRow(7).getCell(5).getStringCellValue();
			int amount = Integer.parseInt(expdate);
			
			Calendar c = d1.getCalendar();
			c.add(c.DAY_OF_MONTH, amount);
			String expdate1 = d1.format(c.getTime());
			//System.out.println(expdate);

			driver.findElement(By.name("campaignName")).sendKeys(campaignname);
			WebElement targetnum = driver.findElement(By.name("targetSize"));
			targetnum.clear();
			targetnum.sendKeys(targetsize);
			WebElement ExpDate = driver.findElement(By.name("expectedCloseDate"));
			ExpDate.sendKeys(expdate1);

			driver.findElement(By.name("campaignStatus")).sendKeys(status);
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).submit();

			WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(toastmsg));
			String msg = toastmsg.getText();
			System.out.println(msg);
			if (msg.contains(campaignname)) {
				System.out.println("Campaign created successfully");
			} else {
				System.out.println("Campaign Not created");
			}

			WebElement product = driver.findElement(By.xpath("//div[@class='user-icon']"));
			Actions a = new Actions(driver);
			a.moveToElement(product).click().perform();
			driver.findElement(By.xpath("//div[text()='Logout ']")).click();

			driver.close();

		}

	}
}
