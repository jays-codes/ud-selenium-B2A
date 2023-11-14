package jayslabs.test.components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retrier implements IRetryAnalyzer {

	int count = 0;
	int maxtries = 2;
	
	@Override
	public boolean retry(ITestResult result) {

		if (count<maxtries) {
			count++;
			System.out.println("Retrying testcase: " + result.getTestName());
			return true;
		}
		return false;
	}

}
