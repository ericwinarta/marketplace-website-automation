package marketplace.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.UserData;

public class RegisterPage extends AbstractComponent {
	
	WebDriver driver;
	
	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By accountTypeOptionLocator = By.cssSelector(".css-1uhh5l2");
	By registerFormLocator = By.cssSelector(".css-1t6ms5n");
	By registerButtonLocator = By.xpath("//span[text()='Register']/parent::button");
	By registerModalTitle = By.cssSelector("h4");
	
	@FindBy(xpath = "//img[@alt='Personal']/parent::div")
	WebElement personalOption;
	
	@FindBy(xpath = "//span[text()='Selanjutnya']/parent::button")
	WebElement nextButton;
	
	@FindBy(xpath = "//div[text()='Nama Depan']/preceding-sibling::div//input")
	WebElement firstNameInput;
	
	@FindBy(xpath = "//div[contains(text(), 'Nama Belakang')]/preceding-sibling::div//input")
	WebElement lastNameInput;
	
	@FindBy(xpath = "//div[text()='Email']/preceding-sibling::div//input")
	WebElement emailInput;
	
	@FindBy(xpath = "//div[text()='Nomor Ponsel']/preceding-sibling::div//input")
	WebElement phoneNumberInput;
	
	@FindBy(xpath = "//div[text()='Password']/preceding-sibling::div//input")
	WebElement passwordInput;
	
	@FindBy(xpath = "//span[text()='Register']/parent::button")
	WebElement registerButton;
	
	@FindBy(css = ".tunggal-modal-body")
	WebElement modalBody;

	@FindBy(css = "h4")
	WebElement modalTitle;
	
	@FindBy(css = ".modal-close")
	WebElement closeModalButton;
	
	public String registerPersonalAccount(UserData user) {
		waitForElementToAppear(accountTypeOptionLocator);
		personalOption.click();
		nextButton.click();
		
		waitForElementToAppear(registerFormLocator);
		firstNameInput.sendKeys(user.getFirstName());
		lastNameInput.sendKeys(user.getLastName());
		emailInput.sendKeys(user.getEmail());
		phoneNumberInput.sendKeys(user.getPhoneNumber());
		passwordInput.sendKeys(user.getPassword());
		waitForElementToClickable(registerButtonLocator);
		
		
		int registerClickAttempt = 0;
		int registerClickMaxAttempt = 5;
		
		while (registerClickAttempt < registerClickMaxAttempt) {
			try {
				registerButton.click();
				waitForElementToAppear(registerModalTitle);
				break;
			} catch (Exception e) {
				registerClickAttempt++;
				
				if (registerClickAttempt >= registerClickMaxAttempt) {
					throw new RuntimeException("Register button failed for " + registerClickMaxAttempt  + " clicked attempt");
				}
			}
		}
		
		
//		registerButton.click();
//		waitForElementToAppear(registerModalTitle);
		
		String registerMessage;
		
		if (modalTitle.getText().equals("Registrasi Berhasil")) {
			registerMessage = modalBody.findElement(By.cssSelector("p")).getText();
		} else {
			registerMessage = modalBody.getText();
		}
		
		return registerMessage;
	}

}
