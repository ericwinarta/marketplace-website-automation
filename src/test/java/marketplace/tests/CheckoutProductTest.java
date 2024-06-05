package marketplace.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import marketplace.model.Product;
import marketplace.model.UserData;
import marketplace.pageObject.CartPage;
import marketplace.pageObject.CheckoutPage;
import marketplace.pageObject.LoginPage;
import marketplace.pageObject.ProductDetailPage;
import marketplace.pageObject.ProductListPage;
import marketplace.testComponent.BaseTest;

public class CheckoutProductTest extends BaseTest {
	
	@Test(dataProvider = "userData")
	public void checkoutProduct(UserData user) {
		
		LoginPage loginPage = landingPage.goToLogin();
		loginPage.loginApplication(user);
		
		List<Product> productData = user.getProduct();
		int totalProduct = productData.size();
		
		ProductListPage productListPage = null;
		ProductDetailPage productDetailPage = null;
		
		for(int i=0;i<totalProduct;i++) {
			Product product = user.getProduct().get(i);
			String productName = user.getProduct().get(i).getName();
			
			//search product
			if (i == 0) {
				productListPage = landingPage.searchProductInsideBanner(productName);
			} else {
				productDetailPage.searchByHeader(productName);
			}
			
			productListPage.verifySearchProductName(productName);
			
			productDetailPage = productListPage.selectProduct(product);
			if (productDetailPage == null) {
				Assert.assertTrue(false, "Selected product not exist in the list");
			}
			productDetailPage.addProductToCart(product);
			productDetailPage.closeModalAddToCart();
		}
		
		CartPage cartPage = productDetailPage.goToCartPage();
//		CartPage cartPage = landingPage.goToCartPage();
		boolean isProductCartMatch = cartPage.verifyCartProduct(productData);
		Assert.assertTrue(isProductCartMatch, "Match selected product with product displayed in cart");
		
		boolean isProductPriceCorrect = cartPage.verifyCartProductPrice();
		Assert.assertTrue(isProductPriceCorrect, "Check all total price in product cart is correct");
		
		CheckoutPage checkoutPage = cartPage.goToCheckoutPage();
		checkoutPage.fillAddressForm(user.getAddress());
		checkoutPage.chooseShippingDelivery(user.getShippingDelivery());
		assertTrue(checkoutPage.verifyTotalPayment(), "Check summary total payment");
		checkoutPage.openModalPaymentMethod();
	}
	
	@DataProvider
	public Iterator<Object[]> userData() throws StreamReadException, DatabindException, IOException {
		String jsonFile = System.getProperty("user.dir") + "\\src\\test\\java\\marketplace\\data\\order-product-data.json";
		List<UserData> users = readJsonFile(jsonFile, UserData.class);
		return users.stream().map(user -> new Object[]{user}).iterator();
	}

}
