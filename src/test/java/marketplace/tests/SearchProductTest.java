package marketplace.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import marketplace.pageObject.ProductListPage;
import marketplace.testComponent.BaseTestWithLogin;

public class SearchProductTest extends BaseTestWithLogin {
	
	String productName = "Samsung Galaxy S24";
	
	@Test
	public void homeBannerSearchProduct() {
		ProductListPage productListPage = landingPage.searchProductInsideBanner(productName);
		Assert.assertTrue(productListPage.verifySearchProductName(productName));
	}

}
