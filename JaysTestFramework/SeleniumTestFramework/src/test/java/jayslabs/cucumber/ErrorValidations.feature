
@tag
Feature: Validate Errors for ecommerce website

	Background: 
	Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: Negative test of Login with wrong user or password
		Given I landed on Ecommerce Page
    And Logged in with user <uid> and password <pwd>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | uid  									| pwd 				| 
      | anshika@gmail.com 		| Iamking@005 | 
	    | anshikB@gmail.com 		| Iamking@000 | 
