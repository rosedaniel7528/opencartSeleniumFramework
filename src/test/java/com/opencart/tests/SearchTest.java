package com.opencart.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.base.BaseTest;
import com.opencart.listeners.RetryAnalyzer;
import com.opencart.pages.CartPage;
import com.opencart.pages.Cartdialoguebox;
import com.opencart.pages.CheckoutPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.OrderSuccessPage;
import com.opencart.pages.SearchResult;
import com.opencart.utilities.JsonDataReader;

public class SearchTest extends BaseTest {

	@Test(dataProvider = "searchData", retryAnalyzer = RetryAnalyzer.class)
	public void searchBox(HashMap<String, String> data) throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String productname = data.get("product");
		// home page
		HomePage homepage = new HomePage(getDriver());
		homepage.searchbox(productname);
		// product list
		SearchResult searchresult = new SearchResult(getDriver());
		searchresult.selectproduct(productname);
		// cart dialogue box
		Cartdialoguebox box = new Cartdialoguebox(getDriver());
		box.clickChekout();
		// cart page
		CartPage cartpage = new CartPage(getDriver());
		cartpage.checkproductincart(productname); // verifying the product in cart
		// checkout page
		CheckoutPage checkout = new CheckoutPage(getDriver());
		checkout.fillCheckoutDetails();
		// order success page
		OrderSuccessPage success = new OrderSuccessPage(getDriver());
		success.orderconfirmation(); // Verifying success msg
	}

	@DataProvider
	public Object[][] searchData() throws IOException {
		List<HashMap<String, String>> data = JsonDataReader.read("testdata/searchbox.json");
		return new Object[][] { { data.get(0) } };
	}
}
