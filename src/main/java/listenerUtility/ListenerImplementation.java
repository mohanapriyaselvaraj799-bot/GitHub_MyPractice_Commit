package listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseClass;

public class ListenerImplementation implements ITestListener,ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Date d = new Date();
		String newdate = d.toString().replace(" ", "_").replace(":", "_");
		 spark= new ExtentSparkReporter("./AdvanceReport/report"+newdate+".html");
		 spark.config().setDocumentTitle("Ninza CRM Result Report");
		 spark.config().setReportName("Test Reports");
		 spark.config().setTheme(Theme.STANDARD);
		
		 
		 reports=new ExtentReports();
		 reports.attachReporter(spark);
		 reports.setSystemInfo("OS", "Windows 11");
		
		Reporter.log("Report Config", true);
	}

	@Override
	public void onFinish(ISuite suite) {
		reports.flush();
		Reporter.log("Report backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		test=reports.createTest(testname);
		test.log(Status.INFO,"======"+testname+"EXECUTION START"+"==========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		
		test.log(Status.PASS,"======"+testname+"EXECUTION SUCCESS"+"==========");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		

		Date d = new Date();
		//System.out.println(d);
		String newdate = d.toString().replace(" ", "_").replace(":", "_");
		//System.out.println(newdate);
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src);
		test.log(Status.FAIL,"======"+testname+ "EXECUTION FAIL"+"==========");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		
		test.log(Status.SKIP,"======"+testname+"TEST SKIP"+"==========");
	
	}
	

}
