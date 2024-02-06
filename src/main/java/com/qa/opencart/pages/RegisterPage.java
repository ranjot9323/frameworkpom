package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.wa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	private By registerSuccessMesg = By.cssSelector("div#content h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	public boolean registerUser(String firstName,String lastName,String email,
			String telephone,String password,String subscribe) {
		
	eleutil.waitForElementVisibility(this.firstName,AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(firstName);
	eleutil.doSendkeys(this.lastName, lastName);
	eleutil.doSendkeys(this.email, email);
	eleutil.doSendkeys(this.telephone, telephone);
	eleutil.doSendkeys(this.password, password);

	eleutil.doSendkeys(this.confirmpassword, password);

if(subscribe.equalsIgnoreCase("yes")) {
	eleutil.doClick(subscribeYes);
}
else {
	eleutil.doClick(subscribeNo);
}
	eleutil.doClick(agreeCheckBox);
	eleutil.doClick(continueButton);
	String successMsg=eleutil.waitForElementVisibility(registerSuccessMesg,AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
System.out.println(successMsg);

if(successMsg.contains(AppConstants.USER_REG_SUCCESS_MSG)) {
	return true;
}
else {
	return false;
}

	}
	
	
	
	
}
