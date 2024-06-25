package com.ust.FreshToHome.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ust.FreshToHome.base.BaseClass;

@Listeners(com.ust.FreshToHome.utilities.ExtentReportsListener.class)

public class AccountDashBoardTest extends BaseClass {

	@Test(priority = 0)
	public void valiidatingWhetherinAccountDashBoard() {

		adp = ap.passDriverToNextTest();
		assertEquals(function.getCurrentUrl(),"https://www.freshtohome.com/customer/account/");
		function.delaySeconds(5);

	}

	@DataProvider(name = "accName")
	public String[][] getdata() {
		return function.readExcelFile("PATH_1", "Sheet4");
	}

	@Test(priority = 1, dataProvider = "accName")
	public void validateUserName(String name) {
		assertTrue(adp.validateUserName(name));

	}


}
