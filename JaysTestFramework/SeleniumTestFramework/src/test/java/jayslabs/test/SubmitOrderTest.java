package jayslabs.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayslabs.pageobjects.CheckoutPage;
import jayslabs.pageobjects.OrderConfirmedPage;
import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;
import jayslabs.utils.CartPage;
import jayslabs.utils.OrdersPage;

public class SubmitOrderTest extends BaseTest {

	//String searchstr = "ZARA COAT 3";
	List<String> orders;
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(String uid, String pwd, String prodname) throws InterruptedException {

		
		ProdCatalog pc = landingPage.loginApplication(uid, pwd);

		pc.addProductToCart(prodname);
		CartPage cp = pc.openCart();

		boolean hasprod = cp.isProductAddedToCart(prodname);
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
	
	@DataProvider
	public Iterator<String[]> getData() {
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(new String[]{"anshika@gmail.com", "Iamking@000","ADIDAS ORIGINAL"});
		list.add(new String[]{"anshika@gmail.com", "Iamking@000","ZARA COAT 3"});
		list.add(new String[]{"anshika@gmail.com", "Iamking@000","IPHONE 13 PRO"});
		
		//list.add(new String[]{"shetty@gmail.com", "Iamking@000", "ZARA COAT 3"});
		
		return list.iterator();
	}

}
