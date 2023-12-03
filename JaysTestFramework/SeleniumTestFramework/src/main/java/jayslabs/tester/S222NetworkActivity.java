package jayslabs.tester;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.fetch.Fetch;
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

		dtools.send(Fetch.enable(Optional.empty(), Optional.empty()));
		dtools.addListener(Fetch.requestPaused(), request -> {
			Request rq = request.getRequest();
			if (rq.getUrl().contains("shetty")) {
				String nurl = rq.getUrl().replace("=shetty", "=BadGuy");
				System.out.println("new url: " + nurl);
				dtools.send(
						Fetch.continueRequest(request.getRequestId(), Optional.of(nurl), 
								Optional.of(rq.getMethod()), 
								Optional.empty(), Optional.empty(), Optional.empty())		
				);
			} else {
				dtools.send(
						Fetch.continueRequest(request.getRequestId(), Optional.of(rq.getUrl()), 
								Optional.of(rq.getMethod()), 
								Optional.empty(), Optional.empty(), Optional.empty())		
				);
				
			}
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.linkText("Library")).click();
		
		
		//mock network response - use Fetch cdp domain
	}
	


}
