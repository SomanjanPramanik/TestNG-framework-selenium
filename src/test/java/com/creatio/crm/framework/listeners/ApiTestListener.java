package com.creatio.crm.framework.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.creatio.crm.framework.api.commons.ApiCommons;
import com.creatio.crm.framework.reports.Reports;

public class ApiTestListener extends Reports implements ITestListener{

	// method to start reporting for every test in a class
		public void onTestStart(ITestResult result) {
			String testName = result.getMethod().getMethodName();
			startReporting(testName);
		}

		// method inform test executed successfully
		public void onTestSuccess(ITestResult result) {
			String testName = result.getMethod().getMethodName();
			logger.pass("Test executed successfully for : " + testName);
			logger.pass("Response Body :"+ApiCommons.response.asPrettyString());
		}

		// method inform test has failed
		public void onTestFailure(ITestResult result) {
			String testName = result.getMethod().getMethodName();
			logger.fail("Test failed for : " + testName);
			logger.info("Test failed due to" + result.getThrowable().getLocalizedMessage());
			logger.pass("Response Body :"+ApiCommons.response.asPrettyString());
		}
}
