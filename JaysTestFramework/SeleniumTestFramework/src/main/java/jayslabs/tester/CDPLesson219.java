package jayslabs.tester;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import com.google.common.collect.ImmutableMap;

public class CDPLesson219 {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools dtools = driver.getDevTools();
		
		dtools.createSession();
		
		//below used to emulate phone browsers
//		dtools.send(
//			Emulation.setDeviceMetricsOverride(
//				600, 1000, 50, true, 
//				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), 
//				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()
//			)
//		);
		
		//directly calling cdp command
		ImmutableMap.Builder<String, Object> params = ImmutableMap.builder();
		params.put("width", 600);
		params.put("height", 1000);
		params.put("deviceScaleFactor", 50);
		params.put("mobile", true);
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", params.build());
		
		driver.get("https://rahulshettyacademy.com/angularpractice/");
	}
	

}
