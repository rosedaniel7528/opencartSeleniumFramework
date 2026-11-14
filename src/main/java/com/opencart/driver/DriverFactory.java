package com.opencart.driver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.opencart.config.Browser;
import com.opencart.config.ConfigReader;
import com.opencart.config.ExecutionMode;

public final class DriverFactory {

	private static final Logger LOG = LogManager.getLogger(DriverFactory.class);

	private DriverFactory() {
	}

	public static WebDriver createDriver(Browser browser, ExecutionMode mode) {
		LOG.info("Starting {} in {} mode", browser, mode);
		return switch (browser) {
		case CHROME -> createChrome(mode);
		case FIREFOX -> createFirefox(mode);
		};
	}

	private static WebDriver createChrome(ExecutionMode mode) {
		ChromeOptions options = BrowserOptions.chrome(mode);
		if (mode == ExecutionMode.REMOTE) {
			return new RemoteWebDriver(gridUrl(), options);
		}
		return new ChromeDriver(options);
	}

	private static WebDriver createFirefox(ExecutionMode mode) {
		FirefoxOptions options = BrowserOptions.firefox(mode);
		if (mode == ExecutionMode.REMOTE) {
			return new RemoteWebDriver(gridUrl(), options);
		}
		return new FirefoxDriver(options);
	}

	private static URL gridUrl() {
		try {
			return URI.create(ConfigReader.gridUrl()).toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
