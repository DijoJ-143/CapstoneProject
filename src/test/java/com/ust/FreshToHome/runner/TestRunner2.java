package com.ust.FreshToHome.runner;

import org.testng.ITestListener;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		glue="com.ust.FreshToHome.stepDefenition",
				features = {"classpath:resources/features/productCategories.feature"},				
				tags =  "@product_categories"	,
			   plugin= { "pretty",
			    "html:reports/cucumber-reports/productcategories.html"}
		)
public class TestRunner2 extends AbstractTestNGCucumberTests {
	
}
