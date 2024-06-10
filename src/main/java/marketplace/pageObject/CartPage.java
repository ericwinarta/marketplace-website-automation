package marketplace.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import marketplace.abstractComponent.AbstractComponent;
import marketplace.model.Product;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	By cartProductListLocator = By.cssSelector(".o_cart_product");
	
	@FindBy(css = ".o_cart_product")
	List<WebElement> cartProductList;
	
	@FindBy(css = "#website_sale_amount .oe_currency_value")
	WebElement grandTotalPriceEle;
	
	@FindBy(css = ".btn-primary")
	WebElement buyNowButton;
	
	@FindBy(css = ".cart-container a[href='/']")
	WebElement backToHomeButton;
	
	public boolean verifyCartProduct(List<Product> productData) {
		waitForAllElementsToAppear(cartProductListLocator);
		
		if (cartProductList.size() == productData.size()) {
			
			List<WebElement> cartProductsInfo = driver.findElements(By.cssSelector(".td-product_name"));
			
			for(WebElement productCart : cartProductsInfo) {
				String productName = productCart.findElement(By.cssSelector("a strong")).getText();
				String productVariant = productCart.findElement(By.cssSelector(".product-variant")).getText();
				
				String productSize = "";
				String productColor = "";
				
				if (productVariant.toLowerCase().contains("size") && productVariant.toLowerCase().contains("color")) {
					productSize = productVariant.split(", ")[0].split(": ")[1];
					productColor = productVariant.split(", ")[1].split(": ")[1];
				} else {
					String value = productVariant.split(": ")[1];
					if (productVariant.toLowerCase().contains("size")) {
						productSize = value;
					} else if (productVariant.toLowerCase().contains("color")) {
						productColor = value;
					}
				}
				
				boolean isProductFound = false;
				
				for(Product product : productData) {
					if (productName.equalsIgnoreCase(product.getName())) {
						isProductFound = true;
						if (
							(!productSize.isEmpty() && !productSize.equalsIgnoreCase(product.getSize()))
							|| (!productColor.isEmpty() && !productColor.equalsIgnoreCase(product.getColor()))
						) {
							System.out.println("This product variant is not match : " + product.getName());
							return false;
						}
					}
				}
				
				if (isProductFound == false) {
					return false;
				}
				
			}
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean verifyCartProductPrice() {
		int sumTotalProductPrice = 0;
		
		for(WebElement cartProduct : cartProductList) {
			String productPriceDisplay = cartProduct.findElement(By.cssSelector(".product-price .oe_currency_value")).getText().replace(".", "");
			int productPrice = Integer.parseInt(productPriceDisplay);
			int quantity = Integer.parseInt(cartProduct.findElement(By.cssSelector(".quantity")).getAttribute("value"));
			String totalProductPriceDisplay = cartProduct.findElement(By.cssSelector(".product-subtotal .oe_currency_value")).getText().replace(".", "");
			int totalProductPrice = Integer.parseInt(totalProductPriceDisplay);
			
			if (productPrice * quantity != totalProductPrice) {
				System.out.println("Total product price is not correct");
				return false;
			}
			
			sumTotalProductPrice += totalProductPrice;
		};
		
		int grandTotalPrice = Integer.parseInt(grandTotalPriceEle.getText().replace(".", ""));
		
		if (sumTotalProductPrice != grandTotalPrice) {
			System.out.println("Grand total price is not correct");
			return false;
		}
		
		return true;
	}
	
	public void clearAllCartProduct() {
		By trashButtonLocator = By.cssSelector("a[data-bs-target*='deleteSingleProduct']");
		By deleteProductButtonLocator = By.cssSelector(".modal.show .js_delete_product");
		
		waitForAllElementsToAppear(trashButtonLocator);
		int totalProduct = cartProductList.size();
		
		while(totalProduct > 0) {
			WebElement firstTrashButton = driver.findElements(trashButtonLocator).get(0);
			waitForWebElementToClickable(firstTrashButton);
			firstTrashButton.click();
			
			WebElement deleteButton = waitForElementToAppear(deleteProductButtonLocator);
			deleteButton.click();
			totalProduct--;
			waitForWebElementToDisappear(firstTrashButton);
		}
		
		backToHomeButton.click();
	}
	
	public CheckoutPage goToCheckoutPage() {
		buyNowButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}

}
