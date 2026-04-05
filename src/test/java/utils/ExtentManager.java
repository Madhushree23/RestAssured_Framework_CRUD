package utils;



	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;

	public class ExtentManager {

	    static ExtentReports extent;

	    public static ExtentReports getReport() {

	        if (extent == null) {

	            ExtentSparkReporter reporter =
	                    new ExtentSparkReporter("reports/extent-report.html");

	            reporter.config().setReportName("API Automation Report");
	            reporter.config().setDocumentTitle("RestAssured Report");

	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	        }

	        return extent;
	    }
	
}
