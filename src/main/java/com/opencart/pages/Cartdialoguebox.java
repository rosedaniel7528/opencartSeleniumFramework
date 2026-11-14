package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cartdialoguebox extends BasePage {

	public Cartdialoguebox(WebDriver driver) {
		super(driver);
	}
	@FindBy(css=".btn.btn-inverse.btn-block.btn-lg.dropdown-toggle")
	WebElement cart;
	
	@FindBy(css="strong i[class*='fa-shopping-cart']")
	WebElement cartbox;
	
	
	public void clickChekout() throws InterruptedException {
		log.info("Opening the cart dialog");
		Thread.sleep(1000);
		reusableMethods.visibilityOf(cart);
		cart.click();
		reusableMethods.visibilityOf(cartbox);
		cartbox.click();
	}
}
