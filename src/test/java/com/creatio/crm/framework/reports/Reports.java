package com.creatio.crm.framework.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.creatio.crm.framework.web.commons.WebCommons;

public class Reports {
	public static ExtentReports extent = null;
	public static ExtentSparkReporter file = null;
	public static ExtentTest logger = null;

	// method setup report before anything
	@BeforeSuite
	@Parameters(value = {"fileNameWithHtmlFormat"})
	public static void setupPrinting(String fileNameWithHtmlFormat) {
		String timestamp = new WebCommons().uniqueId("dd-MM-yyyy_HH-mm-ss");
	    String finalFileName = fileNameWithHtmlFormat.replace(".html", "") + "_" + timestamp + ".html";

	    extent = new ExtentReports();
	    file = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\" + finalFileName);
	    extent.attachReporter(file);
	}

	// method to Stop printing
	@AfterSuite
	public static void stopPrinting() {
		extent.flush();
	}

	// method to create testCaseName and startReporting
	public static void startReporting(String testName) {
		logger = extent.createTest(testName);
		logger.info("Test has started for : " +testName);
	}
	
	//method to print in report
	public static void printInReport(String status , String comment) {
		
		if (status.equalsIgnoreCase("pass")) {
			logger.pass(comment);
		}else if (status.equalsIgnoreCase("fail")) {
			logger.fail(comment);
		}else if (status.equalsIgnoreCase("warning")) {
			logger.warning(comment);
		}else if (status.equalsIgnoreCase("info")) {
			logger.info(comment);
		}
		
	}

}
