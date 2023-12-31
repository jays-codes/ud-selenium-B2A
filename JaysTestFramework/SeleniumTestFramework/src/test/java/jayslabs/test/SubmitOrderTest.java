package jayslabs.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.sql.SQLException;
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
	
	@Test(dataProvider="getJDBCData", groups= {"Purchase"}, retryAnalyzer=jayslabs.test.components.Retrier.class)
	public void submitOrder(HashMap<String,String> input) throws InterruptedException {
		
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
	}
	
	@Test(dependsOnMethods= {"submitOrder"}, retryAnalyzer=jayslabs.test.components.Retrier.class)
	public void orderHistoryTest() {

		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage op = pc.viewOrdersPage();
		boolean allordersdisplayed = op.verifyOrderIsDisplayed(orders);
		
		Assert.assertTrue(allordersdisplayed);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		System.out.println("inside getjsondata...");
		
		String fstr = System.getProperty("user.dir") + "\\src\\test\\java\\jayslabs\\test\\data\\PurchaseOrder.json";

		List<HashMap<String, String>> datamap = getJsonDataToMap(fstr);
		return datamap.stream().map(s-> new Object[] {s}).toArray(Object[][]::new);
	}
	
	@DataProvider
	public Object[][] getxlsData() throws IOException {
		System.out.println("inside getxlsdata...");
		String fstr = System.getProperty("user.dir") + "\\src\\main\\resources\\seleniumTestData.xlsx";

		List<HashMap<String, String>> datamap = getxlsDataToMap(fstr, "2022");
		return datamap.stream().map(s-> new Object[] {s}).toArray(Object[][]::new);
	}
	
	@DataProvider
	public Object[][] getJDBCData() throws IOException, SQLException {
		System.out.println("inside getJDBCdata...");

		List<HashMap<String, String>> datamap = getJDBCDataToMap();
		return datamap.stream().map(s-> new Object[] {s}).toArray(Object[][]::new);
	}
	
}
