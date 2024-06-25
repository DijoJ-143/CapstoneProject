package com.ust.FreshToHome.stepDefenition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.ust.FreshToHome.pom.AccountDashboardPom;
import com.ust.FreshToHome.pom.AccountInformationPom;
import com.ust.FreshToHome.pom.AccountLoginPom;
import com.ust.FreshToHome.pom.AddressBookPom;
import com.ust.FreshToHome.pom.PincodeInput;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepdefenitionForAddressBook {
	private final WebDriver driver = Hooks.driver;
	PincodeInput pi = new PincodeInput(driver);
	AccountLoginPom login = new AccountLoginPom(driver);
	AccountDashboardPom dashboard = new AccountDashboardPom(driver);
	AccountInformationPom information = new AccountInformationPom(driver);
	AddressBookPom addressBook = new AddressBookPom(driver);
	static String name;

	@Given("User eneters pincode credentials")
	public void user_eneters_pincode_credentials() {
		assertEquals(driver.getCurrentUrl(), pi.getUrl());
		pi.setPincode("691506");
	}

	@And("User clicks on the My Account icon")
	public void user_clicks_on_the_my_account_icon() {
		assertTrue(login.clickOnLogin());
	}

	@And("User enters login credentials")
	public void user_enters_login_credentials() {
		login.otplogin();
	}

	@When("User enters the Account Dashboard")
	public void user_enters_the_account_dashboard() {
		login.clickOnLogin();
		
	}

	@Then("User validates account information")
	public void user_validates_account_information() {
		name = dashboard.getAccountUserName();
		dashboard.clickOnAccountInformation();
		assertTrue(information.validateAccountInformations().contains(name));
	}

	@And("User clicks on Address Book")
	public void user_clicks_on_address_book() {
		information.clickonAddressBook();
	}

	@When("User clicks on Add New Address Link")
	public void user_clicks_on_add_new_address_link() {
		addressBook.clickOnAddNewAddress();
	}

	@Then("User is redirected to the Add New Address section successfully")
	public void user_is_redirected_to_the_add_new_address_section_successfully() {
		assertEquals(driver.getCurrentUrl(), addressBook.getUrl());
	}

	@When("User enters all necessary credentials in address")
	public void user_enters_all_necessary_credentials_in_address() {
	
		addressBook.enterNewAddress();
	}


	@Then("The address is saved successfully")
	public void the_address_is_saved_successfully() {
		assertTrue(addressBook.getSuccessMessage().equals("The address has been saved."));
	}


	@When("user clicks delete address in address book")
	public void user_clicks_delete_address_in_address_book() {
		
		System.out.println("delete");
		addressBook.deleteAddressClick();
		addressBook.deleteAddressAlertClick();
	}

	@Then("the address is deleted successfully from address book")
	public void the_address_is_deleted_successfully_from_address_book() {
	
		assertTrue(addressBook.getSuccessMessageOfDelete().equals("The address has been deleted."));
	}
	  @When("User input {string} as firstname {string} as telephone {string} as street_Address {string} as postal_code {string} as Locality")
	    public void user_input_as_firstname_as_telephone_as_street_Address_as_postal_code_as_Locality_as_city(String firstname, String telephone, String address, String postalcode, String locality) {
	        addressBook.clickOnAddNewAddress();
	        addressBook.enterNewAddressFromDataTable(firstname, telephone, address, postalcode);
	    }
	  @And("clicks save")
	    public void clicks_save() {
	       addressBook.clickOnSaveAddress();
	    }


	    @Then("Error message is displayed {string}")
	    public void error_message_is_displayed(String errorMsg) {
	        assertTrue(addressBook.getError().contains(errorMsg));
	    }
}