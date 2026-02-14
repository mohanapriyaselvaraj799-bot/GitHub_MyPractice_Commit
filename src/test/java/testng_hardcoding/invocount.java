package testng_hardcoding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class invocount {
	
	@Test(invocationCount=3)
	
	public void login() throws InterruptedException
	{
		WebDriver driver= new ChromeDriver();
		Reporter.log("login succesfull",true);
		Thread.sleep(2000);
		driver.quit();
		
	}

}
