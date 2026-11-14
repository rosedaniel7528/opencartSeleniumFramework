package com.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductList extends BasePage {

	public ProductList(WebDriver driver){
		super(driver);
	}
	@FindBy(css=".product-layout")
	List<WebElement> listOfElement;
	
	public void selectProduct(String productName) { //selecting the product form the list
		reusableMethods.visibilityOfAllElements(listOfElement);
		List<WebElement> a = listOfElement;
		boolean added = false;
		for (WebElement d : a) {
			String name = d.findElement(By.cssSelector(" div[class='caption'] h4")).getText();
			if (name.contains(productName)) {
				log.info("Adding '{}' to the cart", name);
				d.findElement(By.cssSelector("button[onclick*='cart.add']")).click();
				added = true;
			}
		}
		if (!added) {
			log.warn("'{}' was not found in the product list", productName);
		}
	}
}
