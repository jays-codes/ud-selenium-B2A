package jayslabs.tester;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.emulation.Emulation;

import com.google.common.collect.ImmutableMap;

public class S221Localization {

//	public static void main(String[] args) throws InterruptedException {		
//		// Set up ChromeOptions to include geolocation data and set the language to Spanish        
//		ChromeOptions options = new ChromeOptions();        
//		options.addArguments("--enable-features=Geolocation");        
//		options.addArguments("--lang=es"); 
//		// Set the browser language to Spanish
//		 
//		// Launch Chrome browser with the defined options       
//		ChromeDriver driver = new ChromeDriver(options);
//		 // Set the geolocation using CDP        
//		double latitude = 40;        
//		double longitude = 3;        
//		double accuracy = 1;        
//		DevTools devTools = ((ChromeDriver) driver).getDevTools();        
//		devTools.createSession();        
//		devTools.send(Emulation.setGeolocationOverride(Optional.of(latitude), Optional.of(longitude), Optional.of(accuracy)));
//		// Perform a Google search for "Netflix is open Spanish language"        
//		driver.get("https://www.google.com");        
//		WebElement searchBox = driver.findElement(By.name("q"));        
//		searchBox.sendKeys("Netflix");        
//		searchBox.submit();
//		// Wait for the results to load and perform other actions as needed       
//		Thread.sleep(5000);        
//		// Close the browser       
//		 //driver.quit();    
//		driver.findElements(By.cssSelector("h3.LC20lb")).get(0).click();
//		
//		String titleString = driver.findElement(By.tagName("h1")).getText();
//		System.out.println("page text:" + titleString);
//
//	}	

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();        
		options.addArguments("--enable-features=Geolocation");        
		options.addArguments("--lang=ja"); 
		
		ChromeDriver driver = new ChromeDriver(options);
		DevTools dtools = driver.getDevTools();

		dtools.createSession();

//		ImmutableMap.Builder<String, Object> params = ImmutableMap.builder();
//		params.put("latitude", 40);
//		params.put("longitude", 3);
//		params.put("accuracy", 1);
//		driver.executeCdpCommand("Emulation.setGeolocationOverride", params.build());
//
	
		String search = "netflix";

		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys(search, Keys.ENTER);

//		Thread.sleep(1000);
//		driver.findElements(By.cssSelector("h3.LC20lb")).get(0).click();
//		
//		String titleString = driver.findElement(By.tagName("h1")).getText();
//		System.out.println("page text:" + titleString);
	}
	
	
}
