package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "// a[text()='Cameras']")
	WebElement cameraicon;

	@FindBy(id = "search")
	WebElement searchfield;

	@FindBy(css = "input[name*='search']")
	WebElement searchbox;

	@FindBy(css = ".btn.btn-default.btn-lg")
	WebElement searchbtn;

	public void clickMenu() {
		log.info("Opening the Cameras category");
		cameraicon.click();
	}

	public void searchbox(String productname) {
		log.info("Searching for '{}'", productname);
		reusableMethods.visibilityOf(searchfield);
		searchbox.click();
		searchbox.sendKeys(productname);
		searchbtn.click();
	}
}
