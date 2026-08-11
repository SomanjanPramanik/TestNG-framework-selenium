package com.creatio.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.utilities.PropUtils;

public class WebCommons {

	// This class will have all the common methods related to selenium web action

	public WebDriver driver = new BasePage().getDriver();
	public Properties prop = PropUtils.propReadData("Config.properties");

	// method to launch the application
	public void launchTheApplication() {
		driver.get(prop.getProperty("url"));
	}

	// method to scroll to element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'})", element);
	}

	// method to click on element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	// method to double click on element
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	// method to click on hidden element
	public void jsClick(WebElement element) {
		scrollToElement(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	// method to enter text in textbox
	public void enterText(WebElement textbox, String textValue) {
		scrollToElement(textbox);
		textbox.clear();
		textbox.sendKeys(textValue);
	}

	// method to select checkbox
	public void checkbox(WebElement checkbox, boolean status) {
		scrollToElement(checkbox);
		if (checkbox.isSelected() != status) {
			checkbox.click();
		}
	}

	// method to log the text of every element in a list, tagged "info"
	public void logElementList(String listName, List<WebElement> items) {
		Reports.printInReport("info", listName + " (" + items.size() + " items):");
		for (WebElement item : items) {
			Reports.printInReport("info", "- " + getElementText(item));
		}
	}

	// closes the current (extra) tab and switches back to the given original tab
	public void closeCurrentTabAndSwitchBack(String originalHandle) {
	    if (driver.getWindowHandles().size() > 1) {
	        driver.close();
	        switchTab(originalHandle);
	    }
	}
	
	// method to navigate back in browser history
	public void navigateBack() {
	    driver.navigate().back();
	}

	// method to verify a page loaded by checking its header (and optional
	// sub-header) is visible
	public void verifyPageLoaded(WebElement headerText, WebElement subHeaderText) {

		// Verify the main header (which is always required)
		waitForElementToBeVisible(headerText, 10);
		Assert.assertTrue(isElementDisplayed(headerText), "CRITICAL FAILURE: Page header did not load");
		Reports.printInReport("pass", "Page loaded successfully. Header: " + getElementText(headerText));

		// Verify the sub-header (but only if one was passed in)
		if (subHeaderText != null) {
			waitForElementToBeVisible(subHeaderText, 10);
			Assert.assertTrue(isElementDisplayed(subHeaderText), "CRITICAL FAILURE: Page sub-header did not load");
			Reports.printInReport("pass", "Sub-header is visible: " + getElementText(subHeaderText));
		}
	}

	// method to select option from dropdown
	public void selectOption(WebElement dropdown, String selectBy, String option) {
		scrollToElement(dropdown);
		Select s = new Select(dropdown);
		if (selectBy.equalsIgnoreCase("visibleText")) {
			s.selectByVisibleText(option);
		} else if (selectBy.equalsIgnoreCase("value")) {
			s.selectByValue(option);
		} else if (selectBy.equalsIgnoreCase("index")) {
			s.selectByIndex(Integer.parseInt(option));
		}
	}

	// method to wait
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// method to wait using implicit wait
	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	// method to wait using explicit wait - wait for element
	public void waitForElementToBeVisible(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// method to wait using explicit wait - wait for element
	public void waitForElementToBeMoreThan(By loactor, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(loactor, 0));
	}

	// method to take screenshot of window
	public String takeWindowScreenshot(WebDriver driver, String screenshotNameAndFromat) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotNameAndFromat;
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(screenshotPath));
		return screenshotPath;
	}

	// method to take screenshot of element
	public String takeElementScreenshot(WebElement element, String screenshotNameAndFromat) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotNameAndFromat;
		File screenshotFile = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(screenshotPath));
		return screenshotPath;
	}

	// get element text
	public String getElementText(WebElement element) {
		return element.getText();
	}

	// get element attribute value
	public String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	// get title of the window
	public String getTitle() {
		return driver.getTitle();
	}

	// check element is displayed
	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	// check element is enabled
	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	// generate unique id
	public String uniqueId(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String uniqueId = sdf.format(Calendar.getInstance().getTime());
		return uniqueId;
	}

	// generic method to click on a dropdown element from a list, matching by
	// visible text
	public void selectFromList(List<WebElement> elements, String textToMatch) {
	    for (WebElement element : elements) {
	        String optionText = element.getText().trim().toLowerCase();
	        String targetText = textToMatch.trim().toLowerCase();
	        // 1. First try an exact match (e.g., "India" == "India")
	        // 2. Fall back to startsWith (e.g., "India +91" starts with "India")
	        if (optionText.equalsIgnoreCase(targetText) || optionText.startsWith(targetText)) {
	            click(element);
	            Reports.logger.pass("Selected: " + textToMatch);
	            return;
	        }
	    }

	    Reports.logger.warning("No matching element found for: " + textToMatch);
	    throw new RuntimeException("No matching element found for: " + textToMatch);
	}

	// wait to load image in DOM
	public void waitToLoadImage(WebElement element) {

		// Custom wait using JS so that it waits until really image loads
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2));

		wait.until((driver -> {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return (Boolean) js.executeScript("return arguments[0].complete && "
					+ "typeof arguments[0].naturalWidth !='undefined' && " + "arguments[0].naturalWidth>0", element);
		}));

	}

	// method to get current windowHandle
	public String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	// method to get All windowHandles
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	// method to switch to specific tab
	public void switchTab(String windowHandleId) {
		driver.switchTo().window(windowHandleId);
	}

	// method to auto switch to new tab
	public void autoSwitchTab() {
		Set<String> windowHandles = getWindowHandles();
		for (String windows : windowHandles) {
			if (!getCurrentWindowHandle().contains(windows)) {
				// The id which isn't the current one switch to that id
				driver.switchTo().window(windows);
			}
		}
	}

	// method to close current window
	public void close() {
		driver.close();
	}

	// method to wait using explicit wait - wait for element
	public void waitForElementToClickable(WebElement element, int seconds) {
		scrollToElement(element);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// wait and click element
	public void waitAndClick(WebElement element, int seconds) {
		waitForElementToClickable(element, seconds); // Automatically waits for visibility first!
		click(element);
	}

	// Waits for a specific element to disappear from the screen (become invisible)
	public void waitForElementToDisappear(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(timeout));
		wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf(element));
	}

	// Waits for a JavaScript alert to pop up on the screen
	public void waitForAlertToBePresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	// Grabs the alert and reads the text inside it
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	// Grabs the alert and clicks the "OK" button
	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// Grabs the alert and clicks the "Cancel" / "Dismiss" button
	public void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// Grabs a prompt alert, types text into it, and clicks "OK"
	public void enterTextAndAcceptAlert(String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
		alert.accept();
	}

	// Waits for a JavaScript alert to completely disappear from the screen
	public void waitForAlertToDisappear(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		// Use ExpectedConditions.not() to say "Wait until the alert is NOT present"
		wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
	}
	
	// The safest way to wait for an element to completely disappear from the DOM
		public void waitForNumberOfElementsToBeZero(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.numberOfElementsToBe(locator, 0));
		}

}
