package com.ust.FreshToHome.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.FreshToHome.base.ReusableFunction;

public class AccountDashboardPom {

	public WebDriver driver;
	ReusableFunction function;

	public AccountDashboardPom(WebDriver driver) {
		this.driver = driver;
		function = new ReusableFunction(driver);
		PageFactory.initElements(driver, this);

	}

	/** locators **/
	@FindBy(linkText = "Account Information")
	WebElement accountinformation;

	@FindBy(className = "hello")
	WebElement holdername;

	


	


	/** Method **/

	public void clickOnAccountInformation() {
		function.delaySeconds(2);
		function.clickOnElement(accountinformation);
	}

/**
 * Gets the account user name.
 *
 * @return The account user name.
 */
	public String getAccountUserName() {
	
		function.waitForElementToDisplay(holdername);
		return function.getText(holdername).split(" ")[1];
			
		
	}
	
	

	/**
	 * Validates the user name.
	 *
	 * @param name The name to validate.
	 * @return True if the user name is valid, false otherwise.
	 */

	public boolean validateUserName(String name) {
	
		return function.getText(holdername).contains(name);

	}

}
