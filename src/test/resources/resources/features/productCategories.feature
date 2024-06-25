@product_categories
Feature: Product Sorting Functionality

  Scenario: Verify product sort by functionality - Price: Low to High
    Given User is on the home page
    And User enters pincode credentials
    And User selects one product
    When User selects sorting option "Price - Low to High"
    Then Products should be displayed in ascending order of price

  Scenario: Verify product sort by functionality - Price: High to Low
    Given User is on the home page
    And User enters pincode credentials
    And User selects one product
    When User selects sorting option "Price - High to Low"
    Then Products should be displayed in descending order of price
