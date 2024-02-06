package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.wa.opencart.utils.ElementUtil;

public class LoginPage {

	//own webdriver 
	private WebDriver driver;
	private ElementUtil eleUtil;
	// 1.create page private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink=By.linkText("Register");
	//best practise to maintain the locators in the same file were we do page actions 
	
	//horrible to maintain the locators in the excel file or external file.

	// 2.class/page constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

		// TODO Auto-generated constructor stub
	}

	// 3.page methods
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println(title);
		return title;

	}

	public String getLoginPageurl() {
		String url = eleUtil.waitForUrlandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE );
		System.out.println(url);
		return url;
	}

	public boolean getForgotPwdLink() {
		return eleUtil.waitForElementVisibility(forgotPwdLink,AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		// driver.findElement(forgotPwdLink).isDisplayed();

	}

	public AccountsPage doLogin(String username, String pwd) {

//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		// now here we are using the elementutils ()instead of above code
		eleUtil.waitForElementVisibility(emailId,AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(username);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

		// this is test driver development approach or tdd
		// page chaining model in the pom design pattern
		// good technique to return next landing page class object
	}

	//this method is for clicking on register togo register page
	
	public RegisterPage nagigateToRegisterpage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
}
