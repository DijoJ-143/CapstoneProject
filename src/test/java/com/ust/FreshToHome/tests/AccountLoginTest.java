package com.ust.FreshToHome.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ust.FreshToHome.base.BaseClass;
import com.ust.FreshToHome.base.ReusableFunction;
import com.ust.FreshToHome.pom.AccountLoginPom;

@Listeners(com.ust.FreshToHome.utilities.ExtentReportsListener.class)

public class AccountLoginTest extends BaseClass {

	@Test(priority = 0)
	public void validateCurrentUrl() {

		ap = lp.passDriverToNextTest();

		assertTrue(function.getCurrentUrl().contains("https://www.freshtohome.com/buy-fish-meat-online/products/"));

	}

	// Data provider method to provide input data for login
	@DataProvider(name = "inputDataForLogin")
	public String[][] inputDataForLogin() {
		String[][] data = function.readExcelFile("PATH_1", "Sheet2");
		return data;
	}

	// Test to login with a registered mobile number
	@Test(priority = 1, dataProvider = "inputDataForLogin")
	public void loginWithRegisteredMobileNumberTest(String phoneNumber) {

		ap.loginWithRegisteredMobileNumber(phoneNumber);
	}

	// Test to enter wrong OTP
	@Test(dependsOnMethods = "loginWithRegisteredMobileNumberTest")
	public void enteringWrongtOtp() {

		// Verify that the error message for wrong OTP is displayed
		assertEquals(ap.enteringWrongtOtp(), "OTP verification failed. Please try again.", "OTPErrorMessageAnalysing");
	}

	// Test to enter correct OTP
	@Test(dependsOnMethods = "enteringWrongtOtp")
	public void enteringCorrectOtpTest() {

		// Verify that the URL is as expected after entering correct OTP
		assertTrue(ap.enteringCorrectOtp());

		ap.navigateToRegistrationPage();

	}

	@DataProvider(name = "inputDataForRegistration")
	public String[][] inputDataForRegistration() {
		String[][] data = function.readExcelFile("PATH_1", "Sheet5");
		return data;
	}

	@Test(priority = 2, dataProvider = "inputDataForRegistration")
	public void inputDataForRegistration(String... data) {

		assertTrue(ap.datasForLogin(data[0], data[1]));

	}

	@Test(priority = 3)
	public void navigateToOtpLogin() {
		ap.otpLogin();

	}

//	
//	
//	@DataProvider(name = "inputDataForRegistration")
//	public String[][] inputDataForRegistration() {
//		String[][] data = function.readExcelFile("PATH_1", "Sheet3");
//		return data;
//	}
//	
//		
//		@Test(priority = 2, dataProvider = "inputDataForRegistration")
//		public void inputDataForRegistration(String... data) {
//			ap.enterDetailsForLogin(data[0], data[1], data[2]);
//			ap.clickOnRegister();
//			
//		}

}
