package com.opencart.driver;

import java.util.List;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.opencart.config.ExecutionMode;

public final class BrowserOptions {

	private BrowserOptions() {
	}

	public static ChromeOptions chrome(ExecutionMode mode) {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

		if (mode != ExecutionMode.LOCAL) {
			options.setPlatformName("linux");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		}
		if (mode == ExecutionMode.REMOTE_GIT) {
			options.addArguments("--headless", "--disable-gpu", "--no-sandbox");
		}
		return options;
	}

	public static FirefoxOptions firefox(ExecutionMode mode) {
		FirefoxOptions options = new FirefoxOptions();

		if (mode == ExecutionMode.LOCAL) {
			return options;
		}
		options.setPlatformName("linux");
		options.setPageLoadStrategy(PageLoadStrategy.EAGER);
		if (mode == ExecutionMode.REMOTE_GIT) {
			options.addArguments("-headless");
		}
		return options;
	}
}
