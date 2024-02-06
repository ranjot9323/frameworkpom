package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.Basetest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.RegisterPage;
import com.wa.opencart.utils.ExcelUtils;

public class RegisterPagetest extends Basetest{

	
		@BeforeClass
		public void registerPageSetup() {
		registerPage=loginPage.nagigateToRegisterpage();			
		}
	
		public String getRandomEmail() {
			Random random=new Random();
			String email="automation"+random.nextInt(1000)+"gmail.com";
			return email;
		}
		
		@DataProvider
		
		public Object[][] getRegTestData() {
			Object registerData[][]=ExcelUtils.getdata(AppConstants.REGISTER_SHEET_NAME);
			return registerData;
		
		}
		
	@Test(dataProvider="getRegTestData")
	public void userregTest(String firstname,String lastname,String telephone,
			String password,String subscribe) {
	Assert.assertTrue(registerPage.registerUser(firstname,lastname,getRandomEmail(),telephone,
			password,subscribe));

	}
	
	
	
	
}
