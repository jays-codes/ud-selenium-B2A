package jayslabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class OrderConfirmedPage extends AbstractComponent {

	WebDriver driver;
	
	@FindBy(css=".hero-primary")
	WebElement confirmedtxt;
	
	public OrderConfirmedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isConfirmedPageShowing(String expected) {
		String confirmed = confirmedtxt.getText();
		System.out.println("text: " +confirmed);
		return expected.equalsIgnoreCase(confirmed);
	}

}
