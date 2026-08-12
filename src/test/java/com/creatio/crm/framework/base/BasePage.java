package com.creatio.crm.framework.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.creatio.crm.framework.reports.Reports;

public class BasePage extends Reports {

	// This class will have all the common methods related to browser configurations

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    
	// method to launch the browser based on browser name coming from runner file
	@BeforeMethod(alwaysRun=true)
	@Parameters(value = "browser")
	public void setupBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else {
			Assert.fail("Invalid browser input");
			return;
		}
		driver.get().manage().window().maximize();
		driver.get().manage().deleteAllCookies();
	}
	
	// method to tear down the browser sessions
	@AfterMethod(alwaysRun=true)
	public void teardownBrowser() {
	    if (getDriver() != null) {
	        getDriver().quit();
	        driver.remove(); // Clears the ThreadLocal memory slot
	    }
	}

	// method to share browser session (driver) with other classes
	public WebDriver getDriver() {
		return driver.get();		
	}
	
	// method to update browser session
	public void setDriver(WebDriver newDriver) {
		driver.set(newDriver) ;		
	}
}
