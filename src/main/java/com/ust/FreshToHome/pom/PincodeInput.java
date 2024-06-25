package com.ust.FreshToHome.pom;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ust.FreshToHome.base.ReusableFunction;
import com.ust.FreshToHome.utilities.FileIO;

public class PincodeInput {
    WebDriver driver;
    ReusableFunction function;
    Properties properties;

    // Constructor
    public PincodeInput(WebDriver driver) {
        this.driver = driver;
        function = new ReusableFunction(driver);
        properties = FileIO.getProperties();
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(id = "autoComplete")
    WebElement inputpincode;

    @FindBy(id = "errorMsg")
    WebElement errorMsg;

    @FindBy(xpath = "//button[@type='button'][@class='btn btn-info btn-sm dropdown-toggle']")
    WebElement dropDown;

    /**
     * Method to get the current URL
     * @return Current URL as a String
     */
    public String getUrl() {
        return function.getCurrentUrl();
    }

    /**
     * Method to click on the pincode input field
     * @return true if the input field is displayed, false otherwise
     */
    public boolean clickoninputPincode() {
        function.clickOnElement(inputpincode);
        boolean result = inputpincode.isDisplayed();
        return result;
    }

    /**
     * Method to set the pincode
     * @param pincode The pincode to be set
     */
    public void setPincode(String pincode) {
        function.delaySeconds(1);
        function.setTextToInputField(inputpincode, pincode);
    }

    /**
     * Method to check if the error message is displayed
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isErrorMesageDisplayed() {
        function.delaySeconds(1);
        return errorMsg.isDisplayed();
    }

    /**
     * Method to check if the drop down is displayed
     * @return true if the drop down is displayed, false otherwise
     */
    public boolean isDropDownDisplayed() {
        function.delaySeconds(1);
        function.waitForElementToDisplay(dropDown);
        return dropDown.isDisplayed();
    }

    /**
     * Method to pass the driver to the next test
     * @return An instance of searchBoxPom class
     */
    public searchBoxPom passDriverToNextTest() {
        return PageFactory.initElements(driver, searchBoxPom.class);
//    	return new searchBoxPom(driver);
    }
}
