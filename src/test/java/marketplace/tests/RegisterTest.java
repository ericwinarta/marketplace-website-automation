package marketplace.tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

import marketplace.model.UserData;
import marketplace.pageObject.LoginPage;
import marketplace.pageObject.RegisterPage;
import marketplace.testComponent.BaseTest;

public class RegisterTest extends BaseTest {
	
	@Test(dataProvider = "userData", groups = {"smoke_test"})
	public void register(UserData user) {
		LoginPage loginPage = landingPage.goToLogin();
		RegisterPage registerPage = loginPage.goToRegister();
		String registerMessage = registerPage.registerPersonalAccount(user);
		Assert.assertEquals(registerMessage, "Terima kasih telah melakukan registrasi. "
				+ "Anda akan menerima email dan mohon segera aktifkan akun. "
				+ "Untuk kelengkapan data diperlukan, tim kami segera menghubungi anda");
	}
	
	@DataProvider
	public Iterator<Object[]> userData() throws StreamReadException, DatabindException, IOException {
		String jsonFile = System.getProperty("user.dir") + "\\src\\test\\java\\marketplace\\data\\register-user-data.json";
		List<UserData> users = readJsonFile(jsonFile, UserData.class);
		return users.stream().map(user -> new Object[]{user}).iterator();
	}

}
