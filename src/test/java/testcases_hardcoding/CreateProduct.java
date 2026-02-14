package testcases_hardcoding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProduct {
	public static void main(String[] args) {
		WebDriver driver = new EdgeDriver();
		
		driver.get("http://49.249.28.218:8098/");
		driver.manage().window().maximize();
		
		//implicit wait for popup
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//login 
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		//click Product
		driver.findElement(By.linkText("Products")).click();
		
		//click AddProduct
		driver.findElement(By.cssSelector("i[class='material-icons']")).click();
		
		//add product
		driver.findElement(By.name("productName")).sendKeys("qywyuqyeuq");
		WebElement category = driver.findElement(By.name("productCategory"));
		
		Select s= new Select(category);
		s.selectByValue("Electricals");
		
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys("3");
		
		WebElement price = driver.findElement(By.name("price"));
		price.clear();
		price.sendKeys("0.05");
		
		driver.findElement(By.name("vendorId")).sendKeys("Harika28 - (Electricals)");
		driver.findElement(By.xpath("//button[text()='Add']")).submit();
		
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		if(msg.contains("qywyuqyeuq"))
		{
			System.out.println("Product Created Successfully");
		}
		else
		{
			System.out.println("Product not Created");
		}
		
		WebElement logoutButton = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions a= new Actions(driver);
		a.moveToElement(logoutButton).click().perform();
		driver.findElement(By.xpath("//div[text()='Logout ']")).click();
		
	
		
		
		
		
		 

	}
	}


