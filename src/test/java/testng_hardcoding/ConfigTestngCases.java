package testng_hardcoding;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ConfigTestngCases {
  @Test
  public void test001() 
  {
	  Reporter.log("testcase",true);
  }
  @BeforeMethod
  public void beforeMethod()
  {
	  Reporter.log("login",true);
  }

  @AfterMethod
  public void afterMethod() {
	  Reporter.log("logout",true);
  }

  @BeforeClass
  public void beforeClass() {
	  Reporter.log("launch browser",true);
  }

  @AfterClass
  public void afterClass() {
	  Reporter.log("close browser",true);
  }

  @BeforeTest
  public void beforeTest() {
	  Reporter.log("pre conditions",true);
  }

  @AfterTest
  public void afterTest() {
	  Reporter.log("post conditions",true);
  }

  @BeforeSuite
  public void beforeSuite() {
	  Reporter.log("DB open",true);
  }

  @AfterSuite
  public void afterSuite() {
	  Reporter.log("DB close",true);
  }

}
