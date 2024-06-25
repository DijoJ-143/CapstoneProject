package com.ust.FreshToHome.pom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ust.FreshToHome.base.ReusableFunction;

public class ProductCategories {
    public static WebDriver webDriver;
    ReusableFunction reusable=new ReusableFunction (webDriver);

    /**
     * Constructor to initialize webDriver and page elements
     * @param driver The WebDriver instance to be used
     */
    public ProductCategories(WebDriver driver){
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    // WebElements
    @FindBy(xpath="(//div[@class='sort-by'])[1]//select")
    WebElement sortBy;
    
    @FindBy(xpath="(//div[@class='footer-links container']//a)[1]")
    WebElement productCategory;
    
    @FindBy(id = "autoComplete")
    WebElement inputpincode;
    
    @FindBy(xpath = "//div[@class='price-box']")
    List <WebElement> prices;
    
    
    /**
     * Method to set up pincode
   
     */
    public void pincodeSetup() {
    	String pincode="683513";
        reusable.setTextToInputField(inputpincode,pincode);
    }

    // Method to choose sort button
    public void chooseSortButton() {
        reusable.clickOnElement(sortBy);
    }

    /**
     * Method to choose sorting price
     * @param selectName The name of sorting option
     */
    public void chooseSortPrice(String selectName) {
        Select select = new Select(sortBy);
        select.selectByVisibleText(selectName);
    }
    
    // Method to click product category
    public void clickProductCategory() {
        reusable.clickOnElement(productCategory);
        reusable.manageWindowHandles(webDriver);
    }
    
    // Method to check if prices are sorted high to low
    public boolean priceHighToLowCheck() {
        List<Double> pricesData=extractNumericPrices(prices);
        List<Double> pricesDataSort=new ArrayList<>(pricesData);
        Collections.sort(pricesDataSort);
        Collections.reverse(pricesDataSort);
        return pricesData.equals(pricesDataSort) ? true : false;
    }
    
    // Method to check if prices are sorted low to high
    public boolean priceLowToHighCheck() {
        List<Double> pricesData=extractNumericPrices(prices);
        List<Double> pricesDataSort=new ArrayList<>(pricesData);
        Collections.sort(pricesDataSort);
        return pricesData.equals(pricesDataSort) ? true : false;
    }
    
    /**
     * Method to extract numeric prices
     * @param prices List of WebElement containing prices
     * @return List of Double containing numeric prices
     */
    public List<Double> extractNumericPrices(List<WebElement> prices) {
        List<Double> numericPrices = new ArrayList<>();
        for (WebElement price : prices) {
            String priceText = price.getText();
            String numericPart = priceText.split(" ")[0].split("₹")[1];
            numericPrices.add(Double.parseDouble(numericPart.replace(",", "")));
        }
        return numericPrices;
    }
}
