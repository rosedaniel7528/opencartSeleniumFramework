package com.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.opencart.config.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int count = 0;
	private final int maxRetry = ConfigReader.retryCount();

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxRetry) {
			count++;
			return true;
		}
		return false;
	}
}
