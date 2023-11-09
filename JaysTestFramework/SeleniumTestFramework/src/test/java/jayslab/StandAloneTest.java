package jayslab;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import jayslab.pageobjects.CartPage;
import jayslab.pageobjects.CheckoutPage;
import jayslab.pageobjects.LandingPage;
import jayslab.pageobjects.OrderConfirmedPage;
import jayslab.pageobjects.ProdCatalog;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		ProdCatalog pc = lp.loginApplication("anshika@gmail.com", "Iamking@000");
		
		String searchstr = "ZARA COAT 3";
		pc.addProductToCart(searchstr);
		CartPage cp = pc.openCart();
		
		boolean hasprod = cp.isProductAddedToCart(searchstr);
		Assert.assertTrue(hasprod);
	
		CheckoutPage cop = cp.checkout();
		cop.selectCountry("ph");
		OrderConfirmedPage ocp = cop.placeOrder();
		
		boolean confirmed = ocp.isConfirmedPageShowing("Thankyou for the order.");
				
		Assert.assertTrue(confirmed);
		driver.close();
	}
	
	

}
