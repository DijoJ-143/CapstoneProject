package com.ust.FreshToHome.base;

import java.time.Duration;

import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ust.FreshToHome.pom.AccountDashboardPom;
import com.ust.FreshToHome.pom.AccountLoginPom;
import com.ust.FreshToHome.pom.LinksPage;
import com.ust.FreshToHome.pom.PincodeInput;
import com.ust.FreshToHome.pom.searchBoxPom;

//This class sets up the WebDriver and loads the necessary configurations before running tests.
public class BaseClass {

	public WebDriver driver;
	public static final TestConfig testConfigProperties = ConfigCache.getOrCreate(TestConfig.class);
	public static PincodeInput input;
	public static ReusableFunction function;
	public static searchBoxPom sp;
	public static LinksPage lp;
	public static AccountLoginPom ap;
	public static AccountDashboardPom adp;

	// Setup WebDriver before running test cases
	@BeforeTest
	public void setUpDriver() {
		// Set ChromeDriver path
		System.setProperty("webdriver.chrome.driver",
		System.getProperty("user.dir") + testConfigProperties.chromeDriverPath());
		// Initialize ChromeDriver with the set options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		function = new ReusableFunction(driver);
	}

	// Method to navigate to the application's home page
	public PincodeInput gotoShowHomePage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(testConfigProperties.baseUrl()); // Open the base URL
		return PageFactory.initElements(driver, PincodeInput.class);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
