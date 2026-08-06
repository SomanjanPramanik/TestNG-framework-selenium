package com.creatio.crm.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reports {
	public static ExtentReports extent = null;
	public static ExtentSparkReporter file = null;
	public static ExtentTest logger = null;

	// method setup report before anything
	@BeforeSuite
	public void setupPrinting(String fileNameWithHtmlFormat) {
		extent = new ExtentReports();
		file = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\" + fileNameWithHtmlFormat);
		extent.attachReporter(file);
	}

	// method to Stop printing
	public void stopPrinting() {
		extent.flush();
	}

	// method to create testCaseName and startReporting
	public void startReporting(String testName) {
		extent.createTest(testName);
		logger.info("Test has started for : " +testName);
	}

}
