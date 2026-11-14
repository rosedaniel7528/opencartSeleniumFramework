package com.opencart.base;

import com.opencart.config.Browser;
import com.opencart.config.ConfigReader;
import com.opencart.config.ExecutionMode;
import com.opencart.driver.DriverFactory;
import com.opencart.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

	private static final Logger LOG = LogManager.getLogger(BaseTest.class);

	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	@Parameters({"browserName"})
	@BeforeMethod
	public void initilize(@Optional String browserName) {
		Browser browser = Browser.from(browserName != null ? browserName : ConfigReader.browser());
		ExecutionMode mode = ConfigReader.executionMode();

		WebDriver driver = DriverFactory.createDriver(browser, mode);
		DriverManager.setDriver(driver);
		LOG.info("Opening {}", ConfigReader.baseUrl());
		driver.get(ConfigReader.baseUrl());
		driver.manage().window().maximize();
	}

	@AfterMethod(alwaysRun = true)
	public void quit() {
		DriverManager.quitDriver();
		LOG.info("Browser closed");
	}
}
