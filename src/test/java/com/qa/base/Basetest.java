package com.qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.factory.OptionsManager;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchPage;
import com.wa.opencart.utils.ExcelUtils;

public class Basetest {
	// create the page references here as some other class might also need to use
	// this reference so we
	// can just use the same reference

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;//we use protected so we cn use in the child class as well
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage prodInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initProperty();
		driver = df.initDriver(prop);//this is thread local driver
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
		// new AccountsPage
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
