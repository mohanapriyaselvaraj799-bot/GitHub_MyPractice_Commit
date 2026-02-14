package genericUtility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class JavaUtility {
	
	public int toReadRandomNumber()
	{
		Random r= new Random();
		int randomNum = r.nextInt(1000);
		return randomNum;
	}
	public String toReadRandomString()
	{
		String randomstring = UUID.randomUUID().toString().replace("-", "").replaceAll("[0-9]", "").substring(0, 4);
		return randomstring;
	}
	
	public String togetCurrentDate()
	{
		Date d=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("dd-MM-yyyy");
		String currentdate = sim.format(d);
		return currentdate;
	}
	
	public String togetRequiredDate(int days)
	{
		Date d=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("dd-MM-yyyy");
		String currentdate = sim.format(d);
		Calendar c= sim.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, days);
		String requiredate = sim.format(c.getTime());
		return requiredate;
		
	}

}
