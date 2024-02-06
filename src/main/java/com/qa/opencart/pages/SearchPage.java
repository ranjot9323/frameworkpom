package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.wa.opencart.utils.ElementUtil;

public class SearchPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By searchResultList=By.cssSelector("div#content div.product-layout");
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eleutil=new ElementUtil(driver);
	}
	
	public int getTotalSearchProductCount() {
	return 	eleutil.waitForElementsVisibility(searchResultList, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
	
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator=By.linkText(productName);
		eleutil.waitForElementVisibility(productLocator, AppConstants.DEFAULT_SHORT_TIME_OUT).click();
	
	return new ProductInfoPage(driver);
	
	}
	
	
	
	
	
}
