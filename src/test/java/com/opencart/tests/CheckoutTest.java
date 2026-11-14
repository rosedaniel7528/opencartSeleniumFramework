package com.opencart.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.pages.CartPage;
import com.opencart.pages.Cartdialoguebox;
import com.opencart.pages.CheckoutPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.OrderSuccessPage;
import com.opencart.pages.ProductList;
import com.opencart.utilities.JsonDataReader;

public class CheckoutTest extends BaseTest {

	@Test(dataProvider = "checkoutData", retryAnalyzer = RetryAnalyzer.class)
	public void placeOrder(HashMap<String, String> data) throws InterruptedException {

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().manage().window().maximize();
		String productName = data.get("product");
		// home page
		HomePage homepage = new HomePage(getDriver());
		homepage.clickMenu(); // select category
		// product list menu
		ProductList productlist = new ProductList(getDriver());
		productlist.selectProduct(productName); // select product move to cart
		// cart dialogue box
		Cartdialoguebox box = new Cartdialoguebox(getDriver());
		box.clickChekout();
		// cart page
		CartPage cartpage = new CartPage(getDriver());
		cartpage.checkproductincart(productName); // verifying the product in cart
		// checkout page
		CheckoutPage checkout = new CheckoutPage(getDriver());
		checkout.fillCheckoutDetails();
		// order success page
		OrderSuccessPage success = new OrderSuccessPage(getDriver());
		success.orderconfirmation(); // Verifying success msg
	}

	@DataProvider
	public Object[][] checkoutData() throws IOException {
		return JsonDataReader.asDataProvider(JsonDataReader.read("testdata/checkout.json"));
	}
}
