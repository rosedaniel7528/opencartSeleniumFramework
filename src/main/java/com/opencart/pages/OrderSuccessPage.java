package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OrderSuccessPage extends BasePage {

	public OrderSuccessPage(WebDriver driver){
		super(driver);
	}
	@FindBy(id="common-success")
	WebElement orderpage;
	
	@FindBy(css="#content h1")
	WebElement orderMsg;
	
	public void orderconfirmation() {
		reusableMethods.visibilityOf(orderpage);
		String ordermsg = orderMsg.getText();
		log.info("Order confirmation message: {}", ordermsg);
		Assert.assertEquals(ordermsg, "Your order has been placed!");
	}
}
