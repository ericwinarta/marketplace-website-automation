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
	By dangerWarningMessageLocator = By.cssSelector("div[color='danger'] span");
	By registerLinkLocator = By.cssSelector("a[href='/register']");
	
	@FindBy(xpath = "//span[text()='Selanjutnya']/parent::button")
	WebElement nextButton;
	
	@FindBy(css = "a[href='/register']")
	WebElement registerLink;
	
	public void loginApplication(UserData user) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(user.getEmail());
		
		WebElement passwordInput = inputEmailNextButtonAttempt(emailInput, passwordInputLocator);
		
		passwordInput.sendKeys(user.getPassword());
		nextButton.click();
	}
	
	public String verifyInvalidEmail(String email) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(email);
		
		WebElement warningMessage = inputEmailNextButtonAttempt(emailInput, dangerWarningMessageLocator);
		
		return warningMessage.getText();
	}
	
	public String verifyInvalidPassword(String email, String password) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(email);
		
		WebElement passwordInput = inputEmailNextButtonAttempt(emailInput, passwordInputLocator);
		
		passwordInput.sendKeys(password);
		nextButton.click();
		WebElement warningMessage = waitForElementToAppear(dangerWarningMessageLocator);
		return warningMessage.getText();
	}
	
	public WebElement inputEmailNextButtonAttempt(WebElement input, By locatorToWait) {
		int inputEmailAttempt = 0;
		int inputEmailMaxAttempt = 5;
		
		WebElement elementToWait = null;
		
		while (inputEmailAttempt < inputEmailMaxAttempt) {
			try {
				input.sendKeys(Keys.ENTER);
				elementToWait = waitForElementToAppear(locatorToWait);
				break;
			} catch (Exception e) {
				inputEmailAttempt++;
				
				if (inputEmailAttempt >= inputEmailMaxAttempt) {
					throw new RuntimeException("Next button failed for " + inputEmailMaxAttempt  + " clicked attempt, after type email address");
				}
			}
		}
		return elementToWait;
	}
	
	public RegisterPage goToRegister() {
		waitForElementToAppear(registerLinkLocator);
		registerLink.click();
		RegisterPage registerPage = new RegisterPage(driver);
		return registerPage;
	}

}
