package marketplace.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.UserData;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By emailInputLocator = By.xpath("//div[text()='Email']/parent::div//input");
	By passwordInputLocator = By.xpath("//input[@type='password']");
	
	@FindBy(xpath = "//span[text()='Selanjutnya']/parent::button")
	WebElement nextButton;
	
	public void loginApplication(UserData user) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(user.getEmail());
		nextButton.sendKeys(Keys.ENTER);
		
		WebElement passwordInput = waitForElementToAppear(passwordInputLocator);
		passwordInput.sendKeys(user.getPassword());
		nextButton.click();
	}

}
