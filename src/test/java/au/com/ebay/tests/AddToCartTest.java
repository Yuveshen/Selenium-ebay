package au.com.ebay.tests;

import org.junit.Assert;
//import org.junit.Test;

import org.testng.annotations.Test;

import au.com.ebay.dialogs.AddedToCartDialog;
import au.com.ebay.pages.CartPage;
import au.com.ebay.pages.HomePage;
import au.com.ebay.pages.ProductPage;
import au.com.ebay.pages.SearchResultsPage;;

public class AddToCartTest extends BaseTestSuite {

	@Test
	public void addTwoItemsToCartTest() throws Exception {
		
		String productOne = "Bookcase";
		String productTwo = "Soccer Ball";
		
		HomePage homePage = new HomePage(driver);
		homePage.setSearchString(productOne);
		SearchResultsPage searchResultsPage = homePage.clickSearchButton();
		ProductPage productPage = searchResultsPage.selectFirstProduct(productOne);
		String titleOne = productPage.getProductTitle();
		
		AddedToCartDialog addedToCartDialog = productPage.clickAddToCartBtn();
		productPage = addedToCartDialog.clickCloseDialog();
		Assert.assertEquals("Add first product : Cart Quantity is incorrect", 1, productPage.getCartCount());
		
		productPage.setSearchString(productTwo);
		searchResultsPage = productPage.clickSearchButton();
		productPage = searchResultsPage.selectFirstProduct(productTwo);
		String titleTwo = productPage.getProductTitle();
		
		addedToCartDialog = productPage.clickAddToCartBtn();
		productPage = addedToCartDialog.clickCloseDialog();
		Assert.assertEquals("Add second product : Cart Quantity is incorrect", 2, productPage.getCartCount());
		
		CartPage cartPage = productPage.clickCartButton();
		Assert.assertTrue("Product not in cart", cartPage.findProductInCart(titleOne));
		Assert.assertTrue("Product not in cart", cartPage.findProductInCart(titleTwo));
	}

}
