package com.wa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;// user might get default value which is null so we make it private
private JavascriptUtil jsutil;
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsutil=new JavascriptUtil(driver);

	}

//call this when no wait needed
	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)){
		jsutil.flash(element);
		}
		return element;
	}

	// call this method when needed with wait
	public WebElement getElement(By locator, int timeout) {
		
		return waitForElementVisibility(locator, timeout);
	}

	public void doSendkeys(By locator, String value) {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void getElementAttribute(By locator, String value) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String attributeValue = e.getAttribute(value);
			System.out.println(attributeValue);
		}
	}

	public List<String> getElementstextList(By locator) {

		List<String> textList = new ArrayList<String>();
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			textList.add(text);
		}
		return textList;
	}

	/************ select based dropdonw */
	public void doSelectDropdownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownByvalue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doSelectDropdownByVisibleTextValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doActionSendkeys(By locator, String value) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();

	}

	public void doActionClick(By locator) {
		Actions action = new Actions(driver);

		action.click(getElement(locator)).build().perform();
	}

	// *******wait utils section********************
	public Alert alertWaitPresence(int timeout) {
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String doElementGettext(By locator) {
		return getElement(locator).getText();
	}

	public boolean doElementIsDsiplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	
	
	public void getAlertText(int timeout) {
		alertWaitPresence(timeout).getText();
	}

	public void acceptAlert(int timeout) {
		alertWaitPresence(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		alertWaitPresence(timeout).dismiss();
	}

	public String waitForTitle(int timeout, String titlevalue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(titlevalue));
		return driver.getTitle();
	}
	
	public String waitForTitleAndFetch(int timeout, String titleFractionvalue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(titleFractionvalue));
		return driver.getTitle();
	}

	public String waitForUrlandFetch(int timeout, String urlvalue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(urlvalue));
		return driver.getCurrentUrl();
		// return driver.getTitle();//also getting the title
	}

	public Boolean waitForUrl(int timeout, String urlvalue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(urlvalue));
		// return driver.getTitle();//also getting the title
	}

	public WebElement waitForElementVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));// untill() is coming from wait class
		// visibilityOfElementLocated()-- element is present in dom and visible on the
		// page
	}

	public List<WebElement> waitForElementsPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitForElementsVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// *****framewait methods:
	public void waitForFrameAndSwitchToItByIdOrName(int timeout, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToItByIndex(int timeout, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToItByFrameElement(int timeout, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrameAndSwitchToItByLocator(int timeout, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	///////////////////
	public void clickWhenReady(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementToBeClickable(int timeout, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickWithActionsAndWait(int timeout, By locator) {
		WebElement Element = waitForElementToBeClickable(timeout, locator);
		Actions action = new Actions(null);
		action.click(Element).build().perform();
	}

	// searchgoogle suggestion method :
	public void doSearch(By suggestionList, String suggestionName) {
		List<WebElement> list = getElements(suggestionList);

		System.out.println(list.size());
		for (WebElement e : list) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggestionName)) {
				e.click();
				break;
			}
		}

	}

}
