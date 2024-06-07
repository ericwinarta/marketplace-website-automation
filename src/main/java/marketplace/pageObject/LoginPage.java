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
	
	@FindBy(xpath = "//span[text()='Selanjutnya']/parent::button")
	WebElement nextButton;
	
	public void loginApplication(UserData user) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(user.getEmail());
		
		int inputEmailAttempt = 0;
		int inputEmailMaxAttempt = 5;
		
		WebElement passwordInput = null;
		
		while (inputEmailAttempt < inputEmailMaxAttempt) {
			try {
				nextButton.sendKeys(Keys.ENTER);
				passwordInput = waitForElementToAppear(passwordInputLocator);
				break;
			} catch (Exception e) {
				inputEmailAttempt++;
				
				if (inputEmailAttempt >= inputEmailMaxAttempt) {
					throw new RuntimeException("Next button failed for " + inputEmailMaxAttempt  + " clicked attempt, after type email address");
				}
			}
		}
		
		passwordInput.sendKeys(user.getPassword());
		nextButton.click();
	}
	
	public String verifyInvalidEmail(String email) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(email);
		
		int warningBoxAttempt = 0;
		int warningBoxMaxAttempt = 3;
		
		while (warningBoxAttempt < warningBoxMaxAttempt) {
			try {
				nextButton.sendKeys(Keys.ENTER);
				WebElement warningMessage = waitForElementToAppear(dangerWarningMessageLocator);
				return warningMessage.getText();
			} catch (Exception e) {
				warningBoxAttempt++;
				
				if (warningBoxAttempt >= warningBoxMaxAttempt) {
					break;
				}
			}
		}
		
		return null;
	}
	
	public String verifyInvalidPassword(String email, String password) {
		WebElement emailInput = waitForElementToAppear(emailInputLocator);
		emailInput.sendKeys(email);
		
		int inputEmailAttempt = 0;
		int inputEmailMaxAttempt = 3;
		
		WebElement passwordInput = null;
		
		while (inputEmailAttempt < inputEmailMaxAttempt) {
			try {
				nextButton.sendKeys(Keys.ENTER);
				passwordInput = waitForElementToAppear(passwordInputLocator);
				break;
			} catch (Exception e) {
				inputEmailAttempt++;
				
				if (inputEmailAttempt >= inputEmailMaxAttempt) {
					break;
				}
			}
		}
		
		passwordInput.sendKeys(password);
		nextButton.click();
		WebElement warningMessage = waitForElementToAppear(dangerWarningMessageLocator);
		return warningMessage.getText();
	}

}
