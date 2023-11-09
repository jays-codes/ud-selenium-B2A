package jayslab.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class ProdCatalog extends AbstractComponent{
	WebDriver driver;
	
	public ProdCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	@FindBy(css="button[routerlink='/dashboard/cart'")
	WebElement cartbtn;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastBy = By.id("toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String pname) {
		WebElement prod = products.stream()
		.filter(s->
			s.findElement(By.tagName("b"))
			.getText()
			.equals(pname))
		.findFirst().orElse(null);	
		
		return prod;
	}
	
	public void addProductToCart(String pname) {	
		getProductByName(pname).findElement(addToCartBy).click();
		waitForElementToAppear(toastBy);
		this.waitForElementToDisappear(animation);
	}
	
	public void openCart(){
		cartbtn.click();
	}
}
