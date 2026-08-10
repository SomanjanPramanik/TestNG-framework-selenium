package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CookieBotPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class CookieBotPageSteps extends CookieBotPageElement {

	WebCommons selenium = new WebCommons();

	public CookieBotPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Switches to the newly opened Cookiebot tab and verifies it loaded successfully
	public void verifyCookiebotPageLoaded() {
		selenium.autoSwitchTab();
		selenium.waitForElementToBeVisible(headerText, 10);

		boolean isLoaded = selenium.isElementDisplayed(headerText);
		Assert.assertTrue(isLoaded, "CRITICAL FAILURE: Cookiebot info page did not load — 'Powered by Cookiebot' link is broken");
		Reports.printInReport("pass", "Cookiebot info page loaded successfully — 'Powered by Cookiebot' link works. Header text: " + selenium.getElementText(headerText));
	}
}