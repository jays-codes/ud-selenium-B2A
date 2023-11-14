package jayslabs.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;
import jayslabs.utils.CartPage;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void validateLoginError() throws InterruptedException {

		String expectederr = "Incorrect email or password.";
		String invalidpwd = "Iamking@003";
		
		landingPage.loginApplication("anshika@gmail.com", invalidpwd);
		String errmsg = landingPage.getErrorMsg();
		
		System.out.println(errmsg);
		
		AssertJUnit.assertFalse(expectederr.equalsIgnoreCase(errmsg)); //fail the test. should be false
	}
	
	@Test(groups= {"ErrorHandling"})
	public void validateUnknownProduct() throws InterruptedException {

		String searchstr = "ZARA COAT 3";
		String invalidprod = "ZARA COAT 33";

		// LandingPage lp = launchApplication();
		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

		pc.addProductToCart(searchstr);
		CartPage cp = pc.openCart();

		boolean hasprod = cp.isProductAddedToCart(invalidprod);
		AssertJUnit.assertTrue(hasprod); //fail the test. should be false

//		CheckoutPage cop = cp.checkout();
//		cop.selectCountry("ph");
//		OrderConfirmedPage ocp = cop.placeOrder();
//
//		boolean confirmed = ocp.isConfirmedPageShowing("Thankyou for the order.");
//
//		AssertJUnit.assertTrue(confirmed);
	}

}
