package marketplace.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.Product;

public class ProductListPage extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".bmd-bar-heading h1")
	WebElement headerSearchedProductName;
	
	@FindBy(css = ".oe_product_cart")
	List<WebElement> productCards;
	
	public boolean verifySearchProductName(String productName) {
		String headerProductNameText = headerSearchedProductName.getText();
		if (headerProductNameText.equalsIgnoreCase(productName)) {
			return true;
		} else {
			return false;
		}	
	}
	
	public ProductDetailPage selectProduct(Product product) {
		String selectedProductName = product.getName();
		String size = product.getSize();
		String color = product.getColor();
		
		if (!size.isEmpty() && !color.isEmpty()) {
		    selectedProductName += " " + size + " - " + color;
		} else if (!size.isEmpty() || !color.isEmpty()) {
		    selectedProductName += " " + (size.isEmpty() ? color : size);
		}
		
		boolean isProductFound = false;
		
		for(int i=0;i<productCards.size();i++) {
			WebElement productLink = productCards.get(i).findElement(By.cssSelector("a"));
			String productName = productCards.get(i).findElement(By.cssSelector("h6")).getText().toLowerCase();
			
			if (productName.equalsIgnoreCase(selectedProductName.toLowerCase())) {
				isProductFound = true;
				productLink.click();
				break;
			}
		}
		
		if (isProductFound == true) {
			ProductDetailPage productDetailPage = new ProductDetailPage(driver);
			return productDetailPage;
		} else {
			return null;
		}
	}

}
