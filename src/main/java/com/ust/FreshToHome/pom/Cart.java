package com.ust.FreshToHome.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.FreshToHome.base.ReusableFunction;

public class Cart {
    public static WebDriver webDriver;
    ReusableFunction reusable=new ReusableFunction (webDriver);

    /**
     * Constructor to initialize webDriver and page elements
     * @param driver The WebDriver instance to be used
     */
    public Cart(WebDriver driver){
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    // WebElements
    @FindBy(xpath="(//div[@class=\"product-shop\"])[2]")
    WebElement firstItem;

    @FindBy(css = "div.menu-cart-item-count")
    WebElement cartBadge;
    
    @FindBy(css = "div.menu-cart-icon")
    WebElement cartIcon;
    
    @FindBy(css = "div.block-content")
    WebElement emptyCartMsg;
    
    @FindBy(id = "autoComplete")
    WebElement inputpincode;
    
    @FindBy(xpath = "(//div[@class='add-to-cart']//button)[1]")
    WebElement cartButton;
    
    @FindBy(xpath = "//div[@class=\"product-name\"]//h1")
    WebElement cartedProductDetails;
    
    @FindBy(css = "p.product-name")
    WebElement productName;
    
    @FindBy(xpath = "//div[@class='actions']/button[1]")
    WebElement myCartButton;

    @FindBy(xpath = "(//span[@class='cart-price'])[1]")
    WebElement unitPrice;

    @FindBy(xpath = "(//span[@class='cart-price'])[2]")
    WebElement subTotal;

    @FindBy(xpath ="//input[@class='input-text qty form-control']")
    WebElement qtyInput;
    
    @FindBy(xpath ="//button[@class='button btn-update']")
    WebElement updateButton;
    
    @FindBy(id ="empty_cart_button")
    WebElement clearButton;
    
    @FindBy(css ="button.button.button.btn-continue")
    WebElement continueButton;
    
    @FindBy(css ="div.page-title")
    WebElement emptyMessage;
    
    /**
     * Method to set up pincode
    
     */
    public void pincodeSetup() {
    	String pincode="683513";
        reusable.setTextToInputField(inputpincode,pincode);
    }
    
    // Method to select first product
    public void selectFirstProduct() {
        reusable.clickOnElement(firstItem);
    }

    /**
     * Method to get count from cart badge
     * @return String The text from cart badge
     */
    public String getCountFromCartBadge() {
        return cartBadge.getText();
    }
    
    // Method to check cart icon
    public void cartIconCheck() {
        reusable.clickOnElement(cartIcon);
    }
    
    // Method to get cart message
    public WebElement cartMessage() {
        return emptyCartMsg;
    }

    /**
     * Method to add product to cart and return product details
     * @return String The text of carted product details
     */
    public String cartProduct() {
        reusable.clickOnElement(cartButton);
        return cartedProductDetails.getText();
    }

    // Method to get cart product details
    public String cartProductDetails() {
        return cartedProductDetails.getText();
    }

    // Method to get product name from cart
    public WebElement productNameFromCart() {
        return productName;
    }

    // Method to click My Cart button
    public void clickMyCartButton() {
        reusable.waitForElementToDisplay(myCartButton);
        Actions actions=new Actions(webDriver);
        actions.moveToElement(myCartButton).click().build().perform();
    }
    
    /**
     * Method to get current URL
     * @return String The current URL
     */
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }
    
    /**
     * Method to set up quantity
     * @param qty The quantity to be set
     */
    public void quantitySetUp(int qty) {
        reusable.setTextToInputField(qtyInput,Integer.toString(qty));
    }

    // Method to click update button
    public void updateButtonClick() {
        reusable.clickOnElement(updateButton);
    }
    
    // Method to click clear button
    public void clearButtonClick() {
        reusable.clickOnElement(clearButton);
    }

    // Method to check clear button
    public WebElement  clearButtonCheck() {
        return emptyMessage;
    }
    
    // Method to click continue button
    public void continueButtonClick() {
        reusable.clickOnElement(continueButton);
    }
    
    /**
     * Method to check subtotal calculated is correct or not
     * @param quantity The quantity of the product
     * @return boolean Returns true if subtotal is correct, else false
     */
    public boolean subTotalCheck(int quantity) {
        Double qty=(double)quantity;
        String up = unitPrice.getText().substring(1);
        Double unitPrice=Double.parseDouble(up);
        Double Total=unitPrice*qty;
        String st = subTotal.getText().substring(1).replace(",", "");
        Double subTotal=Double.parseDouble(st);

        return Total.equals(subTotal)?true:false;
    }
}
