package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.Basetest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends Basetest{
	//pages will always give the actual result as coming from selenium

	@BeforeClass
	public void getAccountSetup() {
	accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
		String actualTitle=accPage.getAccountPageTitle();
		Assert.assertEquals(actualTitle,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageURLTest() {
		String actualUrl=accPage.getAccountPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accPageTitleSearchTest() {
		Assert.assertTrue(accPage.isSearchExist());

	}
	
	@Test
	public void getAccountPageHeadersListTest() {
		List<String> headersList=accPage.getAccountPageHeadersList();
		System.out.println("accounts page headers :"+headersList);
		Assert.assertEquals(headersList.size(),AppConstants.ACCOUNT_PAGE_HEADERCOUNT_LIST);
	}
	
	@Test
	public void getAccountPageHeadersValueTest() {
		List<String> headersList=accPage.getAccountPageHeadersList();
		System.out.println("accounts page headers :"+headersList);
		Assert.assertEquals(headersList,AppConstants.EXPECTED_ACCOUNTS_PAGEHEADERS_LIST_VALUE);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"macbook"},{"imac"},{"apple"},{"samsung"}	
		};
	}
	
	@Test(dataProvider="getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage=accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getTotalSearchProductCount()>0);	
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"macbook","Macbook Pro"},{"imac","iMac"},{"apple","Apple Cinema 30\""},{"samsung","Samsung SyncMaster 941BW"}	
		};
	}
	@Test(dataProvider="getProductTestData")
	public void searchProductTest(String searchKey,String productname) {
		searchPage=accPage.performSearch(searchKey);
		if(searchPage.getTotalSearchProductCount()>0) {
			prodInfoPage=searchPage.selectProduct(productname);
		String actualProductHeader=	prodInfoPage.productHeadervalue();
			Assert.assertEquals(actualProductHeader,productname);
		}
	}
	
	
	
	
	
	
	
}
