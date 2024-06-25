package com.ust.FreshToHome.pom;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ust.FreshToHome.Exceptions.ElementInterceptedException;
import com.ust.FreshToHome.base.ReusableFunction;
public class LinksPage {
	public WebDriver driver;
	ReusableFunction reusable = new ReusableFunction(driver);
	public LinksPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//div[@class='footer-links container all-cities']//div//span)")
	List<WebElement> places;
	@FindBy(xpath = "//div[@class='footer-links container']//span//a")
	List<WebElement> products;
	@FindBy(css = "h1.no-results")
	WebElement searchResultMsg;
	
	
	public int placesList() {
		return places.size();
	}
	
	
	public boolean placesLinkClick() {
		int res = 0;
		for (int i = 1; i <= 190; i++) {
			String placeName = "(//div[@class='footer-links container all-cities']//div//span)[" + i + "]";
			WebElement place = driver.findElement(By.xpath(placeName));
			reusable.clickOnElement(place);
			res++;
		}
		return res == placesList() ? true : false;
	}
	public int productsList() {
		return products.size();
	}
	public boolean productsLinkClick() throws ElementInterceptedException {
		int newTabCount = 0;
		String mainWindow = driver.getWindowHandle();
		for (WebElement product : products) {
			try {
				reusable.clickOnElement(product);
			} catch (Exception e) {
				throw new ElementInterceptedException();
			}
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				if (!window.equals(mainWindow)) {
					driver.switchTo().window(window);
					newTabCount++;
					driver.close();
					driver.switchTo().window(mainWindow);
				}
			}
		}
		return newTabCount == products.size();
	}
	public AccountLoginPom passDriverToNextTest() {
		return PageFactory.initElements(driver, AccountLoginPom.class);
	}
}



