package com.ust.FreshToHome.tests;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ust.FreshToHome.Exceptions.ElementInterceptedException;
import com.ust.FreshToHome.base.BaseClass;

@Listeners(com.ust.FreshToHome.utilities.ExtentReportsListener.class)

public class LinkTest extends BaseClass {

	@Test(priority = 0)
	public void productsLinkClickTest() {
		lp = sp.passDriverToNextTest();
		try {
			assertTrue(lp.productsLinkClick());
		} catch (ElementInterceptedException e) {
			// Assertion failed
			fail("Element not interactable: " + e.getMessage());
		}
	}

	@Test(priority = 1)
	public void placesLinkClickTest() {
		lp = sp.passDriverToNextTest();
		assertTrue(lp.placesLinkClick());
	}

}