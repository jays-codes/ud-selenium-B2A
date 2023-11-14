package jayslabs.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

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
	public void submitOrder(HashMap<String,String> input) throws InterruptedException {
		ExtentTest test = extent.createTest(getCurrentMethod());
		
		ProdCatalog pc = landingPage.loginApplication(input.get("uid"), input.get("pwd"));

		pc.addProductToCart(input.get("prodname"));
		CartPage cp = pc.openCart();

		boolean hasprod = cp.isProductAddedToCart(input.get("prodname"));
		AssertJUnit.assertTrue(hasprod);

		CheckoutPage cop = cp.checkout();
		cop.selectCountry("ph");
		OrderConfirmedPage ocp = cop.placeOrder();

		boolean confirmed = ocp.isConfirmedPageShowing("Thankyou for the order.");
		orders = ocp.getOrderIds();
		Assert.assertTrue(confirmed);
		test.addScreenCaptureFromBase64String(getCurrentMethod());
		test.fail("fail");
		extent.flush();
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {

		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage op = pc.viewOrdersPage();
		boolean allordersdisplayed = op.verifyOrderIsDisplayed(orders);
		
		Assert.assertTrue(allordersdisplayed);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String fstr = System.getProperty("user.dir") + "\\src\\test\\java\\jayslabs\\test\\data\\PurchaseOrder.json";

		List<HashMap<String, String>> datamap = getJsonDataToMap(fstr);
		return datamap.stream().map(s-> new Object[] {s}).toArray(Object[][]::new);
	}
	
}
