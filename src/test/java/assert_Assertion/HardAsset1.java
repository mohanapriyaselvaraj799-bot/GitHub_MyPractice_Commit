package assert_Assertion;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAsset1 {
	
	@Test
	public void demo()
	{
		String exptitle="Facebook – log in or sign up";
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle, exptitle);
		System.out.println("Pass");
		//pull testing
		
		driver.close();
	}
	

}

