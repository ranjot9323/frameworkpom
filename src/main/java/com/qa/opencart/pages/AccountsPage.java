package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.wa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private By logout = By.linkText("Logout");
	private By header = By.cssSelector("div[id='content'] h2");
	private By searchField = By.name("search");
	private By searchIcon=By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);

	}

	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);

		// = driver.getTitle();
		System.out.println(title);
		return title;
	}

	public String getAccountPageURL() {
		String url = eleutil.waitForUrlandFetch(AppConstants.DEFAULT_SHORT_TIME_OUT,AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE );
		// driver.getCurrentUrl();
		System.out.println(url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleutil.waitForElementVisibility(logout, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		// driver.findElement(logout).isDisplayed();
	}

	public boolean isSearchExist() {
		// replace this with ()s from element util class
		return eleutil.waitForElementVisibility(searchField, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		// driver.findElement(searchField).isDisplayed();
	}

	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersList = eleutil.waitForElementsVisibility(header, AppConstants.DEFAULT_SHORT_TIME_OUT);
		// = driver.findElements(header);
		List<String> accHeadersList = new ArrayList<String>();
		for (WebElement e : headersList) {
			String text = e.getText();
			accHeadersList.add(text);
		}
		return accHeadersList;
	}
	
	public SearchPage performSearch(String productName) {
		if(isSearchExist()) {
			eleutil.doClick(searchField);
			eleutil.doSendkeys(searchField, productName);
			eleutil.doClick(searchIcon);
			return new SearchPage(driver);
		}
		else {
			System.out.println("search field doesnot exist");
			return null;
		}
	}
	
	
	
	
	

}
