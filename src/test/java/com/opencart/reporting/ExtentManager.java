package com.opencart.reporting;

import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {

	private ExtentManager() {
	}

	public static ExtentReports createInstance() {
		String path = Paths.get(System.getProperty("user.dir"), "reports", "index.html").toString();
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setReportName("Opencart WebAutomation");
		spark.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Tester", "Rose Daniel");
		return extent;
	}
}
