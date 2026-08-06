package com.creatio.crm.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		int maxRetry = 2;
		int count = 0;
		if (!result.isSuccess()) { // if test failed then we would retry otherwise no retry needed
			if (count < maxRetry) {
				count++;
				return true;
			}
		}
		return false;
	}

}
