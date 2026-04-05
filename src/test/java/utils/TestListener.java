package utils;



	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentTest;

	import utils.ExtentManager;

	public class TestListener implements ITestListener {

	    static ExtentTest test;

	    @Override
	    public void onTestStart(ITestResult result) {
	        test = ExtentManager.getReport()
	                .createTest(result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.pass("Test Passed");
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        test.fail("Test Failed: " + result.getThrowable());
	    }

	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	        ExtentManager.getReport().flush();
	    }
	
}
