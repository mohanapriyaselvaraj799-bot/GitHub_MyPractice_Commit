package com.ninza.crm.productTest;

import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProduct {
	public static void main(String[] args) throws IOException {
		 FileInputStream fis1 = new FileInputStream("./src/main/resources/CommonData.properties"); 
		  Properties p=new Properties(); p.load(fis1); 
		  String Browser= p.getProperty("browser");
		  String URL=p.getProperty("url");
		  String UN = p.getProperty("username");
		  String PW = p.getProperty("password");
		 
		
		WebDriver driver;
		if(Browser.equals("Chrome"))
		{
			driver=new ChromeDriver();
			
		}
		else {
			driver=new EdgeDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();
		
		String randomstring = UUID.randomUUID().toString().replace("-","").replaceAll("[0-9]","").substring(0,4);
		
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.cssSelector("i[class='material-icons']")).click();
		
		FileInputStream fis2 = new FileInputStream("./src/test/resources/TestScriptNinzaCRM.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis2);
		String productname = wb.getSheet("Product").getRow(1).getCell(2).getStringCellValue()+randomstring;
		String quantity = wb.getSheet("Product").getRow(1).getCell(3).getStringCellValue();
		String price = wb.getSheet("Product").getRow(1).getCell(4).getStringCellValue();
		String product_category = wb.getSheet("Product").getRow(1).getCell(5).getStringCellValue();
		String vendor = wb.getSheet("Product").getRow(1).getCell(6).getStringCellValue();
		
		driver.findElement(By.name("productName")).sendKeys(productname);
		WebElement category = driver.findElement(By.name("productCategory"));
		
		Select s= new Select(category);
		s.selectByValue(product_category);
		
		WebElement quantity1 = driver.findElement(By.name("quantity"));
		quantity1.clear();
		quantity1.sendKeys(quantity);
		
		WebElement price1 = driver.findElement(By.name("price"));
		price1.clear();
		price1.sendKeys(price);
		
		driver.findElement(By.name("vendorId")).sendKeys(vendor);
		driver.findElement(By.xpath("//button[text()='Add']")).submit();
		
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();
		if(msg.contains(productname))
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
		driver.quit();
		
		
		
		
		
		 

	}
}
}



