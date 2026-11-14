package com.opencart.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends BasePage {

	public SearchResult(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css=".product-thumb")
	List<WebElement> productlist;
	
	
	public void selectproduct(String productname) throws InterruptedException {
		
		reusableMethods.visibilityOfAllElements(productlist);
		//Thread.sleep(1000);
		List<WebElement> lop = productlist;
		List<WebElement> pro = lop.stream()
				.filter(c -> c.findElement(By.cssSelector("h4")).getText().equalsIgnoreCase(productname))
				.collect(Collectors.toList());

		if (pro.isEmpty()) {
			log.warn("'{}' was not found in the search results", productname);
		}
		log.info("Adding '{}' from the search results to the cart", productname);
		pro.get(0).findElement(By.cssSelector(".fa-shopping-cart")).click();
	}
	
}
