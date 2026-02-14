package testng_hardcoding;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class priority {
	
	@Test(priority=3)
	public void test1()
	{
		Reporter.log("test1",true);
	}
	@Test(priority=2)
	public void login()
	{
		Reporter.log("login test", true);
	}
	@Test(priority=4)
	public void logout()
	{
		Reporter.log("logout test", true);
	}
	@Test  //default 0
	public void lanch()
	{
		Reporter.log("launch test", true);
	}
	@Test(priority=5)
	public void close()
	{
		Reporter.log("close test", true);
	}
	

}
