package com.opencart.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver){
		super(driver);
	}
	@FindBy(css=".table-bordered td[class='text-left'] a")
	List<WebElement> productlist;
	
	@FindBy(css=".buttons.clearfix div[class='pull-right']")
	WebElement checkout;
	
	public void checkproductincart(String productName) {
		log.info("Verifying '{}' is in the cart", productName);
		List<WebElement> cartList = productlist;
		boolean Flag = cartList.stream().anyMatch(s -> s.getText().equals(productName));
		if (!Flag) {
			log.warn("'{}' was not found in the cart", productName);
		}
		Assert.assertTrue(Flag);
		checkout.click();
	}
	
}
