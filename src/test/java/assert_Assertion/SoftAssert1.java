package assert_Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert1 {
	
	@Test
	public void demo()
	{
		String exptitle="Facebook – log in or sign ";
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		
		String actualtitle = driver.getTitle();
		SoftAssert soft= new SoftAssert();
		soft.assertEquals(actualtitle, exptitle);
		//System.out.println("Pass");
		System.out.println("fail");
		soft.assertAll();
		
		driver.quit();
	}

}
