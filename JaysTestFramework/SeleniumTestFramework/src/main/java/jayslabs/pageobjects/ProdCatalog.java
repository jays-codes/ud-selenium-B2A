package jayslabs.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import jayslabs.utils.AbstractComponent;

public class ProdCatalog extends AbstractComponent {

	WebDriver driver;
	
	public ProdCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	private List<WebElement> products;

	@FindBy(css = ".ng-animating")
	private WebElement animation;

	private By productsBy = By.cssSelector(".mb-3");
	private By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	private By toastBy = By.id("toast-container");

	public List<WebElement> getProductList() {
		return products;
	}

	public WebElement getProductByName(String pname) {
		WebElement prod = products.stream().filter(s -> s.findElement(By.tagName("b")).getText().equals(pname))
				.findFirst().orElse(null);

		return prod;
	}

	public void addProductToCart(String pname) throws InterruptedException {
		// waitForElementToAppear(productsBy);
		getProductByName(pname).findElement(addToCartBy).click();
		waitForElementToAppear(toastBy);
		this.waitForElementToDisappear(animation);
	}

}
