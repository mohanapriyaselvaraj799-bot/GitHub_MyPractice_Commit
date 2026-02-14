package listenerUtilityPractice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import baseTestPractice.DriverManager;
import baseTestPractice.ExtentManager;

public class MyListenerImplementation implements ITestListener,ISuiteListener {
	@Override
	public void onStart(ISuite suite) {
		
		Reporter.log("Report Config", true);
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentManager.getReports().flush();
		ExtentManager.unhold();
		Reporter.log("Report backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		///String testname = result.getMethod().getMethodName();
		ExtentManager.createTest(result.getMethod().getMethodName());
		ExtentManager.getTest().log(Status.INFO,"==========EXECUTION START==========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.getTest().log(Status.PASS,"==========EXECUTION SUCCESS==========");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
		String src = ts.getScreenshotAs(OutputType.BASE64);
		ExtentManager.getTest().addScreenCaptureFromBase64String(src);
		ExtentManager.getTest().log(Status.FAIL,"==========EXECUTION FAIL==========");
	}

	@Override
	public void onTestSkipped(ITestResult result) {	
		ExtentManager.getTest().log(Status.SKIP,"==========EXECUTION SKIP==========");
	}
	

}

