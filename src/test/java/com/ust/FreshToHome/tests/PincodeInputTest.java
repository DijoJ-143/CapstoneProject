package com.ust.FreshToHome.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ust.FreshToHome.base.BaseClass;

@Listeners(com.ust.FreshToHome.utilities.ExtentReportsListener.class)

// Test class for PincodeInput page
public class PincodeInputTest extends BaseClass {

	@Test(priority = 0)
	public void inputPincodeTest() {
		input = gotoShowHomePage();
		assertTrue(input.clickoninputPincode());
	}

	// Test to validate invalid pincodes
	@DataProvider(name = "invalidpincodes")
	public String[][] getInvalidpincodes() throws IOException {
		return function.readExcelFile("PATH_1", "Sheet1");
	}

	@Test(priority = 1, dataProvider = "invalidpincodes")
	public void inputPincodesTest(String pincodes) {
		function.delaySeconds(2);
		input.setPincode(pincodes);
		assertTrue(input.isErrorMesageDisplayed());
	}

}
