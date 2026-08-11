package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CookiesConsentElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class CookiePageSteps extends CookiesConsentElement {

	WebCommons selenium = new WebCommons();
	private String privacyPolicyOriginalHandle;

	public CookiePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verify the title of the cookie dialog
	public void verifyCookieDialogTitle() {
		selenium.waitForElementToBeVisible(title, 10);
		String actualTitle = selenium.getElementText(title);
		String expectedTitle = selenium.prop.getProperty("cookie_dialog_title");

		Assert.assertEquals(actualTitle, expectedTitle, "CRITICAL FAILURE: Cookie dialog title mismatch!");
		Reports.printInReport("pass", "Cookie dialog title loaded as expected: " + actualTitle);
	}

	// Verify the main description text of the cookie dialog
	public void verifyCookieDialogText() {
		selenium.waitForElementToBeVisible(text, 10);
		String actualText = selenium.getElementText(text).toLowerCase().replaceAll("[^a-z]","").trim();
		String expectedText = selenium.prop.getProperty("cookie_dialog_text").toLowerCase().replaceAll("[^a-z]","").trim();

		Assert.assertEquals(actualText, expectedText, "CRITICAL FAILURE: Cookie dialog text mismatch!");
		Reports.printInReport("pass", "Cookie dialog text loaded as expected: " + actualText);
	}

	// Verify the Cookiebot logo and text are visible
	public void verifyCookiebotLogo() {
		selenium.waitForElementToBeVisible(logo, 10);
		selenium.waitForElementToBeVisible(cybotCookieBot, 10);
		Reports.printInReport("info", "Powered-by-Cookiebot logo and link text: " + selenium.getElementText(cybotCookieBot));
	}

	// Click the 'Allow all' cookies button
	public void clickAllowAllButton() {
		selenium.waitAndClick(allowAllButton, 10);
		Reports.printInReport("info", "Clicked 'Allow all' on cookie dialog");
	}

	// Verify the cookie pop-up has disappeared from the screen
	public void verifyCookiePopupDisappeared() {
		selenium.waitForElementToDisappear(title, 10);
		Reports.printInReport("pass", "Cookie pop-up successfully disappeared from the screen");
	}

	// Click 'Allow selection' on the cookie dialog
	public void clickAllowSelection() {
		selenium.waitAndClick(allowSelectionButton, 10);
		Reports.printInReport("info", "Clicked 'Allow selection' on cookie dialog");
	}

	// Click 'Deny' on the cookie dialog
	public void clickDeny() {
		selenium.waitAndClick(denyButton, 10);
		Reports.printInReport("info", "Clicked 'Deny' on cookie dialog");
	}

	// Click 'Show details' on the cookie dialog
	public void clickShowDetailsLink() {
		selenium.waitAndClick(showDetailsButton, 10);
		Reports.printInReport("info", "Clicked 'Show details' on cookie dialog");
	}

	// Set status for Necessary cookies toggle
	public void toggleNecessary(boolean status) {
		selenium.checkbox(necessaryButton, status);
		Reports.printInReport("info", "Necessary checkbox set to: " + status);
	}

	// Click necessary toggle to trigger alert
	public void clickNecessaryToggle() {
		selenium.waitAndClick(necessaryButton, 10);
		Reports.printInReport("info", "Clicked the Necessary cookies toggle");
	}

	// Wait for mandatory warning alert to appear
	public void verifyAlertIsPresent() {
		selenium.waitForAlertToBePresent(10);
		Reports.printInReport("pass", "Mandatory cookie alert successfully popped up on the screen");
	}

	// Verify text inside the alert
	public void verifyAlertText() {
		String actualAlertText = selenium.getAlertText();
		String expectedAlertText = selenium.prop.getProperty("mandatory_cookie_alert_text");

		Assert.assertTrue(actualAlertText.contains(expectedAlertText), "CRITICAL FAILURE: Alert text mismatch. Found: " + actualAlertText);
		Reports.printInReport("pass", "Alert text matches expected: " + actualAlertText);
	}

	// Accept/click OK on the mandatory alert
	public void acceptAlert() {
		selenium.acceptAlert();
		Reports.printInReport("info", "Clicked OK to accept the alert");
	}

	// Verify alert disappeared
	public void verifyAlertDisappeared() {
		selenium.waitForAlertToDisappear(10);
		Reports.printInReport("pass", "Mandatory cookie alert successfully disappeared");
	}

	// Set status for Preference cookies toggle
	public void togglePreference(boolean status) {
		selenium.checkbox(preferenceButton, status);
		Reports.printInReport("info", "Preference checkbox set to: " + status);
	}

	// Set status for Statistics cookies toggle
	public void toggleStatistics(boolean status) {
		selenium.checkbox(statisticButton, status);
		Reports.printInReport("info", "Statistics checkbox set to: " + status);
	}

	// Set status for Marketing cookies toggle
	public void toggleMarketing(boolean status) {
		selenium.checkbox(marketingButton, status);
		Reports.printInReport("info", "Marketing checkbox set to: " + status);
	}

	// Click the Details tab
	public void clickDetailsTab() {
		selenium.waitAndClick(details, 10);
		Reports.printInReport("info", "Clicked 'Details' tab in cookie dialog");
	}

	// Click the Consent tab
	public void clickConsentTab() {
		selenium.waitAndClick(consent, 10);
		Reports.printInReport("info", "Clicked 'Consent' tab in cookie dialog");
	}

	// Click the About tab
	public void clickAboutTab() {
		selenium.waitAndClick(about, 10);
		selenium.waitForElementToBeVisible(aboutParagraph, 10);
		Reports.printInReport("info", "About tab text: " + selenium.getElementText(aboutParagraph));
	}

	// Verify privacy policy link is clickable in cookie dialog
	public void verifyPrivacyPolicyLinkClickable() {
		selenium.waitForElementToClickable(privacyPolicyLink, 10);
		Assert.assertTrue(selenium.isElementEnabled(privacyPolicyLink), "CRITICAL FAILURE: Privacy Policy link in cookie dialog is disabled");
		Reports.printInReport("pass", "Privacy Policy link in cookie dialog is visible and clickable");
	}

	// Click privacy policy link and switch tab
	public void clickPrivacyPolicyLink() {
		privacyPolicyOriginalHandle = selenium.getCurrentWindowHandle();
		selenium.waitAndClick(privacyPolicyLink, 10);
		selenium.autoSwitchTab();
		Reports.printInReport("info", "Clicked Privacy Policy link, switched to new tab");
	}

	// Close privacy policy tab and switch back
	public void closePrivacyPolicyTab() {
		selenium.closeCurrentTabAndSwitchBack(privacyPolicyOriginalHandle);
		Reports.printInReport("info", "Closed Privacy Policy tab, returned to Cookie popup page");
	}

	// Expand Necessary cookies card
	public void expandNecessaryCard() {
		selenium.waitForElementToBeVisible(necessaryCookieCard, 10);
		selenium.waitAndClick(necessaryDialogButton, 10);
		selenium.logElementList("Necessary", necessaryCookieItems);
	}

	// Expand Preferences cookies card
	public void expandPreferencesCard() {
		selenium.waitForElementToBeVisible(preferencesCookieCard, 10);
		selenium.waitAndClick(preferencesDialogButton, 10);
		selenium.logElementList("Preferences", preferencesCookieItems);
	}

	// Expand Marketing cookies card
	public void expandMarketingCard() {
		selenium.waitForElementToBeVisible(marketingCookieCard, 10);
		selenium.waitAndClick(marketingDialogButton, 10);
		selenium.logElementList("Marketing", marketingCookieItems);
	}

	// Expand Unclassified cookies card
	public void expandUnclassifiedCard() {
		selenium.waitForElementToBeVisible(unclassifiedCookieCard, 10);
		selenium.waitAndClick(unclassifiedDialogButton, 10);
		selenium.logElementList("Unclassified", unclassifiedCookieItems);
	}

	// Expand cross-domain consent card
	public void expandCrossDomainConsent() {
		selenium.waitForElementToBeVisible(crossDomainconsentCard, 10);
		selenium.waitAndClick(crossDomainconsentDialogButton, 10);
		selenium.waitForElementToBeVisible(crossDomainconsentDialogText, 10);
		Reports.printInReport("info", "Cross-domain consent text: " + selenium.getElementText(crossDomainconsentDialogText));
		selenium.logElementList("Cross-domain consent domains", crossDomainconsentItems);
	}

	// Verify cookie update footer details
	public void verifyCookieUpdateFooter() {
		selenium.waitForElementToBeVisible(cookieUpdateText, 10);
		Reports.printInReport("info", "Cookie update footer text: " + selenium.getElementText(cookieUpdateText));
		selenium.waitForElementToClickable(cookieBotLink, 10);
		Assert.assertTrue(selenium.isElementEnabled(cookieBotLink), "CRITICAL FAILURE: Cookiebot.com link is disabled");
		Reports.printInReport("info", "Cookiebot.com footer link is visible and clickable");
	}
}