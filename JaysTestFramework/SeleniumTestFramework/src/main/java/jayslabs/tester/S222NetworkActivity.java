package jayslabs.tester;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

public class S222NetworkActivity {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		DevTools dtools = driver.getDevTools();

		dtools.createSession();

		dtools.send(Network.enable(
				Optional.empty(), Optional.empty(), Optional.empty()));
		
		dtools.addListener(Network.requestWillBeSent(), request -> {
			Request rq = request.getRequest();
			System.out.println("rq url: " + rq.getUrl());
//			System.out.println("status: "+ rq.getHeaders().toString()));

		});
		
		dtools.addListener(Network.responseReceived(), 
				response -> {
					Response res = response.getResponse();
					System.out.println("rs url: " + res.getUrl() 
					+ "|" + res.getStatus());
					
					if (res.getStatus().toString().startsWith("4")) {
						System.out.println("rs url: " + res.getUrl() 
						+ " is failing with status code: " + res.getStatus());
						
					}
				}
		);
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.linkText("Library")).click();
		

//		Thread.sleep(1000);
//		driver.findElements(By.cssSelector("h3.LC20lb")).get(0).click();
//		
//		String titleString = driver.findElement(By.tagName("h1")).getText();
//		System.out.println("page text:" + titleString);
	}
	


}
