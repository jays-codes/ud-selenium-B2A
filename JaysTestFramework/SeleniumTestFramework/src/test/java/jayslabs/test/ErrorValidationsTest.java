package jayslabs.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import jayslabs.pageobjects.CartPage;
import jayslabs.pageobjects.CheckoutPage;
import jayslabs.pageobjects.OrderConfirmedPage;
import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void validateLoginError() throws InterruptedException {

		String expectederr = "Incorrect email or password.";
		String invalidpwd = "Iamking@003";
		
		landingPage.loginApplication("anshika@gmail.com", invalidpwd);
		String errmsg = landingPage.getErrorMsg();
		
		System.out.println(errmsg);
		
		AssertJUnit.assertTrue(expectederr.equalsIgnoreCase(errmsg));
	}
	
	@Test
	public void validateUnknownProduct() throws InterruptedException {

		String searchstr = "ZARA COAT 3";
		String invalidprod = "ZARA COAT 33";

		// LandingPage lp = launchApplication();
		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

		pc.addProductToCart(searchstr);
		CartPage cp = pc.openCart();

		boolean hasprod = cp.isProductAddedToCart(invalidprod);
		AssertJUnit.assertFalse(hasprod);

		CheckoutPage cop = cp.checkout();
		cop.selectCountry("ph");
		OrderConfirmedPage ocp = cop.placeOrder();

		boolean confirmed = ocp.isConfirmedPageShowing("Thankyou for the order.");

		AssertJUnit.assertTrue(confirmed);
	}

}
