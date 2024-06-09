package marketplace.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String targetPath = System.getProperty("user.dir") + "\\test-reports\\automation-reports.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(targetPath);
		reporter.config().setDocumentTitle("Automation Test Result");
		reporter.config().setReportName("Web Ecommerce Automation Results");
		
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester Name", "Eric Win");
		return report;
	}

}
