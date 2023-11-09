package jayslabs.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayslabs.pageobjects.CartPage;
import jayslabs.pageobjects.CheckoutPage;
import jayslabs.pageobjects.LandingPage;
import jayslabs.pageobjects.OrderConfirmedPage;
import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;

public class SubmitOrderTest extends BaseTest{

@Test
public void submitOrder() throws InterruptedException {
		LandingPage lp = launchApplication();
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
		// driver.close();
	}

}
