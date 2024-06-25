package com.ust.FreshToHome.stepDefenition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.ust.FreshToHome.pom.Cart;
import com.ust.FreshToHome.pom.ProductCategories;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionForCart {
    private final WebDriver driver = Hooks.driver;
    private Cart cart = new Cart(driver);

    @When("User clicks on the cart icon")
    public void user_clicks_on_the_cart_icon() {
        cart.cartIconCheck();
    }

    @Then("Message block should be displayed")
    public void message_block_should_be_displayed() {
        assertTrue(cart.cartMessage().isDisplayed());
    }

    @And("User has added items to the cart")
    public void user_has_added_items_to_the_cart() {
        cart.selectFirstProduct();
        cart.cartProduct();
    }

    @Then("Corresponding item details should be displayed")
    public void corresponding_item_details_should_be_displayed() {
        String productName = cart.cartProduct();
        String productDetails = cart.cartProductDetails();
        assertEquals(productDetails, productName);
    }

    @When("User clicks on My Cart button")
    public void user_clicks_on_my_cart_button() {
        cart.clickMyCartButton();
    }

    @Then("User is redirected to the cart page successfully")
    public void user_is_redirected_to_the_cart_page_successfully() {
        String currentUrl = cart.getCurrentUrl();
        assertTrue(currentUrl.contains("cart"));
    }

    @When("User updates the quantity to {int}")
    public void user_updates_the_quantity_to(int qty) {
        cart.quantitySetUp(qty);
    }

    @And("clicks the update button")
    public void clicks_the_update_button() {
        cart.updateButtonClick();
    }

    @Then("Cart is updated successfully with a quantity of {int}")
    public void cart_is_updated_successfully_with_a_quantity_of(int qty) {
        assertTrue(cart.subTotalCheck(qty));
    }

    @And("User clicks on the clear button")
    public void user_clicks_on_the_clear_button() {
        cart.clearButtonClick();
    }

    @Then("Empty message should be displayed")
    public void empty_message_should_be_displayed() {
        assertTrue(cart.clearButtonCheck().isDisplayed());
    }
}
