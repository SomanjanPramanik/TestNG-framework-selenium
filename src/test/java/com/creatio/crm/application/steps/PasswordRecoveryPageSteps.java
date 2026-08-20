package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.creatio.crm.application.elements.PasswordRecoveryPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class PasswordRecoveryPageSteps extends PasswordRecoveryPageElement {

	WebCommons selenium = new WebCommons();

	public PasswordRecoveryPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verify password recovery page loaded
	public void verifyPageLoaded() {
	    try {
	        selenium.waitForElementToBeVisible(header, 10);
	        Reports.printInReport("pass", "Password Recovery page loaded");
	    } catch (Exception e) {
	        Reports.printInReport("fail", "Password Recovery page did not load");
	        throw e;
	    }
	}

	// Verify logo on recovery page
	public void verifyLogo() {
		selenium.waitForElementToBeVisible(logo, 10);
		Reports.printInReport("pass", "Password Recovery page logo is visible");
	}
}