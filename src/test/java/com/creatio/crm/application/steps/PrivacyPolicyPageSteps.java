package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.PrivacyPolicyPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class PrivacyPolicyPageSteps extends PrivacyPolicyPageElement {

	WebCommons selenium = new WebCommons();

	public PrivacyPolicyPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verifies the Privacy Policy page loaded by checking the header
	public void verifyPageLoaded() {
		selenium.waitForElementToBeVisible(header, 10);
		boolean isLoaded = selenium.isElementDisplayed(header);

		Assert.assertTrue(isLoaded, "CRITICAL FAILURE: Privacy Policy page did not load");
		Reports.printInReport("pass", "Privacy Policy page loaded. Header: " + selenium.getElementText(header));
	}

	// Verifies and logs the introduction text of the policy
	public void verifyIntroText() {
		selenium.waitForElementToBeVisible(introText, 10);
		Reports.printInReport("info", "Intro text: " + selenium.getElementText(introText));
	}

	// Verifies and logs all the section and subsection headings
	public void verifyAndLogHeadings() {
		selenium.logElementList("Section headings", sectionHeadings);
		selenium.logElementList("Subsection headings", subsectionHeadings);
	}
}