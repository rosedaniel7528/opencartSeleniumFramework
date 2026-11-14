package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "collapse-checkout-option")
	WebElement checkoutoption;

	@FindBy(css = "input[value='guest']")
	WebElement radioguest;

	@FindBy(css = "button-account")
	WebElement Continue;

	@FindBy(id = "collapse-payment-address")
	WebElement accountandbill;

	@FindBy(id = "input-payment-firstname")
	WebElement firstname;

	@FindBy(id = "input-payment-lastname")
	WebElement lastname;

	@FindBy(id = "input-payment-email")
	WebElement email;

	@FindBy(id = "input-payment-telephone")
	WebElement number;

	@FindBy(id = "input-payment-address-1")
	WebElement adress;

	@FindBy(id = "input-payment-city")
	WebElement city;

	@FindBy(id = "input-payment-postcode")
	WebElement postcode;

	@FindBy(id = "input-payment-country")
	WebElement Country;

	@FindBy(id = "input-payment-zone")
	WebElement Zone;

	@FindBy(id = "button-guest")
	WebElement Billingcontinue;

	@FindBy(id = "collapse-shipping-method")
	WebElement deliverymethod;

	@FindBy(id = "button-shipping-method")
	WebElement deliverymethodcontinue;

	@FindBy(id = "collapse-payment-method")
	WebElement paymentmethod;

	@FindBy(css = "input[type*='checkbox'][name='agree']")
	WebElement Termscheckbox;

	@FindBy(id = "button-payment-method")
	WebElement paymentcontinue;

	@FindBy(id = "collapse-checkout-confirm")
	WebElement checkout;

	@FindBy(id = "button-confirm")
	WebElement confirmorder;

	public void fillCheckoutDetails() {
		log.info("Starting guest checkout");
		reusableMethods.visibilityOf(checkoutoption);
		radioguest.click();
		driver.findElement(By.id("button-account")).click();

		log.info("Filling billing details");
		reusableMethods.visibilityOf(accountandbill);
		firstname.sendKeys("Rose");
		lastname.sendKeys("Daniel");
		email.sendKeys("rose@gmail.com");
		number.sendKeys("2323423232");
		adress.sendKeys("nowhere");
		city.sendKeys("hyd");
		postcode.sendKeys("267887");
		WebElement country = Country;
		Select opt = new Select(country);
		opt.selectByVisibleText("Brazil");
		WebElement zone = Zone;
		Select opt2 = new Select(zone);
		opt2.selectByVisibleText("Acre");
		Billingcontinue.click();

		log.info("Selecting delivery method");
		reusableMethods.visibilityOf(deliverymethod);
		deliverymethodcontinue.click();

		log.info("Accepting terms and selecting payment method");
		reusableMethods.visibilityOf(paymentmethod);
		Termscheckbox.click();
		paymentcontinue.click();

		log.info("Confirming the order");
		reusableMethods.visibilityOf(checkout);
		confirmorder.click();
	}
}
