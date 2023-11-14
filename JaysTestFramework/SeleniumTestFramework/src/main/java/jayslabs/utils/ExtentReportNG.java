package jayslabs.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	public static ExtentReports getReportObject() {
			System.out.println("configuring extent reports...");
			String fstr = System.getProperty("user.dir") + "\\test-output\\extentreports\\index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(fstr);
			
			ExtentReports extent = new ExtentReports();
			
			reporter.config().setReportName("Submit Order Test Results");
			reporter.config().setDocumentTitle("Test Results");
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Jay M");
			
			return extent;
		}	

}
