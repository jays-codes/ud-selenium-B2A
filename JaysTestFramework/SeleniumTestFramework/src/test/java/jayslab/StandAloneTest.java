package jayslab;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import jayslab.pageobjects.CartPage;
import jayslab.pageobjects.CheckoutPage;
import jayslab.pageobjects.LandingPage;
import jayslab.pageobjects.ProdCatalog;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		lp.loginApplication("anshika@gmail.com", "Iamking@000");
		
		ProdCatalog pc = new ProdCatalog(driver);
		List<WebElement> prods = pc.getProductList();
	
		String searchstr = "ZARA COAT 3";
		pc.addProductToCart(searchstr);
		pc.openCart();
		
		CartPage cp = new CartPage(driver);
		boolean hasprod = cp.isProductAddedToCart(searchstr);
		
		Assert.assertTrue(hasprod);
	
		cp.checkout();
		
		CheckoutPage cop = new CheckoutPage(driver);
		cop.selectCountry("ph");
		cop.placeOrder();
		
		String confirmed = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println("text: " +confirmed);
		
		Assert.assertTrue(confirmed.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}
	
	

}
