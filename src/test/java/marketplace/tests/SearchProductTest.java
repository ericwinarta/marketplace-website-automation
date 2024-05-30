package marketplace.tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import marketplace.model.UserData;
import marketplace.pageObject.LoginPage;
import marketplace.pageObject.ProductListPage;
import marketplace.testComponent.BaseTest;
import marketplace.testComponent.BaseTestWithLogin;

public class SearchProductTest extends BaseTest {
	
	String productName = "Samsung Galaxy S24";
	
	@Test(dataProvider = "userData")
	public void homeBannerSearchProduct(UserData user) {
		LoginPage loginPage = landingPage.goToLogin();
		loginPage.loginApplication(user);
		ProductListPage productListPage = landingPage.searchProductInsideBanner(productName);
		Assert.assertTrue(productListPage.verifySearchProductName(productName));
	}
	
	@DataProvider
	public Iterator<Object[]> userData() throws StreamReadException, DatabindException, IOException {
		String jsonFile = System.getProperty("user.dir") + "\\src\\test\\java\\marketplace\\data\\order-product-data.json";
		List<UserData> users = readJsonFile(jsonFile, UserData.class);
		return users.stream().map(user -> new Object[]{user}).iterator();
	}

}
