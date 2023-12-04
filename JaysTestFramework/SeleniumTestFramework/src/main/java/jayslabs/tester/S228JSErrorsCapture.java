package jayslabs.tester;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class S228JSErrorsCapture {


	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.linkText("Browse Products")).click();
		driver.findElement(By.linkText("Selenium")).click();
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		driver.findElement(By.linkText("Cart")).click();
		driver.findElement(By.id("exampleInputEmail1")).clear();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
		
		//line below should be implemented in Listeners
		LogEntries entries = driver.manage().logs().get(LogType.BROWSER);
		List<LogEntry> logs = entries.getAll();
		
		//below line should instead be sent to log4j
		logs.stream().forEach(log -> System.out.println(log.getMessage()));
	}

}
