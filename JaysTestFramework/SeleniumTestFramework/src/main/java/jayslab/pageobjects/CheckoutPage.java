package jayslab.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css=".user__address .text-validated")
	WebElement ctrytxt;
	
	@FindBy(css=".ta-results button")
	WebElement ctrybtn;
	
	@FindBy(css=".btnn")
	WebElement placeorderbtn;
	
	By resultsbtnBy = By.cssSelector(".ta-results button");
	By confirmorderBy = By.cssSelector(".hero-primary");
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}

	public void selectCountry(String string) {

		//ctrytxt.sendKeys("ph");
		Actions a = new Actions(driver);
		a.sendKeys(ctrytxt, string).build().perform();

		this.waitForElementToAppear(resultsbtnBy);
		ctrybtn.click();
	}

	public OrderConfirmedPage placeOrder() {
		placeorderbtn.click();
		this.waitForElementToAppear(confirmorderBy);
		return new OrderConfirmedPage(driver);
	}

}
