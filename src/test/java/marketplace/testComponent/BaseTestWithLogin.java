package marketplace.testComponent;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import marketplace.pageObject.LandingPage;
import marketplace.pageObject.LoginPage;

public class BaseTestWithLogin extends BaseTest {
	LoginPage loginPage;
	
//	@BeforeMethod
//	@Override
//	public LandingPage launchApplication() {
//		super.launchApplication();
//		
//		loginPage = landingPage.goToLogin();
//		loginPage.loginApplication();
//		Assert.assertTrue(landingPage.verifyAccountLoggedIn());
//		return landingPage;
//	}

}
