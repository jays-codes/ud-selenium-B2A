package jayslabs.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@CucumberOptions(features="src/test/java/jayslabs/cucumber", 
//glue="jayslabs.cucumber.stepDefinitions",
// monochrome=true, tags= "@Regression and @Error", plugin= {"html:target/cucumber.html"})

@CucumberOptions(features="src/test/java/jayslabs/cucumber", 
glue="jayslabs.cucumber.stepDefinitions",
 monochrome=true, plugin= {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
}
