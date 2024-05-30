package marketplace.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EndToEndTest {

	public static void main(String[] args) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("disable-infobars");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.bhinneka.com/");
		driver.findElement(By.cssSelector(".bmd-login-button")).click();
		
		////div[text()='Email']
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		By emailInputLocator = By.xpath("//div[text()='Email']/parent::div//input");
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInputLocator));
		emailInput.sendKeys("testingjaja333@gmail.com");
		driver.findElement(By.xpath("//span[text()='Selanjutnya']/parent::button")).click();
		
		By passwordInputLocator = By.xpath("//input[@type='password']");
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInputLocator));
		passwordInput.sendKeys("Binetest1!");
		driver.findElement(By.xpath("//span[text()='Selanjutnya']/parent::button")).click();
		
		By accountBannerLocator = By.cssSelector(".bmd-header-account-wrapper");
		WebElement accountBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(accountBannerLocator));
		Assert.assertTrue(accountBanner.isDisplayed());
		
		driver.quit();
		
	}

}
