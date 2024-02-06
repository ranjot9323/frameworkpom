package com.wa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {

	private static WebDriver driver;

	public JavascriptUtil(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitleJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title").toString();
	}

	public void generateAlert(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("alert(" + message + ")");

	}

	public void confirmPopup(String message) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("confirm('" + message + "')");
	}

	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].style.backgroundColor= '" + color + "'", element);
		try {
			Thread.sleep(8);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0", element);// 1
			changeColor(bgColor, element);// 2
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;

	}
}
