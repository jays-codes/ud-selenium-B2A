package jayslabs.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.pageobjects.CheckoutPage;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	private WebElement checkoutbtn;
	
	private By countryfieldBy = By.cssSelector(".user__address .text-validated");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isProductAddedToCart(String searchstr) {
		boolean prodincart = cartproducts.stream().anyMatch(s->s.getText().equals(searchstr));
		//System.out.println("product is in cart: " +prodincart);
		return prodincart;
	}

	public CheckoutPage checkout() {
		checkoutbtn.click();
		this.waitForElementToAppear(countryfieldBy);
		return new CheckoutPage(driver);
		
	}
	
	

	
}
