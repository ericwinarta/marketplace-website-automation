package marketplace.tests;

import org.testng.annotations.Test;

import marketplace.testComponent.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginApplication() {
		landingPage.goToLogin();
	}

}
