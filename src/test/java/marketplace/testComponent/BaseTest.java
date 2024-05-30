package marketplace.testComponent;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import marketplace.pageObject.LandingPage;

public class BaseTest {
	
	WebDriver driver;
	public LandingPage landingPage;
	
	public void initializeDriver() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public LandingPage launchApplication() {
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToApp();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
//		driver.quit();
	}

}
