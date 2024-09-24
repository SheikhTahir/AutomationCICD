package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG  {
	
	public static ExtentReports getReportObject() {
		//ExtentReports, ExtentSparkReporter
				String path = System.getProperty("user.div")+"\\reports\\index.html"; //	Created the path to create a folder and save the index.html file
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);	//	Created reporter object(helper) to get the ExtentSparkReporter and provided path.
				reporter.config().setReportName("Web Automation Result");	//	Set the name of the extent report
				reporter.config().setDocumentTitle("Test Results");	//	set the title of the extent report
				
				ExtentReports extent = new ExtentReports();	//	created the object for the main  ExtentReports which is main action
				extent.attachReporter(reporter);			//	we attached the helper object to link with the main extent object
				extent.setSystemInfo("Tester", "Sheikh Tahir");	//	set the system info like tester name
				return extent;
	}
}
