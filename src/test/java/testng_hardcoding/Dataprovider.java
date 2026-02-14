package testng_hardcoding;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider {
	
	@DataProvider(name="provider")
	public Object[][] login()
	{
		Object[][] objarr= new Object[2][2];
		objarr[0][0]="abc";
		objarr[0][1]="123";
		objarr[1][0]="def";
		objarr[1][1]="456";
		return objarr;
		
	}
	@Test(dataProvider="provider")
	public void testlogin(String username,String password)
	{
		System.out.println(username+"==========="+password);
	}

}
