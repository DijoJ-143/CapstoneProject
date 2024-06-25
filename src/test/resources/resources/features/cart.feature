@cart_functionality
Feature: Cart Functionality

  Scenario: Clicking on the empty cart icon
    Given User is on the home page
    And User enters pincode credentials
    When User clicks on the cart icon
    Then Message block should be displayed

  Scenario: Clicking on cart with items and checking if the item is added correctly
    Given User is on the home page
    And User enters pincode credentials
    And User has added items to the cart
    When User clicks on the cart icon
    Then Corresponding item details should be displayed

  Scenario Outline: Checking update, clear, continue cart buttons
    Given User is on the home page
    And User enters pincode credentials
    And User has added items to the cart
    And User clicks on the cart icon
    And User clicks on My Cart button
    When User updates the quantity to <Value>
    And clicks the update button
    Then Cart is updated successfully with a quantity of <Value>
    When User clicks on the clear button
    Then Empty message should be displayed

    Examples: 
      | Value |
      |     2 |
