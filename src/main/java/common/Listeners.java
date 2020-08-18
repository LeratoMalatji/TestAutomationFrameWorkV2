package common;

import common.helper.ScreenShot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utility.ExtentReporterNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Listeners extends Web_Base implements ITestListener {

    private  ExtentReports extent =ExtentReporterNg.getReportObject();
    private ExtentTest test;
    private static Logger log = LogManager.getLogger(Listeners.class.getName());

    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // pool for keeping extentTest object thread safe.

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        log.info("on TestStart dynamically created method name under test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.get().log(Status.PASS, "Test has passed");
        log.info("Test case passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String failedMethodName = result.getMethod().getMethodName(); // getting method name from the current running
        // instance of a class
        //get stacktrace log if failed
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
                    .get(result.getInstance());

            if (driver == null) {
                log.error("Webdriver is found to be null");
                throw new NullPointerException("Listener Webdriver has a null state");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        try {

            String screen_Shot_Path =new ScreenShot().getScreenShotPath(failedMethodName, driver);
            extentTest.get().addScreenCaptureFromPath(screen_Shot_Path, result.getMethod().getMethodName());
            log.info("Screenshot taken successfully of the failed test");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        extentTest.get().log(Status.FAIL,"page took to long to respond");
        log.error("Test has failed due to time out");

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }

}
