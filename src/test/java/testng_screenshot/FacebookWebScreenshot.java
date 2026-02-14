package testng_screenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FacebookWebScreenshot {

	@Test
	public void facebookScreen() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");

		Date d = new Date();
		System.out.println(d);
		String newdate = d.toString().replace(" ", "_").replace(":", "_");
		System.out.println(newdate);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/facebookwebpage" + newdate + ".png");
		FileHandler.copy(src, dest);

		driver.quit();

	}

}
