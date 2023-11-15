package jayslabs.test.components;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import jayslabs.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();

		String fstr = System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalData.properties";
		FileInputStream fis = new FileInputStream(fstr);

		prop.load(fis);
		// browsername = prop.getProperty("browser");

		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("chrome")) {
			ChromeOptions opts = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver(opts);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}

		else if (browsername.contains("headless")) {
			ChromeOptions opts = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			System.out.println("Running in Headless mode");
			opts.addArguments("--headless=new");
			driver = new ChromeDriver(opts);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}

		else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		// driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonstr = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// using jackson databind to convert json str to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonstr,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String takeScreenshot(String tcname, WebDriver driver2) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver2;
		File src = ts.getScreenshotAs(OutputType.FILE);

		String fname = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fstr = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + tcname + fname + ".png";

		File dest = new File(fstr);
		FileUtils.copyFile(src, dest);
		return fstr;
	}
}
