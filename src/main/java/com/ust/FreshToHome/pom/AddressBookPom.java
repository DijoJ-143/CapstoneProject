package com.ust.FreshToHome.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ust.FreshToHome.base.ReusableFunction;

public class AddressBookPom {

	public WebDriver driver;
	ReusableFunction function;

	public AddressBookPom(WebDriver driver) {
		this.driver = driver;
		function = new ReusableFunction(driver);
		PageFactory.initElements(driver, this);

	}

	/** locators **/

	@FindBy(xpath = "//button[@title='Add New Address']")
	WebElement addnewaddress;

	@FindBy(id = "firstname")
	WebElement firstname;

	@FindBy(id = "lastname")
	WebElement lastname;

	@FindBy(name = "telephone")
	WebElement telephone;

	@FindBy(id = "street_1")
	WebElement streetAddress;

	@FindBy(id = "zip")
	WebElement postalCode;

	@FindBy(xpath = "//div[@class='input-box']//select")
	WebElement city;

	@FindBy(css = "button[id='test_button']")
	WebElement saveAddress;

	@FindBy(xpath = "//ul[@class='messages']//li/span")
	WebElement successmsg;
	@FindBy(xpath = "(//a[@class='link-remove'])[1]")
	WebElement deleteAddress;
	@FindBy(xpath = "//li[@class='success-msg']//ul//li//span")
	WebElement deleteSuccessMsg;
	@FindBy(xpath = "//div[@class='validation-advice']")
	WebElement errorMsg;


	// Click on Add New Address button
	public void clickOnAddNewAddress() {
		function.waitForElementToDisplay(addnewaddress);
		function.clickOnElement(addnewaddress);
	}

	// Enter new address
	public void enterNewAddress() {
		String data[][]=function.readExcelFile("PATH_2","Sheet3");
		function.setTextToInputField(firstname,data[0][0]);
		function.setTextToInputField(lastname, data[0][1]);
		function.setTextToInputField(telephone,data[0][2]);
		function.setTextToInputField(postalCode,data[0][4]);
		function.setTextToInputField(streetAddress, data[0][3]);	
	}

	// Get current URL
	public String getUrl() {
		return driver.getCurrentUrl();	
	}

	// Click on Save Address button
	public void clickOnSaveAddress() {
		System.out.println("Clicking on Save Address button");
		saveAddress.click();
		System.out.println("Clicked on Save Address button");
	}

	// Get success message
	public String getSuccessMessage() {
		function.waitForElementToDisplay(successmsg);
		return function.getText(successmsg);
	}

	// Click on Delete Address button
	public void deleteAddressClick() {
		function.clickOnElement(deleteAddress);
	}

	// Confirm delete address action in alert
	public void deleteAddressAlertClick() {
		function.delayForAlertMessage();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// Get success message after deleting address
	public String getSuccessMessageOfDelete() {
		function.waitForElementToDisplay(deleteSuccessMsg);
		return function.getText(deleteSuccessMsg);
	}

	// Enter new address from data table
	public void enterNewAddressFromDataTable(String firstname1, String telephone1, String address1, String postalcode1) {
		function.setTextToInputField(firstname,firstname1);
		function.setTextToInputField(telephone,telephone1);
		function.setTextToInputField(postalCode,postalcode1);
		function.setTextToInputField(streetAddress,  address1);
	}

	// Get error message
	public String getError() {
		function.waitForElementToDisplay(errorMsg);
		return function.getText(errorMsg);
	}
}