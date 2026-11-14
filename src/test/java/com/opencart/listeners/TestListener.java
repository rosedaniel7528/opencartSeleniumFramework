package com.opencart.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.opencart.driver.DriverManager;
import com.opencart.reporting.ExtentManager;
import com.opencart.utilities.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	private static final Logger LOG = LogManager.getLogger(TestListener.class);
	private static final ExtentReports extent = ExtentManager.createInstance();
	private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		LOG.info("START {}", result.getMethod().getMethodName());
		currentTest.set(extent.createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOG.info("PASS  {}", result.getMethod().getMethodName());
		currentTest.get().log(Status.PASS, "Test Passed");
		currentTest.remove();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOG.error("FAIL  {}", result.getMethod().getMethodName(), result.getThrowable());
		ExtentTest test = currentTest.get();
		test.fail(result.getThrowable());

		WebDriver driver = DriverManager.getDriver();
		if (driver != null) {
			test.addScreenCaptureFromBase64String(ScreenshotUtil.captureBase64(driver));
		}
		test.log(Status.FAIL, "Test Failed");
		currentTest.remove();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOG.warn("SKIP  {}", result.getMethod().getMethodName());
		ExtentTest test = currentTest.get();
		if (test != null) {
			test.log(Status.SKIP, "Test Skipped");
			currentTest.remove();
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
