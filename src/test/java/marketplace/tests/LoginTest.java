package marketplace.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import marketplace.pageObject.LoginPage;
import marketplace.testComponent.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginApplication() {
		LoginPage loginPage = landingPage.goToLogin();
		loginPage.loginApplication();
		Assert.assertTrue(landingPage.verifyAccountLoggedIn());
	}

}
