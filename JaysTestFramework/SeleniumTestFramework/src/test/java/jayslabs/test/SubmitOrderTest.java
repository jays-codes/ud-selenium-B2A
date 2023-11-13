package jayslabs.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayslabs.pageobjects.CartPage;
import jayslabs.pageobjects.CheckoutPage;
import jayslabs.pageobjects.OrderConfirmedPage;
import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;
import jayslabs.utils.OrdersPage;

public class SubmitOrderTest extends BaseTest {

	String searchstr = "ZARA COAT 3";
	List<String> orders;
	
	@Test
	public void submitOrder() throws InterruptedException {

		
		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

		pc.addProductToCart(searchstr);
		CartPage cp = pc.openCart();

		boolean hasprod = cp.isProductAddedToCart(searchstr);
		AssertJUnit.assertTrue(hasprod);

		CheckoutPage cop = cp.checkout();
		cop.selectCountry("ph");
		OrderConfirmedPage ocp = cop.placeOrder();

		boolean confirmed = ocp.isConfirmedPageShowing("Thankyou for the order.");
		orders = ocp.getOrderIds();
		Assert.assertTrue(confirmed);
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage op = pc.viewOrdersPage();
		boolean allordersdisplayed = op.verifyOrderIsDisplayed(orders);
		
		Assert.assertTrue(allordersdisplayed);
	}

}
