package marketplace.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.Address;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By addAddressButtonLocator = By.xpath("//span[text()='Tambah Alamat Pengiriman']/parent::button");
	By addressModalLocator = By.id("address_modal");
	By shippingDeliveryDropdownLocator = By.cssSelector("#delivery_method ul[class*='show']");
	
	@FindBy(xpath = "//span[text()='Tambah Alamat Pengiriman']/parent::button")
	WebElement addAddressButton;
	
	@FindBy(css = "input[name='judul_alamat']")
	WebElement addressTitleInput;
	
	@FindBy(css = "input[name='nama_lengkap']")
	WebElement fullNameInput;
	
	@FindBy(css = "input[name='nomor_ponsel']")
	WebElement phoneNumberInput;
	
	@FindBy(css = "textarea[name='alamat']")
	WebElement addressDetailInput;
	
	@FindBy(css = ".modal__input_submit")
	WebElement submitAddress;
	
	@FindBy(xpath = "//span[text()='Batal']/parent::button")
	WebElement closeAddressModal;
	
	@FindBy(id = "shipping-select")
	WebElement shippingDeliveryButton;
	
	@FindBy(id = "o_payment_submit_button")
	WebElement paymentMethodButton;
	
	@FindBy(xpath = "//div[@id='checkout_summary']//div[@class='p-3']/div[1]/div[1]/p//span[@class='oe_currency_value']")
	WebElement totalProductPrice;
	
	@FindBy(css = "#order_delivery .oe_currency_value")
	WebElement totalShippingPrice;
	
	@FindBy(css = "#order_total .oe_currency_value")
	WebElement grandTotalPrice;
	
	public void fillAddressForm(Address userAddress) {
		try {
			waitForElementToAppear(addAddressButtonLocator);
			
			if (addAddressButton.isDisplayed()) {
				addAddressButton.click();
				waitForElementToAppear(addressModalLocator);
				addressTitleInput.sendKeys(userAddress.getAddressTitle());
				fullNameInput.sendKeys(userAddress.getFullName());
				phoneNumberInput.sendKeys(userAddress.getPhoneNumber());
				addressDetailInput.sendKeys(userAddress.getAddressDetail());
				selectAddressDropdownForm("select-province", userAddress.getProvince());
				selectAddressDropdownForm("select-city", userAddress.getCity());
				selectAddressDropdownForm("select-district", userAddress.getDistrict());
				selectAddressDropdownForm("select-subdistrict", userAddress.getSubdistrict());
				submitAddress.click();
				waitForElementToDisappear(addressModalLocator);
			}
		} catch (Exception e) {
			System.out.println("Add button not appear");
		}
	}
	
	public void selectAddressDropdownForm(String selectId, String value) {
		By inputLocator = By.xpath("//select[@id='" + selectId + "']/following-sibling::div//input");
		By inputActiveLocator = By.xpath("//select[@id='" + selectId + "']/following-sibling::div//div[contains(@class, 'input-active')]");
		By dropdownOptionsLocator = By.xpath("//select[@id='" + selectId  + "']/following-sibling::div//div[contains(@class, 'selectize-dropdown-content')]//div[contains(@class, 'option')]");
		
		waitForElementToClickable(inputLocator);
		driver.findElement(inputLocator).click();
		waitForElementToAppear(inputActiveLocator);
		List<WebElement> options = driver.findElements(dropdownOptionsLocator);
		WebElement selectedOption = options.stream().filter(prov -> prov.getText().equalsIgnoreCase(value)).findFirst().orElse(null);
		selectedOption.click();
	}
	
	public void chooseShippingDelivery(String shippingDelivery) {
		waitForElementToAppear(By.id("shipping-select"));
		shippingDeliveryButton.click();
		WebElement shippingDeliveryDropdown = waitForElementToAppear(shippingDeliveryDropdownLocator);
		List<WebElement> shippingOptions = shippingDeliveryDropdown.findElements(By.cssSelector("#delivery_method ul[class*='show'] li"));
		WebElement selectedShipping = shippingOptions.stream().filter(opt -> opt.findElement(By.cssSelector("span label")).getText().equalsIgnoreCase(shippingDelivery)).findFirst().orElse(null);
		selectedShipping.click();
		
		By cartContainerLocator = By.cssSelector(".cart-container");
		waitForElementToDisappear(shippingDeliveryDropdownLocator);
		waitForElementToAppear(cartContainerLocator);
	}
	
	public boolean verifyTotalPayment() {
		int totalProductPriceNumber = convertToPriceNumber(totalProductPrice);
		int totalShippingPriceNumber = convertToPriceNumber(totalShippingPrice);
		int grandTotalPriceNumber = convertToPriceNumber(grandTotalPrice);
		
		if (totalProductPriceNumber + totalShippingPriceNumber != grandTotalPriceNumber) {
			return false;
		} else {
			return true;
		}
	}
	
	public void openModalPaymentMethod() {
		paymentMethodButton.click();
	}

}
