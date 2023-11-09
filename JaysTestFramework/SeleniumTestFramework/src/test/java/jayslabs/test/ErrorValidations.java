package jayslabs.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import jayslabs.test.components.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test
	public void validateLoginError() throws InterruptedException {

		String expectederr = "Incorrect email or password.";
		
		landingPage.loginApplication("anshika@gmail.com", "Iamking@003");
		String errmsg = landingPage.getErrorMsg();
		
		System.out.println(errmsg);
		
		Assert.assertTrue(expectederr.equalsIgnoreCase(errmsg));
	}

}
