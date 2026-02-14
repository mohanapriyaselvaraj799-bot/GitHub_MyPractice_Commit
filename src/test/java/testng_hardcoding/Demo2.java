package testng_hardcoding;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo2 {
	@Test
	public void apple()
	{
		Reporter.log("apple",true);
	}
	@Test
	public void Mango()
	{
		Reporter.log("Mango",true);
	}
	@Test
	public void orange()
	{
		Reporter.log("orange",true);
	}
	@Test
	public void Grapes()
	{
		Reporter.log("Grapes",true);
	}
	

}
