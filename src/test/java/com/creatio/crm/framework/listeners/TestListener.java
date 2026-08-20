package com.creatio.crm.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class TestListener extends Reports implements ITestListener {

	// method to start reporting for every test in a class
			public void onTestStart(ITestResult result) {
				String testName = result.getMethod().getMethodName();
				startReporting(testName);
			}

			// method inform test executed successfully
			public void onTestSuccess(ITestResult result) {
				String testName = result.getMethod().getMethodName();
				logger.pass("Test executed successfully for : " + testName);
			}

			// method inform test has failed
			public void onTestFailure(ITestResult result) {
				String testName = result.getMethod().getMethodName();
				logger.fail("Test failed for : " + testName);
				logger.info("Test failed due to" + result.getThrowable().getLocalizedMessage());
				// Add screenshot of it
				String ssFileName = testName + ".png";
				try {
					if (new BasePage().getDriver()!= null) {
						logger.addScreenCaptureFromPath(
								new WebCommons().takeWindowScreenshot(new BasePage().getDriver(), ssFileName));
					}else {
			            logger.warning("Could not take screenshot: WebDriver was null (Test likely crashed before browser launched).");
			        }
				} catch (Exception e) {
					e.printStackTrace();
					logger.warning("Failed to attach screenshot due to: " + e.getLocalizedMessage());
				}
			}
}
