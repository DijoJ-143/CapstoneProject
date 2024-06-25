package com.ust.FreshToHome.runner;


import org.testng.ITestListener;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		glue="com.ust.FreshToHome.stepDefenition",
				features = {"classpath:resources/features/checkOut.feature"},
				tags =  "@checkOut_Functionality"	
				,
				plugin= { "pretty",
			    "html:target/html-reports/checkout.html"},
				monochrome=true
		)
public class TestRunner1 extends AbstractTestNGCucumberTests {
	
}
