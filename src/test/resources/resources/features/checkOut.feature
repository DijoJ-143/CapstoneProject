@checkOut_Functionality
Feature: Checkout functionality

  Scenario Outline: Users buying an item
    Given User is on the home page
    And User enters pincode credentials
    And User searches for "<itemName>"
    And adds the item to the cart
    And User goes to the checkout page
    When User clicks the proceed to checkout button
    Then "Total Order amount should be minimum ₹193, exclusive of any membership purchases" should be displayed
    When User increases the quantity
    And updates the cart
    # here calculated values are stored in excel,which filepath is stored in configuration file
    Then Calculated values must be correct
    When User clicks the proceed to checkout button
    Then User moves to the corresponding checkout page
    When User clicks login button
    And User enter the phone number and click otp button
    And User clicking continue button
    Then User moves to Shipping Information section
    And User add new address
    When User tries to click the continue button without filling all credentials
    Then Error message should be displayed
    When User enter all credentials
    And Clicks continue button
    Then User moves to Delivery info section
    When User check delivery details correct or not
    Then Correct Details
    When User clicks Delivery continue button
    Then User moves to Order review section
    And User checks orderdetails are correct using "<itemName>"
    When User clicks place order
    Then Alert message shouls be displayed
    When User select online payment mode
    And User clicks place orders
    Then User redirect to justpayonline window
   

    Examples: 
      | itemName                          |
      | Mutton Nihari Ready-To-Cook Paste |
