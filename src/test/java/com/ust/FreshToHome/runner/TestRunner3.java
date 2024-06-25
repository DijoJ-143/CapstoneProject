package com.ust.FreshToHome.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    glue="com.ust.FreshToHome.stepDefenition",
    		features = {"classpath:resources/features/cart.feature"},    
    		tags = "@cart_functionality",
    		plugin= { "pretty",
    "html:target/html-reports/cart.html"},
	
	monochrome = true
   
)
public class TestRunner3 extends AbstractTestNGCucumberTests {
}
