package com.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.opencart.utilities.ReusableMethods;

public abstract class BasePage {

	protected final Logger log = LogManager.getLogger(getClass());
	protected final WebDriver driver;
	protected final ReusableMethods reusableMethods;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.reusableMethods = new ReusableMethods(driver);
		PageFactory.initElements(driver, this);
	}
}
