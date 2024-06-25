@AddressBook_functionality
Feature: Add New Address Functionality

  Scenario: Adding a new address to the account
    Given User is on the home page
    And User enters pincode credentials
    And User clicks on the My Account icon
    And User enters login credentials
    When User enters the Account Dashboard
    Then User validates account information
    And User clicks on Address Book
    When User clicks on Add New Address Link
    Then User is redirected to the Add New Address section successfully
    When User enters all necessary credentials in address
    Then The address is saved successfully
    When user clicks delete address in address book
    Then the address is deleted successfully from address book

  @negative
  Scenario Outline: Checking add new address functionality
    Given User is on the home page
    And User enters pincode credentials
    And User clicks on the My Account icon
    And User enters login credentials
    When User enters the Account Dashboard
    Then User validates account information
    And User clicks on Address Book
    When User clicks on Add New Address Link
    Then User is redirected to the Add New Address section successfully
    When User input "<firstname>" as firstname "<telephone>" as telephone "<address>" as street_Address "<postalcode>" as postal_code
    And clicks save
    Then Error message is displayed "<errorMsg>"

    Examples: 
      | firstname | telephone  | address  | postalcode | errorMsg                             |
      |           |            |          |            | Presently enter a valid postal code. |
      | dijo      |            |          |            | Presently enter a valid postal code. |
      | dijo      | 7736305205 |          |            | Presently enter a valid postal code. |
      | dijo      | 7736305205 | humbi123 |            | Presently enter a valid postal code. |
