package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.Basetest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoTest extends Basetest{
	@BeforeClass
	public void productInfoPageSetup() {
	accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"macbook","Macbook Pro",4},{"imac","iMac",3},{"apple","Apple Cinema 30\"",6},{"samsung","Samsung SyncMaster 941BW",1}	
		};
	}
	@Test(dataProvider="getProductImagesTestData")
	public void imageCountTest(String searchkey,String prodname,int imgCount) {
		searchPage=accPage.performSearch(searchkey);
		prodInfoPage=searchPage.selectProduct(prodname);
		int actualImgCount=prodInfoPage.getImageCount();
		Assert.assertEquals(actualImgCount,imgCount);
	}
	
	@Test
	public void productInfotest() {
		searchPage=accPage.performSearch("macbook");
		prodInfoPage=searchPage.selectProduct("macbook pro");
		Map<String, String>actProdInfoMap=prodInfoPage.getProductInfo();
	
	softAssert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
	//can add more soft assertions here in case of map
	//so if first assertions ifs getting failed it will 
	//still assert rest of checks which is not case with the hardassert
	//after that we use 
	softAssert.assertAll();
	}
	
	@Test
	public void addTocarttest() {
		searchPage=accPage.performSearch("macbook");
		prodInfoPage=searchPage.selectProduct("macbook pro");
		prodInfoPage.enterQuantity(1);
		String addtocrtMsg=prodInfoPage.addProductTocart();
		softAssert.assertTrue(addtocrtMsg.contains("Success"));
		softAssert.assertTrue(addtocrtMsg.contains("macbook Pro"));

		
		softAssert.assertAll();

	}
	
	
	
}
