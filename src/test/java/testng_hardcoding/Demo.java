package testng_hardcoding;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {
	
	@Test
	public void add()
	{
		Reporter.log("add",true);
	}
	@Test
	public void sub()
	{
		Reporter.log("sub",true);
	}
	@Test
	public void mul()
	{
		Reporter.log("mul",true);
	}
	@Test
	public void div()
	{
		Reporter.log("div",true);
	}
	

}
