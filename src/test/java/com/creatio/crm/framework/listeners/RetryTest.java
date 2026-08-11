package com.creatio.crm.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {
	private final int maxRetry = 2;
	private int count = 0;
	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) { // if test failed then we would retry otherwise no retry needed
			if (count < maxRetry) {
				count++;
				return true;
			}
		}
		return false;
	}

}
