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
	
	By cartButtonLocator = By.cssSelector(".bmd-btn-cart");
	By cartQuantityLocator = By.cssSelector(".my_cart_quantity");
	
	@FindBy(css = ".bmd-login-button")
	WebElement loginMenuButton;
	
	@FindBy(css = ".bmd-navbar-container input[type='search']")
	WebElement headerSearchInput;
	
	@FindBy(css = ".bmd-navbar-container button[title='Pencarian']")
	WebElement headerSearchButton;
	
	public LoginPage goToLogin() {
		loginMenuButton.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	public WebElement waitForElementToAppear(By locator, int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementToAppear(By locator) {
		return waitForElementToAppear(locator, 5);
	}
	
	public WebElement waitForElementToPresence(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public Boolean waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public Boolean waitForWebElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public List<WebElement> waitForAllElementsToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement waitForElementToClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public WebElement waitForWebElementToClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public ProductListPage searchByHeader(String productName) {
		headerSearchInput.clear();
		headerSearchInput.sendKeys(productName);
		headerSearchButton.click();
		ProductListPage productListPage = new ProductListPage(driver);
		return productListPage;
	}
	
	public int convertToPriceNumber(WebElement priceElement) {
		return Integer.parseInt(priceElement.getText().replace(".", ""));
	}
	
	public CartPage goToCartPage() {		
		WebElement cartMenuButton = waitForElementToAppear(cartButtonLocator);
		cartMenuButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public boolean isProductExistInCart() {
		try {
			WebElement cartQuantity = waitForElementToAppear(cartQuantityLocator);
			int cartQuantityNumber = Integer.parseInt(cartQuantity.getText());
			
			if (cartQuantityNumber > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			//no product in cart
			return false;
		}
	}

}
