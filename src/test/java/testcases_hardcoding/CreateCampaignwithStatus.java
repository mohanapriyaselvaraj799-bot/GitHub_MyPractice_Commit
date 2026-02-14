package testcases_hardcoding;

import java.io.FileInputStream;
import java.io.IOException;
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

public class CreateCampaignwithStatus {
	
public static void main(String[] args) throws IOException {
		
		
		WebDriver driver;
		FileInputStream fis = new FileInputStream("./src/test/resources/Commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String BROWSER = p.getProperty("browser");
		String UL = p.getProperty("url");
		String UN = p.getProperty("username");
		String PW = p.getProperty("password");

		if(BROWSER.equals("Chrome")) 
		{
			driver=new ChromeDriver();
		}
		else
		{
			driver = new EdgeDriver();
			driver.get(UL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PW);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();

			driver.findElement(By.cssSelector("i[class='material-icons']")).click();

			FileInputStream exc = new FileInputStream("./src/test/resources/data.xlsx");

			Workbook book = WorkbookFactory.create(exc);
			Sheet s = book.getSheet("Campaign");

			String campname = s.getRow(1).getCell(0).getStringCellValue();
			String target = s.getRow(1).getCell(1).getStringCellValue();
			String status = s.getRow(1).getCell(2).getStringCellValue();

			driver.findElement(By.name("campaignName")).sendKeys(campname);
			driver.findElement(By.name("targetSize")).sendKeys(target);
			driver.findElement(By.name("campaignStatus")).sendKeys(status);

			driver.findElement(By.xpath("//button[text()='Create Campaign']")).submit();

			WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(toastmsg));
			String msg = toastmsg.getText();
			if (msg.contains(campname)) {
				System.out.println("Campaign Created Successfully");
			} else {
				System.out.println("Campaign not Created");
			}

			WebElement logoutButton = driver.findElement(By.xpath("//div[@class='user-icon']"));
			Actions a = new Actions(driver);
			a.moveToElement(logoutButton).click().perform();
			driver.findElement(By.xpath("//div[text()='Logout ']")).click();

		}

	}
}



