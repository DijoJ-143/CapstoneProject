package com.ust.FreshToHome.pom;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ust.FreshToHome.base.ReusableFunction;
import com.ust.FreshToHome.base.TestConfig;
import com.ust.FreshToHome.utilities.FileIO;

public class CheckOut {
    // Initializing WebDriver and ReusableFunction objects
    private static Properties properties = FileIO.getProperties();
    public static WebDriver webDriver;
    ReusableFunction reusable = new ReusableFunction(webDriver);
    private boolean isOnPaymentModePage = false;
    // Constructor
    public CheckOut(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    // WebElement Locators
    @FindBy(id = "autoComplete")
    WebElement inputpincode;
    @FindBy(id = "youama-phone")
    WebElement phoneNo;
    @FindBy(xpath="(//div[@class='product-shop'])[1]")
    WebElement firstItem;
    @FindBy(css = "div.menu-cart-icon")
    WebElement cartIcon;
    @FindBy(xpath = "(//button[@class='button btn-cart'])[1]")
    WebElement cartButton;
    @FindBy(xpath = "//div[@class='actions']/button[2]")
    WebElement checkOutButton;
    @FindBy(id="search")
    WebElement searchButton;
    @FindBy(id="sendOtpBtn")
    WebElement otpButton;
    @FindBy(xpath="(//div[@class='card-content'])[1]")
    WebElement itemAmtLessthan193;
    @FindBy(css="li.error-msg")
    WebElement errorMsg;
    @FindBy(xpath="(//ul[@class='checkout-types']//li//button)[2]")
    WebElement proceedToCheckOutButton;
    @FindBy(xpath ="//button[@class='button btn-update']")
    WebElement updateButton;
    @FindBy(xpath ="//input[@class='input-text qty form-control']")
    WebElement qtyInput;
    @FindBy(id ="onepage-guest-register-button")
    WebElement loginButton;
    @FindBy(id ="resendotp")
    WebElement resendotp;
    @FindBy(id ="y-to-login")
    WebElement loginUsingPasswordLink;
    @FindBy(id ="youama-email-phone")
    WebElement loginEmail;
    @FindBy(id ="youama-password")
    WebElement loginPass;
    @FindBy(xpath ="(//button[@class='button btn-green youama-ajaxlogin-button'])[1]")
    WebElement loginButtonInCheckout;
    @FindBy(id ="otpLoginBtn")
    WebElement continueButton;
    @FindBy(xpath ="(//div[@class='input-box'])[1]")
    WebElement addressDropDown;
    @FindBy(xpath ="//li[@class='wide']//button")
    WebElement newAddressButton;
    @FindBy(id ="billing:telephone")
    WebElement telephone;
    @FindBy(id ="billing:street1")
    WebElement address;
    @FindBy(xpath ="(//div[@class='buttons-set']//button)[1]")
    WebElement continueShip;
    @FindBy(id ="dvice-required-entry-billing:street1")
    WebElement errorMsgForCred;
    @FindBy(xpath ="//div[@class='box-content']//address")
    WebElement addressContainer;
    @FindBy(xpath ="(//div[@class='input-box'])[9]")
    WebElement timeSlot;
    @FindBy(xpath ="(//div[@class='buttons-set']//button)[2]")
    WebElement continueDel;
    @FindBy(xpath ="//tr[@class='first last']")
    WebElement productContainerOrder;
    @FindBy(xpath ="//h3[@class='product-name']")
    WebElement productName;
    @FindBy(xpath ="//button[@class='button btn-checkout']")
    WebElement placeOrder;
    @FindBy(id ="pay-online")
    WebElement payOnline;
    @FindBy(linkText ="Debit / Credit Card")
    WebElement debitCard;
    @FindBy(xpath ="(//div[@class='linearLayout'])[115]")
    WebElement enterCardNumber;
    @FindBy(xpath ="(//div[@class='linearLayout'])[125]")
    WebElement expiry;
    @FindBy(xpath ="(//div[@class='linearLayout'])[135]")
    WebElement cvv;
    @FindBy(xpath ="(//div[@class='linearLayout'])[142]")
    WebElement proceedToPay;
    @FindBy(xpath ="(//span[@class='price'])[6]")
    WebElement itemSubtotalValue;
    @FindBy(xpath ="(//span[@class='price'])[7]")
    WebElement gstValue;
    @FindBy(xpath ="(//td[@class='a-right'])[4]")
    WebElement grandTotalValue;
    @FindBy(xpath ="(//div[@id='content']//div)[93]")
    WebElement verifyAndPayButton;
    @FindBy(xpath ="(//div[@id='content']//div)[85]")
    WebElement upiId;
    
    @FindBy(xpath="//form[@id='checkoutRetry']//button")
    WebElement continueButtonInPaPage;
 

    /**
     * Method to set pincode
     */
    public void pincodeSetup() {
        reusable.setTextToInputField(inputpincode,"683513");
    }

    /**
     * Method to search for an item
     * @param itemName Name of the item to search for
     */
    public void searchItem(String itemName) {
        reusable.setTextToInputField(searchButton,itemName);
    }

    /**
     * Method to add item to cart 
     */
    public void cartItemPriceLessThan193() {
        reusable.clickOnElement(itemAmtLessthan193);
        reusable.clickOnElement(cartButton);
        reusable.clickOnElement(cartIcon);
    }

    /**
     * Method to check error message (error message for carted product price less than 193)
     * @return Error message as String
     */
    public String messageCheck() {
        return errorMsg.getText();
    }

    /**
     * Method to click checkout button
     */
    public void checkOutButtonClick() {
        reusable.clickOnElement(checkOutButton);
    }

    /**
     * Method to click proceed to checkout button
     */
    public void proceedToCheckOutButtonClick() {
        reusable.clickOnElement(proceedToCheckOutButton);
    }

    /**
     * Method to click update quantity
     */
    public void updateButtonClick() {
        reusable.clickOnElement(updateButton);
    }

    /**
     * Method to set quantity
     * @param qty Quantity to be set
     */
    public void quantitySetUp(int qty) {
        reusable.setTextToInputField(qtyInput,Integer.toString(qty));
    }

    /**
     * Method to verify calculated values
     * @return true if values match, false if they don't
     */
    public boolean calculatedValues() {
        String data[][]=reusable.readExcelFile("PATH_2","Sheet1");
        String subTotal=itemSubtotalValue.getText();
        String gst=gstValue.getText();
        String grandTotal=grandTotalValue.getText();
        String subTotalExcel=data[0][0];
        String gstExcel=data[0][1];
        String grandTotalExcel=data[0][2];
        return subTotal.contains(subTotalExcel)&&gst.contains(gstExcel)&&grandTotal.contains(grandTotalExcel);
    }

    /**
     * Method to login for order
     */
    public void loginForOrder() {
        reusable.clickOnElement(loginButton);
    }

    /**
     * Method to enter phone number
     */
    public void enterPhoneNumber() {
        reusable.delaySeconds(1);
        reusable.setTextToInputField(phoneNo,properties.getProperty("phoneNumber"));
    }

    /**
     * Method to click on OTP button
     */
    public void otpButtonClick() {
        reusable.clickOnElement(otpButton);
    }

    /**
     * Method to click on login using password link
     */
    public void loginUsingPassLinkClick() {
        reusable.clickOnElement(loginUsingPasswordLink);
    }

    /**
     * Method to enter login credentials
     */
    public void enterLoginData() {
        reusable.delaySeconds(5);
        loginEmail.sendKeys(properties.getProperty("userEmail"));
        loginPass.sendKeys(properties.getProperty("userPass"));
    }

    /**
     * Method to click on login with password button
     */
    public void loginwithPasswordClick() {
        reusable.clickOnElement(loginButtonInCheckout);
    }

    /**
     * Method to wait for OTP entry
     */
    public void waitToEnterOtp() {
        reusable.delaySeconds(15);
    }

    /**
     * Method to continue after entering OTP
     */
    public void continueButtonClick() {
        reusable.clickOnElement(continueButton);
       
    }

    /**
     * Method to check if shipping section is displayed
     * @return true if displayed, false if not
     */
    public boolean shippingSectionCheck() {
        reusable.waitForElementToDisplay(addressDropDown);
        return addressDropDown.isDisplayed();
    }

    /**
     * Method to click on new address option
     */
    public void newAddressOptionClick() {
        reusable.waitForElementToDisplay(newAddressButton);
        reusable.clickOnElement(newAddressButton);
    }

    /**
     * Method to click on continue shipping button
     */
    public void continueShipClick() {
        reusable.clickOnElement(continueShip);
    }

    /**
     * Method to enter details for a new address
     */
    public void enterDetailsForNewAddress() {
        reusable.delaySeconds(2);
        String data[][]=reusable.readExcelFile("PATH_2","Sheet2");
        String telephoneNo=data[0][0];
        String addressDet=data[0][1];
        reusable.setTextToInputField(telephone,telephoneNo);
        reusable.setTextToInputField(address,addressDet);
    }

    /**
     * Method to check if error message for credentials is displayed
     * @return true if displayed, false if not
     */
    public boolean errorMsgForCred() {
        return addressDropDown.isDisplayed();
    }

    /**
     * Method to check if delivery section is displayed
     * @return true if displayed, false if not
     */
    public boolean deliverySectionCheck() {
        reusable.delaySeconds(2);
        return timeSlot.isDisplayed();
    }

    /**
     * Method to read the address
     */
    public void readAdress() {
        address.getText();
    }

    /**
     * Method to check if delivery address is correct
     * @return true if correct, false if not
     */
    public boolean deliveryAddressCheck() {
        return addressContainer.getText().contains(properties.getProperty("address"));
    }

    /**
     * Method to click on continue delivery button
     */
    public void continueDelClick() {
        reusable.clickOnElement(continueDel);
    }

    /**
     * Method to check if order section is displayed
     * @return true if displayed, false if not
     */
    public boolean orderSectionCheck() {
        reusable.delaySeconds(2);
        return productContainerOrder.isDisplayed();
    }

    /**
     * Method to get carted product name
     * @return Product name as String
     */
    public String getProductName() {
        return productName.getText();
    }

    /**
     * Method to click on place order button
     */
    public void placeOrderClick() {
        reusable.clickOnElement(placeOrder);
    }

    /**
     * Method to choose online payment method
     */
    public void choosePayOnline() {
        reusable.clickOnElement(payOnline);
    }

    /**
     * Method to manage alert
     * @return "alert present" if alert is present
     */
    public String manageAlert() {
        reusable.delayForAlertMessage();
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        return "alert present";
    }

    /**
     * Method to place order and manage JustPay
     */
    public void placeOrderManageJustpay() {
        reusable.clickOnElement(placeOrder);
        reusable.delaySeconds(2);
    }

    /**
     * Method to get Juspay URL
     */
    public void juspayUrl() {
        reusable.manageWindowHandles(webDriver);
    }
 
 
    /**
     * Method to navigate back if URL contains "retry"
     */
    public void correctUrl() {
        if(webDriver.getCurrentUrl().contains("retry")) {
            webDriver.navigate().back();
        }
        reusable.delaySeconds(10);
     
    }

    /**
     * Method to click on my cart button
     */
    public void clickMyCartButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOf(checkOutButton));
        Actions actions=new Actions(webDriver);
        actions.moveToElement(checkOutButton).click().build().perform();
    }
}