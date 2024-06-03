package marketplace.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.Product;

public class ProductDetailPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By sizeOptionsLocator = By.cssSelector("li[data-attribute_name='Size'] ul");
	By colorOptionsLocator = By.cssSelector("li[data-attribute_name='Color'] ul");
	By closeModalAddToCartButtonLocator = By.xpath("//button/span[text()='Kembali Berbelanja']");
	
	@FindBy(css = "li[data-attribute_name='Size'] li")
	List<WebElement> sizeOptions;
	
	@FindBy(css = "li[data-attribute_name='Color'] li")
	List<WebElement> colorOptions;
	
	@FindBy(id = "add_to_cart")
	WebElement addToCartButton;
	
	public void addProductToCart(Product product) {
		if (product.getSize() != null && !product.getSize().isEmpty()) {
			waitForElementToAppear(sizeOptionsLocator);
			WebElement selectedSize = sizeOptions.stream().filter(size -> size.findElement(By.cssSelector("label span")).getText().equalsIgnoreCase(product.getSize())).findFirst().orElse(null);
			
			if (selectedSize != null) {
				Actions action = new Actions(driver);
				action.moveToElement(selectedSize).scrollByAmount(0, 150).build().perform();
				action.moveToElement(selectedSize).click().build().perform();
			}
		}
		
		if (product.getColor() != null && !product.getColor().isEmpty()) {
			waitForElementToAppear(colorOptionsLocator);
			WebElement selectedColor = colorOptions.stream().filter(size -> size.findElement(By.cssSelector("label span")).getText().equalsIgnoreCase(product.getColor())).findFirst().orElse(null);
			
			if (selectedColor != null) {
				Actions action = new Actions(driver);
				action.moveToElement(selectedColor).scrollByAmount(0, 150).build().perform();
				action.moveToElement(selectedColor).click().build().perform();
			}
		}
		
		addToCartButton.click();
	}
	
	public void closeModalAddToCart() {
		WebElement closeModalButton = waitForElementToAppear(closeModalAddToCartButtonLocator);
		closeModalButton.click();
	}

}
