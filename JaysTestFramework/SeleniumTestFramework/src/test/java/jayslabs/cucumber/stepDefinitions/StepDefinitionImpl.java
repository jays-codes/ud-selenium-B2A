package jayslabs.cucumber.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jayslabs.pageobjects.CheckoutPage;
import jayslabs.pageobjects.LandingPage;
import jayslabs.pageobjects.OrderConfirmedPage;
import jayslabs.pageobjects.ProdCatalog;
import jayslabs.test.components.BaseTest;
import jayslabs.utils.CartPage;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProdCatalog prodcatalog;
	public OrderConfirmedPage ocp;
	public List<String> orders;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with user (.+) and password (.+)$")
	public void logged_in_with_user_password (String uid, String pwd){
		prodcatalog = landingPage.loginApplication(uid, pwd);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String prodname) throws InterruptedException {
		prodcatalog.addProductToCart(prodname);

	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String prodname) {
		CartPage cp = prodcatalog.openCart();
		CheckoutPage cop = cp.checkout();
		cop.selectCountry("ph");
		ocp = cop.placeOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationpage(String str) {
		boolean confirmed = ocp.isConfirmedPageShowing(str);
		orders = ocp.getOrderIds();
		Assert.assertTrue(confirmed);
		driver.quit();
	}
	
    //Then "Incorrect email or password." message is displayed
    @Then("{string} message is displayed")
    public void incorrect_email_or_password_message_is_displayed(String str){
    	String expectederr = "Incorrect email or password.";
		String errmsg = landingPage.getErrorMsg();
		Assert.assertTrue(expectederr.equalsIgnoreCase(errmsg));
		driver.quit();
    }
    
	
	
}
