package marketplace.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import marketplace.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By accountBannerLocator = By.cssSelector(".bmd-header-account-wrapper");
	
	@FindBy(css = ".s_bcom_main_banner .oe_search_box")
	WebElement searchInput;
	
	@FindBy(css = ".s_bcom_main_banner .oe_search_button")
	WebElement searchButton;
	
	public void goToApp() {
		driver.get("https://www.bhinneka.com/");
	}
	
	public boolean verifyAccountLoggedIn() {
		WebElement accountBanner = waitForElementToAppear(accountBannerLocator);
		return accountBanner.isDisplayed();
	}
	
	public ProductListPage searchProductInsideBanner(String productName) {
		searchInput.sendKeys(productName);
		searchButton.click();
		ProductListPage productListPage = new ProductListPage(driver);
		return productListPage;
	}

}
