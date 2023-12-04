# ud-selenium-B2A
contains multiple projects done during WebDriver Basic to Adv course (by ud-R.Shetty)

For recruiters, pls. refer to the project folder: JaysTestFramework/SeleniumTestFramework for working sample project covering the topics 
- Selenium API
- TestNG API
- Cucumber API, StepDefinition integration with Existing Page Objects, Feature files written in Gherkin
- Parallel Execution, Headless Execution
- DataProvider integration with JSON data (using Jackson API)
- DataProvider integration with xls data (using Apache POI)
- integrated automated test retry (TestNG)
- Integration with Selenium Grid - Setting up Hub, Nodes, using RemoteWebDriver
- TestSuite execution via Maven profiles, Jenkins
- DataProvider integration via JDBC querying from mysql
- ChromeDevtoolsProtocol
  - simulating browser in mobile (Emulation)
  - Localization Testing (via Emulation.setGeolocationOverride and ChromeOptions '--lang')
  - logging/tracking Network request, and resp codes via listeners (Network)
  - intercept network req and modify it as per test scenario
  - use an urlpattern and explicitly fail request via Fetch.failRequest
  - blocking unwanted network calls, speeding up execution
  - simulated network conditions, connection types
- used selenium HasAuthentication and UsernameAndPaswword to perform basic auth
- integrated selenium logging (LogEntries, etc.) to testNG listener
