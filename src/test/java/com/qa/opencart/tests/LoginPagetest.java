package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.Basetest;
import com.qa.opencart.constants.AppConstants;

public class LoginPagetest extends Basetest{
	 
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title,AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority=2)
	public void loginPageURLTest() {
		String url = loginPage.getLoginPageurl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Test(priority=3)
	public void loginPageForgotPwdLinkTest() {
		boolean forgotLink = loginPage.getForgotPwdLink();
		Assert.assertTrue(forgotLink);
	}
	@Test(priority=4)
	public void loginPageLoginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
}
