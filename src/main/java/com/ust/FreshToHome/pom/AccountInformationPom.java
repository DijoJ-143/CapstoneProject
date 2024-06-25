package com.ust.FreshToHome.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.FreshToHome.base.ReusableFunction;

public class AccountInformationPom {
	public WebDriver driver;
	ReusableFunction function;

	public AccountInformationPom(WebDriver driver) {
		this.driver = driver;
		function = new ReusableFunction(driver);
		PageFactory.initElements(driver, this);

	}

	/** locators **/

	@FindBy(xpath = "//input[@name='firstname']")
	WebElement accountfirstname;

	@FindBy(name = "lastname")
	WebElement accountlastname;

	@FindBy(linkText = "Address Book")
	WebElement addressbook;
	@FindBy(xpath = "(//a[@class='link-remove'])[1])")
	WebElement deleteAddress;

	/** Method **/

	public String validateAccountInformations() {
		function.waitForElementToDisplay(accountfirstname);
		String name = function.getAttribute(accountfirstname, "value");
		return name;
	}

	public void clickonAddressBook() {
		function.delaySeconds(2);
		function.clickOnElement(addressbook);

	}
	

}
