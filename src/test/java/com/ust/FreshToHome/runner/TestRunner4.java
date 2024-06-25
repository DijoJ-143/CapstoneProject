package com.ust.FreshToHome.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "com.ust.FreshToHome.stepDefenition", features = {
		"classpath:resources/features/AdressBook.feature" }, tags = "@AddressBook_functionality", plugin = { "pretty",
				"html:target/html-reports/address.html" },

		monochrome = true

)
public class TestRunner4 extends AbstractTestNGCucumberTests {
}
