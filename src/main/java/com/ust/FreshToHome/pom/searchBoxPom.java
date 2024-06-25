package com.ust.FreshToHome.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.FreshToHome.base.ReusableFunction;

public class searchBoxPom {

	public WebDriver driver;
	ReusableFunction reusable;

	// Constructor
	public searchBoxPom(WebDriver driver) {
		this.driver = driver;
		reusable = new ReusableFunction(driver);
		PageFactory.initElements(driver, this);
	}

	// WebElements
	@FindBy(id = "search")
	WebElement searchInput;

	@FindBy(css = "h1.no-results")
	WebElement searchResultMsg;

	@FindBy(xpath = "(//h3)[4]")
	WebElement searchResult;

	// Methods for search functionality

	public searchBoxPom clickonSearchInput() {
		reusable.waitForElementToDisplay(searchInput);
		reusable.clickOnElement(searchInput);
		return this;

	}

	public void sendValue(String values) {

		reusable.setTextToInputField(searchInput, values);

	}

	/**
	 * Method to perform a search with null value
	 */
	public void searchWithNullValue() {

		clickonSearchInput().sendValue(" ");

	}

	/**
	 * Method to perform a search with wrong value
	 * 
	 * @return WebElement - search result message when no results found
	 */
	public WebElement searchWithWrongValue() {

		clickonSearchInput().sendValue("Lipstick");

		return searchResultMsg;
	}

	/**
	 * Method to perform a search with correct value
	 * 
	 * @return String - search result text
	 */
	public String searchWithValue() {

		clickonSearchInput().sendValue("Chicken");

		reusable.waitForElementToDisplay(searchResult);
		return searchResult.getText();
	}

	/**
	 * Method to pass driver to the next test
	 * 
	 * @return LinksPage - Page object for the next test
	 */
	public LinksPage passDriverToNextTest() {

		return PageFactory.initElements(driver, LinksPage.class);
//		return new LinksPage(driver);
	}
}