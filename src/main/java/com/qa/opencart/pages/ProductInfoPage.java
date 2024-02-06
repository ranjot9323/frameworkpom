package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.wa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	private By productHeader = By.tagName("h1");
	private By imageCount = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By cartSuccessMsg=By.cssSelector("div.alert.alert-success");
	
	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String productHeadervalue() {
		String headervalue = eleutil.doElementGettext(productHeader);
		System.out.println(headervalue);
		return headervalue;
	}

	public int getImageCount() {
		int totalImageCount = eleutil.waitForElementsVisibility(imageCount, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
		System.out.println(totalImageCount);
		return totalImageCount;
	}

	/*
	 * Brand: Apple Product Code: Product 18 Reward Points: 800 Availability: In
	 * Stock
	 */
	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		//can also use linkedhasmap if need the list 
		//in same order as show on page
		productInfoMap = new LinkedHashMap<String, String>();
		//if need to store data based on alphabetical order 
		// then use treemap based on sorting 
		productInfoMap = new TreeMap<String, String>();



		productInfoMap.put("productname", productHeadervalue());
		getProductMetaData();
		getProductPricedata();//encapsulation
		return productInfoMap;

	}

	private void getProductMetaData() {
		List<WebElement> metaList = eleutil.getElements(productMetaData);

		for (WebElement e : metaList) {
			String text = e.getText();
			String metaInfo[] = text.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}

	private void getProductPricedata() {
		List<WebElement> priceList = eleutil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String tax = priceList.get(1).getText();
		String extax = tax.split(":")[1].trim();
		productInfoMap.put("productPrice", price);
		productInfoMap.put("extax", extax);

	}
	
	public void enterQuantity(int qty) {
		System.out.println("product quantity is :"+qty);
		eleutil.doSendkeys(quantity, String.valueOf(qty));
	}
	
	public String addProductTocart() {
		eleutil.doClick(addToCartBtn);
	String successMsg=eleutil.waitForElementVisibility(cartSuccessMsg, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
	System.out.println(successMsg);
	return successMsg;
	}
	
	

}
