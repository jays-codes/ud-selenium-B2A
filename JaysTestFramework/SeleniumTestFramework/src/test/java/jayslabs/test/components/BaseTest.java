package jayslabs.test.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import jayslabs.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() {
		Properties prop = new Properties();
		try {
			
			String fstr = System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalData.properties";
			FileInputStream fis = new FileInputStream(fstr);

			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browsername = prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	public LandingPage launchApplication() {
		driver = initializeDriver();
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

}
