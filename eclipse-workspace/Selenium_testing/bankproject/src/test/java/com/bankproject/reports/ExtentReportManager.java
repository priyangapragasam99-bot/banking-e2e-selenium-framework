package com.bankproject.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	private static ExtentReports extentReports;

	public static ExtentReports getInstance() {

		if (extentReports == null) {

			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");

			sparkReporter.config().setDocumentTitle("Banking E2E Automation Report");

			sparkReporter.config().setReportName("Banking E2E Test Results");

			extentReports = new ExtentReports();
			extentReports.attachReporter(sparkReporter);

			extentReports.setSystemInfo("Project", "Banking E2E Automation");

			extentReports.setSystemInfo("Automation Tool", "Selenium WebDriver");

			extentReports.setSystemInfo("Test Framework", "TestNG");
		}

		return extentReports;
	}

}
