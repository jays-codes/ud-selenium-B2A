package jayslabs.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProdCatalog pc = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrdersPage op = pc.viewOrdersPage();
		boolean allordersdisplayed = op.verifyOrderIsDisplayed(orders);
		
		Assert.assertTrue(allordersdisplayed);
	}
	
	
//	@DataProvider
//	public Iterator<String[]> getData() {
//		List<String[]> list = new ArrayList<String[]>();
//		
//		list.add(new String[]{"anshika@gmail.com", "Iamking@000","ADIDAS ORIGINAL"});
//		list.add(new String[]{"anshika@gmail.com", "Iamking@000","ZARA COAT 3"});
//		list.add(new String[]{"anshika@gmail.com", "Iamking@000","IPHONE 13 PRO"});
//		
//		//list.add(new String[]{"shetty@gmail.com", "Iamking@000", "ZARA COAT 3"});
//		
//		return list.iterator();
//	}

//	@DataProvider
//	public Object[][] getData() {
//		List<String[]> list = new ArrayList<String[]>();
//	
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("uid", "anshika@gmail.com");
//		map1.put("pwd", "Iamking@000");
//		map1.put("prodname", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("uid", "anshika@gmail.com");
//		map2.put("pwd", "Iamking@000");
//		map2.put("prodname", "ZARA COAT 3");
//
//		HashMap<String, String> map3 = new HashMap<String, String>();
//		map3.put("uid", "anshika@gmail.com");
//		map3.put("pwd", "Iamking@000");
//		map3.put("prodname", "IPHONE 13 PRO");
//
//		
//		return new Object[][] {{map1},{map2},{map3}};
//	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		String fstr = System.getProperty("user.dir") + "\\src\\test\\java\\jayslabs\\test\\data\\PurchaseOrder.json";

		List<HashMap<String, String>> datamap = getJsonDataToMap(fstr);
		return datamap.stream().map(s-> new Object[] {s}).toArray(Object[][]::new);
	}
	
}
