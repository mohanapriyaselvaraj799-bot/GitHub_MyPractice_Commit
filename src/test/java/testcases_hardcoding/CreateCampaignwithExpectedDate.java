package testcases_hardcoding;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithExpectedDate {
	public static void main(String[] args) throws  IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/Exceldata.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Row s = book.getSheet("Sheet1").getRow(1);
		String url=s.getCell(0).toString();
		String Un = s.getCell(1).toString();
		String psswrd = s.getCell(2).toString();
		String CampaignName= s.getCell(3).toString();
		String Target =s.getCell(4).toString();

		ChromeOptions set = new ChromeOptions();
		Map<String, Object> pref = new HashMap<>();
		pref.put("profile.password_manager_leak_detection", false);
		set.setExperimentalOption("prefs", pref);
		WebDriver driver = new ChromeDriver(set);

		driver.get(url);
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		Date d = new Date();
		// System.out.println(d);
		SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy");
		String currentdate = d1.format(d);

		Calendar c = d1.getCalendar();
		c.add(c.DAY_OF_MONTH, 15);
		String expdate = d1.format(c.getTime());
		System.out.println(expdate);

		driver.findElement(By.id("username")).sendKeys(Un);
		driver.findElement(By.id("inputPassword")).sendKeys(psswrd);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		driver.findElement(By.cssSelector("i[class='material-icons']")).click();

		driver.findElement(By.name("campaignName")).sendKeys(CampaignName);
		WebElement targetnum = driver.findElement(By.name("targetSize"));
		targetnum.clear();
		targetnum.sendKeys(Target);
		WebElement ExpDate = driver.findElement(By.name("expectedCloseDate"));
		ExpDate.sendKeys(expdate);

		driver.findElement(By.xpath("//button[text()='Create Campaign']")).submit();

		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		System.out.println(msg);
		if (msg.contains("SeleniumClass")) 
		{
			System.out.println("Campaign created successfully");
		}
		else {
			System.out.println("campaign Not created");
		}
		
		 WebElement product = driver.findElement(By.xpath("//div[@class='user-icon']"));
		 Actions a = new Actions(driver);
		 a.moveToElement(product).click().perform();
		 driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		 
		 driver.close();

	}


}





