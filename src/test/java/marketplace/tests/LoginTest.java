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
import marketplace.testComponent.BaseTest;

public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "login-data-provider", dataProviderClass = LoginTest.class , groups = {"smoke_test"})
	public void loginApplication(UserData user) {
		LoginPage loginPage = landingPage.goToLogin();
		loginPage.loginApplication(user);
		Assert.assertTrue(landingPage.verifyAccountLoggedIn());
	}
	
	@DataProvider(name = "login-data-provider")
	public Iterator<Object[]> userData() throws StreamReadException, DatabindException, IOException {
		String jsonFile = System.getProperty("user.dir") + "\\src\\test\\java\\marketplace\\data\\login-data.json";
		List<UserData> users = readJsonFile(jsonFile, UserData.class);
		return users.stream().map(user -> new Object[]{user}).iterator();	
	}
	
	@Test(groups = {"error_validation"})
	public void loginWithInvalidEmail() {
		String invalidEmail = "testing+failed@gmail.com";
		LoginPage loginPage = landingPage.goToLogin();
		String warningMessage = loginPage.verifyInvalidEmail(invalidEmail);
		Assert.assertEquals(warningMessage, "Email belum terdaftar");
	}
	
	@Test(groups = {"error_validation"})
	public void loginWithInvalidPassword() {
		String email = "testingjaja333@gmail.com";
		String invalidPassword = "Wrongpassword1!";
		LoginPage loginPage = landingPage.goToLogin();
		String warningMessage = loginPage.verifyInvalidPassword(email, invalidPassword);
		Assert.assertEquals(warningMessage, "Login error! Kata sandi salah");
	}
	
}
