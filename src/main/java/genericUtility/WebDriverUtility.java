package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	WebDriver driver;
	public void waitforPageLoad(WebDriver driver) {
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void elementvisibility(WebDriver driver, WebElement element) {
		this.driver=driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToFrame(int index, WebDriver driver) {
		this.driver=driver;
		driver.switchTo().frame(index);
	}

	public void switchToFrame(String name, WebDriver driver) {
		this.driver=driver;
		driver.switchTo().frame(name);
	}

	public void switchToFrame(WebElement ele, WebDriver driver) {
		this.driver=driver;
		driver.switchTo().frame(ele);
	}

	public void acceptToAlert(WebDriver driver) {
		this.driver=driver;
		Alert a = driver.switchTo().alert();
		a.accept();
	}

	public void dismissToAlert(WebDriver driver) {
		this.driver=driver;
		driver.switchTo().alert().dismiss();
	}

	public void getTextAlert(WebDriver driver, String text) {
		this.driver=driver;
		driver.switchTo().alert().getText();
	}

	public void sendTexttoAlert(WebDriver driver, String text) {
		this.driver=driver;
		driver.switchTo().alert().sendKeys(text);
	}

	public void mouseHoverOnWebElement(WebElement ele, WebDriver driver) {
		this.driver=driver;
		Actions a = new Actions(driver);
		a.moveToElement(ele).click().perform();
	}

	public void clickOnWebElement(WebElement ele, WebDriver driver) {
		this.driver=driver;
		Actions a = new Actions(driver);
		a.moveToElement(ele).click().perform();
	}

	public void doubleClick(WebElement ele, WebDriver driver) {
		this.driver=driver;
		Actions a = new Actions(driver);
		a.doubleClick(ele).perform();
	}

	public void rightClick(WebElement ele, WebDriver driver) {
		this.driver=driver;
		Actions a = new Actions(driver);
		a.contextClick(ele).perform();
	}

	public void passInput(WebElement ele, WebDriver driver, String text) {
		this.driver=driver;
		Actions a = new Actions(driver);
		a.click(ele).sendKeys(text).perform();
	}

	public void SwitchToWindows(WebElement ele, WebDriver driver) {
		this.driver=driver;
		Set<String> allwin = driver.getWindowHandles();
		for (String win : allwin) {
			driver.switchTo().window(win);
		}
	}

	public void selectDropdownByIndex(WebElement ele, WebDriver driver, int index) {
		this.driver=driver;
		Select s = new Select(ele);
		s.selectByIndex(index);
	}

	public void selectDropdownByValue(WebElement ele, WebDriver driver, String value) {
		this.driver=driver;
		Select s = new Select(ele);
		s.selectByValue(value);
	}

	public void selectDropdownByElement(WebElement ele, WebDriver driver, String text) {
		this.driver=driver;
		Select s = new Select(ele);
		s.selectByVisibleText(text);

	}

	public void takeSS(WebDriver driver, String filename) throws Throwable {
		this.driver=driver;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./errorshots/" + filename + ".png");
		FileHandler.copy(temp, dest);
	}

	public void scrollBy(WebDriver driver, int x, int y)
	{
		this.driver=driver;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + " ," + y + " )");
	}

}
