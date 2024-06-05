package marketplace.abstractComponent;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import marketplace.pageObject.CartPage;
import marketplace.pageObject.LoginPage;
import marketplace.pageObject.ProductListPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".bmd-login-button")
	WebElement loginMenuButton;
	
	@FindBy(css = ".bmd-navbar-container input[type='search']")
	WebElement headerSearchInput;
	
	@FindBy(css = ".bmd-navbar-container button[title='Pencarian']")
	WebElement headerSearchButton;
	
	@FindBy(css = ".bmd-btn-cart")
	WebElement cartMenuButton;
	
	public LoginPage goToLogin() {
		loginMenuButton.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	public WebElement waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public Boolean waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public List<WebElement> waitForAllElementsToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitForElementToClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public ProductListPage searchByHeader(String productName) {
		headerSearchInput.sendKeys(productName);
		headerSearchButton.click();
		ProductListPage productListPage = new ProductListPage(driver);
		return productListPage;
	}
	
	public int convertToPriceNumber(WebElement priceElement) {
		return Integer.parseInt(priceElement.getText().replace(".", ""));
	}
	
	public CartPage goToCartPage() {
		cartMenuButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

}
