package jayslabs.utils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	
	
	@FindBy(css="tbody tr th")
	private List<WebElement> orderids;

	@FindBy(css="tbody tr")
	private List<WebElement> orders;

	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyOrderIsDisplayed(List<String> curorder) {
		List<String> ids = orderids.stream()
				.map(s->s.getText()).toList();
		
		return ids.containsAll(curorder);
		
	}

}
