package marketplace.tests;

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
import marketplace.pageObject.LoginPage;
import marketplace.pageObject.ProductDetailPage;
import marketplace.pageObject.ProductListPage;
import marketplace.testComponent.BaseTest;
import marketplace.testComponent.BaseTestWithLogin;

public class SearchProductTest extends BaseTest {
	
	String productName = "Samsung Galaxy S24";
	
	@Test(dataProvider = "userData", groups = {"smoke_test"})
	public void searchProduct(UserData user) {
		LoginPage loginPage = landingPage.goToLogin();
		loginPage.loginApplication(user);
		
		List<Product> productData = user.getProduct();
		int totalProduct = productData.size();
		
		ProductListPage productListPage = null;
		
		for(int i=0;i<totalProduct;i++) {
			String productName = user.getProduct().get(i).getName();
			
			if (i == 0) {
				productListPage = landingPage.searchProductInsideBanner(productName);
			} else {
				productListPage.searchByHeader(productName);
			}
			
			boolean isProductNameMatch = productListPage.verifySearchProductName(productName);
			Assert.assertTrue(isProductNameMatch, "Match the product name searched in list");
		}
	}
	
	@DataProvider
	public Iterator<Object[]> userData() throws StreamReadException, DatabindException, IOException {
		String jsonFile = System.getProperty("user.dir") + "\\src\\test\\java\\marketplace\\data\\search-product-data.json";
		List<UserData> users = readJsonFile(jsonFile, UserData.class);
		return users.stream().map(user -> new Object[]{user}).iterator();
	}

}
