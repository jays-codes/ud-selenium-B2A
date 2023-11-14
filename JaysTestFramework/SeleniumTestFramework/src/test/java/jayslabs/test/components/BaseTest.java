package jayslabs.test.components;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import jayslabs.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;
	public ExtentReports extent = new ExtentReports();

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

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() {
		driver = initializeDriver();
		LandingPage lp = new LandingPage(driver);
		lp.goTo();
		this.landingPage = lp;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		//.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonstr = FileUtils
				.readFileToString(
						new File(filePath), StandardCharsets.UTF_8);
		
		//using jackson databind to convert json str to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data 
			= mapper.readValue(
					jsonstr, 
					new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	
	public String takeScreenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		String fname = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
		String fstr = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + fname + ".png";

		
		File dest = new File(fstr);
		FileUtils.copyFile(src, dest);
		return fstr;
	}
	
	@BeforeTest(alwaysRun = true)
	public void configExtentReport() {
		System.out.println("configuring extent reports...");
		String fstr = System.getProperty("user.dir") + "\\test-output\\extentreports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(fstr);
		
		reporter.config().setReportName("Submit Order Test Results");
		reporter.config().setDocumentTitle("Test Results");
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jay M");
	}
	
	public String getCurrentMethod() {
		
		String str = Thread.currentThread()
	      .getStackTrace()[1].getMethodName();
		
//		Optional<String> name = StackWalker.getInstance()
//				.walk(frames -> frames
//			      .findFirst()
//			      .map(StackWalker.StackFrame::getMethodName));
		return str;
	}

}
