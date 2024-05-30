package marketplace.testComponent;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public static <T> List<T> readJsonFile(String filePath, Class<T> classData) throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, classData));
	}

}
