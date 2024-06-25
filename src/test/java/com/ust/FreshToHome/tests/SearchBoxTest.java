package com.ust.FreshToHome.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ust.FreshToHome.base.BaseClass;
import com.ust.FreshToHome.base.ReusableFunction;
import com.ust.FreshToHome.pom.LinksPage;
import com.ust.FreshToHome.pom.searchBoxPom;

@Listeners(com.ust.FreshToHome.utilities.ExtentReportsListener.class)

// Test class for search functionality
public class SearchBoxTest extends BaseClass {
//    public static WebDriver driver;
//    ReusableFunction rf;
//    searchBoxPom sp;

	// Initialize searchBoxPom object with shared driver before running the tests
//    @BeforeClass
//    public void setUp() {
//        sp = new searchBoxPom(PincodeInputTest.sharedDriver);
//    }

	// Method to pass the WebDriver from the previous test
//    public static void getDriver(WebDriver driver) {
//        SearchBoxTest.driver = driver;
//    }

	@Test(priority = 0)
	public void inputuserEntryTest() {
		assertTrue(input.isDropDownDisplayed());
		function.delaySeconds(2);
		input.setPincode("691506");
		assertFalse(input.isErrorMesageDisplayed());
		sp = input.passDriverToNextTest();

	}

	// Test to search with null value
	@Test(priority = 1)
	public void searchWithNullValueTest() {

		sp.searchWithNullValue();
		function.delaySeconds(2);
		// Verify that the URL is as expected after searching with null value
		assertEquals(driver.getCurrentUrl(), "https://www.freshtohome.com/catalog-elastic/search/?q=+");
	}

	// Test to search with wrong value
	@Test(priority = 2)
	public void searchWithWrongValueTest() {
		WebElement searchResultMsg = sp.searchWithWrongValue();
		// Verify that search result message is displayed
		assertTrue(searchResultMsg.isDisplayed());
	}

	// Test to search with a valid value
	@Test(priority = 3)
	public void searchWithValueTest() {
		String searchResult = sp.searchWithValue();
		// Verify that the URL is as expected after searching with a valid value
		assertEquals(driver.getCurrentUrl(), "https://www.freshtohome.com/catalog-elastic/search/?q=Chicken");
		// Verify that the search result contains the searched value
		assertTrue(searchResult.contains("Chicken"));
	}

}
