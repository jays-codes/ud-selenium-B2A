package jayslabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class LandingPage extends AbstractComponent{

	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginError;
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;

	public ProdCatalog loginApplication(String user, String pwd) {
		userEmail.sendKeys(user);
		password.sendKeys(pwd);
		submit.click();
		return new ProdCatalog(driver);
		
	}

	public String getErrorMsg() {
		waitForElementToAppear(loginError);
		String errstr = loginError.getText();
		return errstr;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
