package jayslab.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	By countryfieldBy = By.cssSelector(".user__address .text-validated");
	
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

	public void checkout() {
		checkoutbtn.click();
		this.waitForElementToAppear(countryfieldBy);
		
	}
	
	

	
}
