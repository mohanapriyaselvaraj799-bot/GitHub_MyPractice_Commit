package baseTestPractice;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentSparkReporter spark;
	public static ExtentReports reports;
	static ThreadLocal<ExtentTest> exttest = new ThreadLocal<>();

	public static ExtentReports getReports() {
		if (reports == null) {
			Date d = new Date();
			String newdate = d.toString().replace(" ", "_").replace(":", "_");
			spark = new ExtentSparkReporter("./ReportPractice/TestReport" + newdate + ".html");
			spark.config().setDocumentTitle("Ninza CRM Result Report");
			spark.config().setReportName("Test Reports");
			spark.config().setTheme(Theme.DARK);

			reports = new ExtentReports();
			reports.attachReporter(spark);
			reports.setSystemInfo("OS", "Windows 11");
		}

		return reports;

	}

	public static void createTest(String testname) {
		ExtentTest test = getReports().createTest(testname);
		exttest.set(test);
	}

	public static ExtentTest getTest() {
		return exttest.get();

	}

	public static void unhold() {
		exttest.remove();
	}
}
