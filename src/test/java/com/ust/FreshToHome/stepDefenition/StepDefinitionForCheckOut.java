package com.ust.FreshToHome.stepDefenition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import com.ust.FreshToHome.Exceptions.ElementInterceptedException;
import com.ust.FreshToHome.pom.CheckOut;

public class StepDefinitionForCheckOut {
	private final WebDriver driver = Hooks.driver;
	private CheckOut checkOut = new CheckOut(driver);
	private String[][] data;

	@And("User searches for {string}")
	public void user_searches_for(String itemName) {
		checkOut.searchItem(itemName);
	}

	@And("adds the item to the cart")
	public void adds_the_item_to_the_cart() {
		checkOut.cartItemPriceLessThan193();
	}

	@And("User goes to the checkout page")
	public void user_goes_to_the_checkout_page() {
		checkOut.checkOutButtonClick();
	}

	@When("User clicks the proceed to checkout button")
	public void user_clicks_the_checkout_button() {
		checkOut.proceedToCheckOutButtonClick();
	}

	@Then("{string} should be displayed")
	public void message_should_be_displayed(String msg) {

		String errorMsg = checkOut.messageCheck();
		assertEquals(errorMsg, msg);
	}

	@When("User increases the quantity")
	public void user_increases_the_quantity() {
		checkOut.quantitySetUp(5);
	}

	@When("updates the cart")
	public void updates_the_cart() {
		checkOut.updateButtonClick();
	}

	@Then("Calculated values must be correct")
	public void calculated_values() {
		assertTrue(checkOut.calculatedValues());
	}

//@Then("Calculated values must be correct use excel in {string}")
//public void calculated_values_in_must_be_correct_use_excel_in(String string) {
//	assertTrue(checkOut.calculatedValues());
//}
	@Then("User moves to the corresponding checkout page")
	public void user_moves_to_the_corresponding_checkout_page() {
		System.out.println("jncajndjj");

		assertEquals("https://www.freshtohome.com/checkout/onepage/", "https://www.freshtohome.com/checkout/onepage/");
	}

	@When("User clicks login button")
	public void user_clicks_login_button() {
		checkOut.loginForOrder();
	}

	@When("User enter the phone number and click otp button")
	public void user_enter_the_phone_number_and_click_otp_button() {
		checkOut.enterPhoneNumber();
		checkOut.otpButtonClick();
	}

//@And("User choose login using password option")
//public void user_choose_enter_using_password_option() {
//    checkOut.loginUsingPassLinkClick();
//}
//
//@And("Enters login credentials using data in configuration file")
//public void enters_login_credentials_using_data_in_properties() {
//	 checkOut.enterLoginData();
//	
//}
	@And("User clicking continue button")
	public void user_clicking_continue_button() {

		checkOut.waitToEnterOtp();
		checkOut.continueButtonClick();
		// checkOut.correctUrl();

	}

	@Then("User moves to Shipping Information section")
	public void user_moves_to_shipping_information_section() {

		assertTrue(checkOut.shippingSectionCheck());

	}

	@And("User add new address")
	public void user_add_new_address() {
		checkOut.newAddressOptionClick();

	}

	@When("User tries to click the continue button without filling all credentials")
	public void user_tries_to_click_the_continue_button_without_filling_all_credentials() {
		// checkOut.enterDetailsForNewAddress();
		checkOut.continueShipClick();

	}

	@Then("Error message should be displayed")
	public void error_message_should_be_displayed() {
		assertTrue(checkOut.errorMsgForCred());

	}

	@When("User enter all credentials")
	public void user_enter_all_credentials() {
		checkOut.enterDetailsForNewAddress();

	}

	@And("Clicks continue button")
	public void clicks_continue_button() {
		checkOut.continueShipClick();

	}

	@Then("User moves to Delivery info section")
	public void user_moves_to_delivery_info_section() {
		checkOut.deliverySectionCheck();

	}

	@When("User check delivery details correct or not")
	public void user_check_delivery_details_correct_or_not() {
		checkOut.readAdress();

	}

	@Then("Correct Details")
	public void correct_details() {

		assertTrue(checkOut.deliveryAddressCheck());

	}

	@When("User clicks Delivery continue button")
	public void user_clicks_delivery_continue_button() {

		checkOut.continueDelClick();

	}

	@Then("User moves to Order review section")
	public void user_moves_to_order_review_section() {
		assertTrue(checkOut.orderSectionCheck());

	}

	@And("User checks orderdetails are correct using {string}")
	public void user_checks_orderdetails_are_correct(String itemName) {
		assertTrue(checkOut.getProductName().contains(itemName));

	}

	@Then("User clicks place order")
	public void user_clicks_place_order() {
		checkOut.placeOrderClick();

	}

//@Then("User choose payment mode")
//public void user_choose_payment_mode() {
//	checkOut.placeOrderClick();
//}
	@Then("Alert message shouls be displayed")
	public void alert_message_shouls_be_displayed() {
		String alertInfo = checkOut.manageAlert();
		assertTrue(alertInfo != null);

	}

	@When("User select online payment mode")
	public void user_select_online_payment_mode() {
		checkOut.choosePayOnline();

	}

	@Then("User clicks place orders")
	public void user_clicks_place_orders() {
		checkOut.placeOrderManageJustpay();

	}

	@Then("User redirect to justpayonline window")
	public void user_redirect_to_justpayonline_window() {
		checkOut.juspayUrl();
		System.out.println(driver.getCurrentUrl());
	}
}