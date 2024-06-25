package com.ust.FreshToHome.stepDefenition;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.ust.FreshToHome.pom.ProductCategories;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionForProduct {
    private final WebDriver driver = Hooks.driver;
    private ProductCategories productPage = new ProductCategories(driver);

    @Given("User is on the home page")
    public void user_is_on_the_home_page() {
        assertEquals(driver.getCurrentUrl(), "https://www.freshtohome.com/");
    }

    @And("User enters pincode credentials")
    public void user_enters_pincode_credentials() {
    	productPage.pincodeSetup();
    }

    @And("User selects one product")
    public void user_selects_one_product() {
    	productPage.clickProductCategory();
    }

    @When("User selects sorting option {string}")
    public void user_selects_sorting_option(String sortingOption) {
       productPage.chooseSortPrice(sortingOption);
    }

    @Then("Products should be displayed in ascending order of price")
    public void products_should_be_displayed_in_ascending_order_of_price() {
       assertTrue(productPage.priceLowToHighCheck());
    }

    @Then("Products should be displayed in descending order of price")
    public void products_should_be_displayed_in_descending_order_of_price() {
    	 assertTrue(productPage.priceHighToLowCheck());
    }

}
