package testng_hardcoding;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class threadpool {
	
	@Test(invocationCount=6,threadPoolSize=2)  //threadpool refers to no.of thread opens, invocations refers to  no of times executions happens
	public void login() throws InterruptedException
	{
		WebDriver driver= new ChromeDriver();
		Reporter.log("login succesfull",true);
		Thread.sleep(2000);
		//driver.quit();
		
	}

}
