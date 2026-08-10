package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CookiesConsentElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class CookiePageSteps extends CookiesConsentElement {

	WebCommons selenium = new WebCommons();

	public CookiePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// ---------- Main dialog ----------

	// Verifies the cookie consent dialog title matches the expected text
	public void verifyTitle() {
		selenium.waitForElementToBeVisible(title, 10);
		String actualTitle = selenium.getElementText(title);
		String expectedTitle = selenium.prop.getProperty("cookie_dialog_title");

		Assert.assertEquals(actualTitle, expectedTitle, "CRITICAL FAILURE: Cookie dialog title mismatch!");
		Reports.printInReport("pass", "Cookie dialog title loaded as expected: " + actualTitle);
	}

	// Verifies the cookie consent body text matches the expected text
	public void verifyText() {
		selenium.waitForElementToBeVisible(text, 10);
		String actualText = selenium.getElementText(text);
		String expectedText = selenium.prop.getProperty("cookie_dialog_text");

		Assert.assertEquals(actualText, expectedText, "CRITICAL FAILURE: Cookie dialog text mismatch!");
		Reports.printInReport("pass", "Cookie dialog text loaded as expected: " + actualText);
	}

	// Verifies the CookieBot logo and powered-by link are visible
	public void verifyPoweredByCookiebot() {
		selenium.waitForElementToBeVisible(logo, 10);
		selenium.waitForElementToBeVisible(cybotCookieBot, 10);
		Reports.printInReport("info", "Powered-by-Cookiebot logo and link text: " + selenium.getElementText(cybotCookieBot));
	}

	// ---------- Main action buttons ----------

	public void clickAllowAll() {
		selenium.waitAndClick(allowAllButton, 10);
		Reports.printInReport("info", "Clicked 'Allow all' on cookie dialog");
	}

	public void clickAllowSelection() {
		selenium.waitAndClick(allowSelectionButton, 10);
		Reports.printInReport("info", "Clicked 'Allow selection' on cookie dialog");
	}

	public void clickDeny() {
		selenium.waitAndClick(denyButton, 10);
		Reports.printInReport("info", "Clicked 'Deny' on cookie dialog");
	}

	public void clickShowDetails() {
		selenium.waitAndClick(showDetailsButton, 10);
		Reports.printInReport("info", "Clicked 'Show details' on cookie dialog");
	}

	// Toggle checkboxes for each cookie category on the summary view
	public void toggleNecessary(boolean status) {
		selenium.checkbox(necessaryButton, status);
		Reports.printInReport("info", "Necessary checkbox set to: " + status);
	}

	public void togglePreference(boolean status) {
		selenium.checkbox(preferenceButton, status);
		Reports.printInReport("info", "Preference checkbox set to: " + status);
	}

	public void toggleStatistics(boolean status) {
		selenium.checkbox(statisticButton, status);
		Reports.printInReport("info", "Statistics checkbox set to: " + status);
	}

	public void toggleMarketing(boolean status) {
		selenium.checkbox(marketingButton, status);
		Reports.printInReport("info", "Marketing checkbox set to: " + status);
	}

	// ---------- Detail view tabs ----------

	public void clickDetailsTab() {
		selenium.waitAndClick(details, 10);
		Reports.printInReport("info", "Clicked 'Details' tab in cookie dialog");
	}

	public void clickConsentTab() {
		selenium.waitAndClick(consent, 10);
		Reports.printInReport("info", "Clicked 'Consent' tab in cookie dialog");
	}

	public void clickAboutTab() {
		selenium.waitAndClick(about, 10);
		selenium.waitForElementToBeVisible(aboutParagraph, 10);
		Reports.printInReport("info", "About tab text: " + selenium.getElementText(aboutParagraph));
	}

	// Verifies the Privacy Policy link inside the cookie dialog is clickable
	public void verifyPrivacyPolicyLinkClickable() {
		selenium.waitForElementToClickable(privacyPolicyLink, 10);
		Assert.assertTrue(selenium.isElementEnabled(privacyPolicyLink),
				"CRITICAL FAILURE: Privacy Policy link in cookie dialog is disabled");
		Reports.printInReport("pass", "Privacy Policy link in cookie dialog is visible and clickable");
	}

	// ---------- Cookie category cards (expand + log every item) ----------



	public void expandNecessaryCard() {
		selenium.waitForElementToBeVisible(necessaryCookieCard, 10);
		selenium.waitAndClick(necessaryDialogButton, 10);
		selenium.logElementList("Necessary", necessaryCookieItems);
	}

	public void expandPreferencesCard() {
		selenium.waitForElementToBeVisible(preferencesCookieCard, 10);
		selenium.waitAndClick(preferencesDialogButton, 10);
		selenium.logElementList("Preferences", preferencesCookieItems);
	}

	public void expandMarketingCard() {
		selenium.waitForElementToBeVisible(marketingCookieCard, 10);
		selenium.waitAndClick(marketingDialogButton, 10);
		selenium.logElementList("Marketing", marketingCookieItems);
	}

	public void expandUnclassifiedCard() {
		selenium.waitForElementToBeVisible(unclassifiedCookieCard, 10);
		selenium.waitAndClick(unclassifiedDialogButton, 10);
		selenium.logElementList("Unclassified", unclassifiedCookieItems);
	}

	// ---------- Cross-domain consent ----------

	public void expandCrossDomainConsent() {
		selenium.waitForElementToBeVisible(crossDomainconsentCard, 10);
		selenium.waitAndClick(crossDomainconsentDialogButton, 10);
		selenium.waitForElementToBeVisible(crossDomainconsentDialogText, 10);

		Reports.printInReport("info", "Cross-domain consent text: " + selenium.getElementText(crossDomainconsentDialogText));
		selenium.logElementList("Cross-domain consent domains", crossDomainconsentItems);
	}

	// ---------- Footer ----------

	public void verifyCookieUpdateFooter() {
		selenium.waitForElementToBeVisible(cookieUpdateText, 10);
		Reports.printInReport("info", "Cookie update footer text: " + selenium.getElementText(cookieUpdateText));

		selenium.waitForElementToClickable(cookieBotLink, 10);
		Assert.assertTrue(selenium.isElementEnabled(cookieBotLink), "CRITICAL FAILURE: Cookiebot.com link is disabled");
		Reports.printInReport("info", "Cookiebot.com footer link is visible and clickable");
	}
}