package baseTestPractice;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private static ThreadLocal<WebDriver>driver=new ThreadLocal<>();
	
	
	public static void setDriver(WebDriver drive) {
		driver.set(drive);
	}

	public static  WebDriver getDriver() {
		return driver.get();
	}
	
	public static void unload()
	{
		driver.remove();
	}

	
	
	
	

}
