package jayslabs.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jayslabs.pageobjects.CartPage;

public class AbstractComponent {

	WebDriver driver;
	
	@FindBy(css="button[routerlink='/dashboard/cart'")
	WebElement cartbtn;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement webEl) throws InterruptedException {
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webEl));
	}

	public CartPage openCart(){
		cartbtn.click();
		return new CartPage(driver);
	}
}
