package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	public WebDriver driver;
	public static String highlight;
	public Properties prop;
	public OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();

	/*
	 * this method is initializing the driver based on browser
	 */
	public WebDriver initDriver(Properties prop) {
		optionManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();

		String browserName = prop.getProperty("browser").toLowerCase();

		System.out.println("browsername is : " + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			//driver = new ChromeDriver(optionManager.getChromeOptions());

		} else if (browserName.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionManager.getFirefoxOptions());
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			tlDriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionManager.getEdgeOptions());
		} else {
			System.out.println("pass the right browser: " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		// driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		//return driver;
		return getDriver();
	}
//get locak thread copy of the driver
	public synchronized static WebDriver getDriver() {
	return 	tlDriver.get();
		
	}
	
	/*
	 * this method is reading the properties from congif file
	 *
	 */
	public Properties initProperty() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"/Users/ranjotgill/eclipse-workspace/PomSeries/src/test/resources/config/config.properties");

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// TODO Auto-generated catch block
			// using try catch instead of throws
		}
		return prop;
	}

	/*
	 * take screenshot
	 */
	public String getScreenShot() {
	File srcFile=	((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String filePath=System.getProperty("user.dir")+"/screenshot"+System.currentTimeMillis()+".png";
	File dstFile=new File(filePath);
	try {
		FileHandler.copy(srcFile, dstFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return filePath;
	}
	
	
	
	
	
	
	
	
	
	
}
