
@tag
Feature: Purchase the order from ecommerce website

	Background: 
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with user <uid> and password <pwd>
    When I add product <prodname> to Cart
    And Checkout <prodname> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | uid  									| pwd 				| prodname  			|
      | anshika@gmail.com 		| Iamking@000 | ADIDAS ORIGINAL |
	    | anshika@gmail.com 		| Iamking@000 | ZARA COAT 3 		|
      