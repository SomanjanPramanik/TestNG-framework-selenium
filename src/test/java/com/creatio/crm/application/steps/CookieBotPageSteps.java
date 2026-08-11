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

	// Verify cookiebot external page loaded
	public void verifyCookiebotPageLoaded() {
		selenium.autoSwitchTab();
		selenium.waitForElementToBeVisible(headerText, 10);

		Assert.assertTrue(selenium.isElementDisplayed(headerText), "CRITICAL FAILURE: Cookiebot info page did not load");
		Reports.printInReport("pass", "Cookiebot info page loaded successfully. Header text: " + selenium.getElementText(headerText));
	}
}