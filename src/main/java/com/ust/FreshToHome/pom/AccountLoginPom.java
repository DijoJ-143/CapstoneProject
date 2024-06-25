package com.ust.FreshToHome.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ust.FreshToHome.base.ReusableFunction;

public class AccountLoginPom {
	public WebDriver driver;
	ReusableFunction function;
	

	static int count = 0;

	public AccountLoginPom(WebDriver driver) {
		this.driver = driver;
		function = new ReusableFunction(driver);
		PageFactory.initElements(driver, this);
	}

	/** locators **/

	@FindBy(id = "youama-firstname")
	WebElement firstName;

	@FindBy(id = "youama-lastname")
	WebElement lastName;

	@FindBy(id = "youama-email")
	WebElement email;

	@FindBy(id = "youama-phonereg")
	WebElement registeredmobileNo;

	@FindBy(id = "youama-newsletter")
	WebElement chechbox;

	@FindBy(xpath = "//button[@id='registerBtn']")
	WebElement registerButton;

	@FindBy(css = "a.profile-icon")
	WebElement loginIcon;

	@FindBy(id = "sendOtpBtn")
	WebElement sendOtpBtn;

	@FindBy(id = "youama-phone")
	WebElement mobileNo;

	@FindBy(id = "youama-loginotp")
	WebElement otpInputArea;

	@FindBy(id = "otpLoginBtn")
	WebElement loginContinueButton;

	@FindBy(xpath = "(//div[@class='youama-ajaxlogin-error err-otpfailed err-nootp err-otpfraud err-inactive'])[1]")
	WebElement otperror;

	@FindBy(id = "resendotp")
	WebElement resendotp;

	@FindBy(xpath = "//p[@id='y-to-login']")
	WebElement switchLoginmethodtoPassword;

	@FindBy(xpath = "//input[@name='youama-email-phone']")
	WebElement emailforswitchedlogin;

	@FindBy(xpath = "//input[@name='youama-password']")
	WebElement passwordforswitchedlogin;

	@FindBy(xpath = "(//button[@class='button btn-green youama-ajaxlogin-button'])[1]")
	WebElement loginbuttonforswitchedlogin;

	@FindBy(css = "div[class='youama-ajaxlogin-error err-email err-noemailorphone err-wrongemail err-wronglogin err-phone err-wrongphone ']")
	WebElement emailaddressWarning;

	@FindBy(css = "div[class='youama-ajaxlogin-error err-password err-dirtypassword err-nopassword err-longpassword err-inactive']")
	WebElement passwordWarning;

	@FindBy(id = "y-to-otplogin")
	WebElement switchLoginmethodtoOTP;
	
	
	
	
	/** Method **/

	public boolean clickOnLogin() {
		function.delaySeconds(3);
		function.waitForElementToDisplay(loginIcon);
		boolean b = loginIcon.isDisplayed();
		function.clickOnElement(loginIcon);
		return b;
		
	}
	
	

	public void enterMobileNo(String mobileNo) {
		function.setTextToInputField(this.mobileNo, mobileNo);
	}

	public void clickOnSendOtp() {
		function.clickOnElement(sendOtpBtn);
		function.delaySeconds(15);
	}

	public void enterOtp(String otp) {
		function.setTextToInputField(otpInputArea, otp);
	}
	
	public void clickonLoginContinueButton() {
		function.clickOnElement(loginContinueButton);
	}

	public void loginWithRegisteredMobileNumber(String phoneNumber) {
		function.delaySeconds(2);
		function.clickOnElement(loginIcon);
		function.delaySeconds(2);
		function.setTextToInputField(mobileNo, phoneNumber);
		function.clickOnElement(sendOtpBtn);

	}
	
	public void otplogin() {
		String data[][]=function.readExcelFile("PATH_2","Sheet4");
        function.delaySeconds(2);
        function.setTextToInputField(mobileNo,data[1][0]);
        function.clickOnElement(sendOtpBtn);
        function.delaySeconds(15);
        function.clickOnElement(loginContinueButton);
        function.delaySeconds(4);

        
        
        
	}

	public String enteringWrongtOtp() {
		function.delaySeconds(3);
		function.setTextToInputFieldwithoutEnter(otpInputArea, "7890");
		function.clickOnElement(loginContinueButton);
		function.delaySeconds(3);
		function.waitForElementToDisplay(otperror);
		return otperror.getText();
	}

	public boolean enteringCorrectOtp() {
		function.clearText(otpInputArea);
		function.delaySeconds(10);
		boolean res = loginContinueButton.isDisplayed();
		function.clickOnElement(loginContinueButton);
		function.delaySeconds(2);
		return res;
	}

	public void enterDetailsForLogin(String fname, String lname, String vemail) {

		function.delaySeconds(1);
		function.waitForElementToDisplay(firstName);
		function.setTextToInputField(firstName, fname);
		function.delaySeconds(2);
		function.waitForElementToDisplay(lastName);
		function.setTextToInputField(lastName, lname);
		function.delaySeconds(2);
		function.waitForElementToDisplay(email);
		function.setTextToInputField(email, vemail);

	}

	public void clickOnRegister() {
		function.delaySeconds(1);
		function.clickOnElement(registerButton);
	}

	public void navigateToRegistrationPage() {
		function.delaySeconds(2);
		function.clearText(otpInputArea);
		function.clickOnElement(loginContinueButton);

		function.delaySeconds(2);
		if (otperror.isDisplayed() && count < 1) {
			count++;

			function.clickOnElement(resendotp);
			function.delaySeconds(5);
			navigateToRegistrationPage();

		}

		else {
			function.delaySeconds(3);
			function.clickOnElement(switchLoginmethodtoPassword);

		}

	}

	public boolean datasForLogin(String string, String string2) {

		function.delaySeconds(2);

		function.setTextToInputFieldwithoutEnter(emailforswitchedlogin, string);
		function.waitForElementToDisplay(passwordforswitchedlogin);
		function.delaySeconds(2);

		function.setTextToInputFieldwithoutEnter(passwordforswitchedlogin, string2);
		function.delaySeconds(2);

		function.clickOnElement(loginbuttonforswitchedlogin);
		function.delaySeconds(3);

		return emailaddressWarning.isDisplayed() || passwordWarning.isDisplayed();

	}

	public void otpLogin() {
		function.clickOnElement(switchLoginmethodtoOTP);
		function.delaySeconds(2);
		function.waitForElementToDisplay(mobileNo);
		function.setTextToInputField(mobileNo, "7736305205");
		function.delaySeconds(2);

		function.clickOnElement(sendOtpBtn);

		function.delaySeconds(20);
		function.clickOnElement(loginContinueButton);
		function.delaySeconds(2);
		
		function.waitForElementToDisplay(loginIcon);
		function.clickOnElement(loginIcon);

	}

	public void enterPhonenumber() {

		function.delaySeconds(2);
		function.clearText(mobileNo);
		function.setTextToInputField(mobileNo, "7736305205");
		function.delaySeconds(2);
		function.clickOnElement(sendOtpBtn);
		function.delaySeconds(6);
		function.clickOnElement(loginContinueButton);

	}
	
	
	
	public AccountDashboardPom passDriverToNextTest() {
		 return PageFactory.initElements(driver, AccountDashboardPom.class);
    }



	

}
