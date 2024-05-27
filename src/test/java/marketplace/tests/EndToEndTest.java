package marketplace.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EndToEndTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.bhinneka.com/");
		driver.findElement(By.cssSelector(".bmd-login-button")).click();
		
		////div[text()='Email']
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement emailInput = driver.findElement(By.xpath("//div[text()='Email']/parent::div//input"));
		wait.until(ExpectedConditions.visibilityOf(emailInput));
		emailInput.sendKeys("testingjaja333@gmail.com");
		driver.findElement(By.xpath("//span[text()='Selanjutnya']/parent::button")).click();
		WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
		wait.until(ExpectedConditions.visibilityOf(passwordInput));
		passwordInput.sendKeys("Binetest1!");
		driver.findElement(By.xpath("//span[text()='Selanjutnya']/parent::button")).click();
		
		WebElement accountBanner = driver.findElement(By.cssSelector(".bmd-header-account-wrapper"));
		wait.until(ExpectedConditions.visibilityOf(accountBanner));
		Assert.assertTrue(accountBanner.isDisplayed());
		
		driver.quit();
		
	}

}
