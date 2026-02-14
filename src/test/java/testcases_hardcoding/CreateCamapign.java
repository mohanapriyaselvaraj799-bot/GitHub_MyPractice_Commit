package testcases_hardcoding;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCamapign {
	public static void main(String[] args) throws IOException {
		
		/*
		 * FileInputStream fis = new
		 * FileInputStream("./src/test/resources/Commondata.properties"); Properties p=
		 * new Properties(); p.load(fis); String UN = p.getProperty("username"); String
		 * PW = p.getProperty("password");
		 */
		
		WebDriver driver= new EdgeDriver();
		driver.get("http://49.249.28.218:8098/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		
		driver.findElement(By.cssSelector("i[class='material-icons']")).click();
		
		driver.findElement(By.name("campaignName")).sendKeys("hhjxbcjnb");
		driver.findElement(By.name("targetSize")).sendKeys("7");
		
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).submit();
		
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		if(msg.contains("hhjxbcjnb"))
		{
			System.out.println("Campaign Created Successfully");
		}
		else
		{
			System.out.println("Campaign not Created");
		}
		
		WebElement logoutButton = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions a= new Actions(driver);
		a.moveToElement(logoutButton).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		
	
	}

}
