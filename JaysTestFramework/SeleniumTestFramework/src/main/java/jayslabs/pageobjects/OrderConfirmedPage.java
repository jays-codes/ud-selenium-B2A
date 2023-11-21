package jayslabs.pageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class OrderConfirmedPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(css = ".hero-primary")
	private WebElement confirmedtxt;

	@FindBy(css = "label[class='ng-star-inserted']")
	private List<WebElement> orderIds;

	public OrderConfirmedPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isConfirmedPageShowing(String expected) {
		String confirmed = confirmedtxt.getText();
		//System.out.println("text: " + confirmed);
		return expected.equalsIgnoreCase(confirmed);
	}

	public List<String> getOrderIds() {

		List<String> ids = orderIds.stream().map(s -> s.getText()).collect(Collectors.toList());

		List<String> corrected = new ArrayList<String>();
		ids.forEach(idstr -> {
			String correct = "";

			correct = removeLeadingBar(idstr);
			correct = removeTrailingBar(correct);
			corrected.add(correct.trim());
		});

		return corrected;
	}

	String removeLeadingBar(String s) {
		int index;
		for (index = 0; index < s.length(); index++) {
			if (s.charAt(index) != '|') {
				break;
			}
		}
		return s.substring(index);
	}

	String removeTrailingBar(String s) {
		int index;
		for (index = s.length() - 1; index >= 0; index--) {
			if (s.charAt(index) != '|') {
				break;
			}
		}
		return s.substring(0, index + 1);
	}
}
