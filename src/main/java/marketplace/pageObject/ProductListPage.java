package marketplace.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;

public class ProductListPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".bmd-bar-heading h1")
	WebElement headerSearchedProductName;
	
	public boolean verifySearchProductName(String productName) {
		String headerProductNameText = headerSearchedProductName.getText();
		if (headerProductNameText.equalsIgnoreCase(productName)) {
			return true;
		} else {
			return false;
		}
		
	}

}
