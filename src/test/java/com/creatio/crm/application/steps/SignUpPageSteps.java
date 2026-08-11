package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.SignUpPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class SignUpPageSteps extends SignUpPageElement {

	WebCommons selenium = new WebCommons();
	private String privacyPolicyOriginalHandle;

	public SignUpPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verify that the sign up page header is correctly displayed
	public void verifySignUpPageIsLaunched() {
		selenium.waitForElementToBeVisible(pageTitle, 10);
		String actualHeader = selenium.getElementText(pageTitle);
		String expectedHeader = selenium.prop.getProperty("Signup_page_header");

		Assert.assertEquals(actualHeader, expectedHeader, "CRITICAL FAILURE: Sign Up page header mismatch!");
		Reports.printInReport("pass", "Sign up page header loaded as expected");
	}

	// Verify the help text description on the sign up page
	public void verifyHelpText() {
		selenium.waitForElementToBeVisible(helpText, 10);
		String actualHelpText = selenium.getElementText(helpText);
		String expectedHelpText = selenium.prop.getProperty("Signup_page_help_text");

		Assert.assertEquals(actualHelpText, expectedHelpText, "CRITICAL FAILURE: Sign Up page help text mismatch!");
		Reports.printInReport("pass", "Sign Up page help text loaded as expected");
	}

	// Verify all individual field labels on the sign up page against properties
	public void verifyFieldLabels() {
		selenium.waitForElementToBeVisible(logo, 10);
		Reports.printInReport("pass", "Sign Up logo is visible");

		Assert.assertEquals(selenium.getElementText(nameFieldLabel), selenium.prop.getProperty("signup_name_label"));
		Assert.assertEquals(selenium.getElementText(emailFieldLabel), selenium.prop.getProperty("signup_email_label"));
		Assert.assertEquals(selenium.getElementText(passwordFieldLabel), selenium.prop.getProperty("signup_password_label"));
		Assert.assertEquals(selenium.getElementText(companyFieldLabel), selenium.prop.getProperty("signup_company_label"));
		Assert.assertEquals(selenium.getElementText(cityFieldlabel), selenium.prop.getProperty("signup_city_label"));
		Assert.assertEquals(selenium.getElementText(phoneFieldlabel), selenium.prop.getProperty("signup_phone_label"));

		Reports.printInReport("pass", "All Sign Up field labels match expected text");
	}

	// Enter the passed name parameter into the name field
	public void enterName(String name) {
		selenium.waitForElementToBeVisible(nameField, 10);
		selenium.enterText(nameField, name);
		Reports.printInReport("info", "Entered name: " + name);
	}

	// Enter the passed email parameter into the email field
	public void enterEmail(String email) {
		selenium.enterText(emailField, selenium.uniqueId("ddmmss")+email);
		Reports.printInReport("info", "Entered email: " + selenium.uniqueId("ddmmss")+email);
	}

	// Enter the passed password parameter into the password field
	public void enterPassword(String password) {
		selenium.enterText(passwordField, password);
		Reports.printInReport("info", "Entered password");
	}

	// Enter the passed company parameter into the company field
	public void enterCompany(String company) {
		selenium.enterText(companyField, company+selenium.uniqueId("yyyyMMddmmss"));
		Reports.printInReport("info", "Entered company: " + company+selenium.uniqueId("yyyyMMddmmss"));
	}

	// Click to open the country selection dropdown menu
	public void clickCountryDropdown() {
		selenium.waitAndClick(countryDropdownToggle, 10);
		Reports.printInReport("info", "Clicked country dropdown");
	}

	// Type the passed country name into the search box to filter options
	public void enterCountrySearchText(String country) {
		selenium.waitForElementToBeVisible(countrySearchInput, 10);
		selenium.enterText(countrySearchInput, country);
		Reports.printInReport("info", "Searched country: " + country);
	}

	// Select the matching country from the filtered list using the passed parameter
	public void selectCountryOption(String country) {
		selenium.waitForElementToBeMoreThan(countryResultsLocator, 10);
		selenium.selectFromList(countryOptions, country);
		Reports.printInReport("info", "Selected country: " + country);
	}

	// Enter the passed city parameter into the city field
	public void enterCity(String city) {
		selenium.enterText(cityField, city);
		Reports.printInReport("info", "Entered city: " + city);
	}

	// Click to open the country code dropdown for the phone field
	public void clickPhoneCountryDropdown() {
		selenium.scrollToElement(phoneCountryDropdownToggle);
		selenium.waitAndClick(phoneCountryDropdownToggle, 10);
		Reports.printInReport("info", "Clicked phone country dropdown");
	}

	// Select the country code for the phone number from the list using the passed parameter
	public void selectPhoneCountryOption(String country) {
		selenium.selectFromList(phoneCountryOptions, country);
		Reports.printInReport("info", "Selected phone country: " + country);
	}

	// Enter the passed phone number digits into the phone field
	public void enterPhone(String phone) {
		selenium.enterText(phoneField, phone + selenium.uniqueId("ddHHmmss"));
		Reports.printInReport("info", "Entered phone number: " + phone+selenium.uniqueId("ddHHmmss"));
	}

	// Verify the privacy policy notice text at the bottom of the form
	public void verifyPrivacyPolicyText() {
		selenium.waitForElementToBeVisible(privacypolicyText, 10);
		String actualPrivacyText = selenium.getElementText(privacypolicyText);
		String expectedPrivacyText = selenium.prop.getProperty("signup_privacy_text");

		Assert.assertTrue(actualPrivacyText.contains(expectedPrivacyText),
				"CRITICAL FAILURE: Privacy policy text mismatch. Found: " + actualPrivacyText);
		Reports.printInReport("pass", "Privacy policy text is displayed as expected");
	}

	// Verify that the Privacy Policy link is enabled and clickable
	public void verifyPrivacyPolicyLinkClickable() {
		selenium.waitForElementToClickable(privacypolicyLink, 10);
		Assert.assertTrue(selenium.isElementEnabled(privacypolicyLink),
				"CRITICAL FAILURE: Privacy Policy link is disabled on the page");
		Reports.printInReport("pass", "Privacy Policy link is visible and clickable");
	}

	// Click the Privacy Policy link on the sign up page and switch tabs
	public void clickPrivacyPolicyLink() {
		privacyPolicyOriginalHandle = selenium.getCurrentWindowHandle();
		selenium.waitAndClick(privacypolicyLink, 10);
		selenium.autoSwitchTab();
		Reports.printInReport("info", "Clicked sign-up privacy policy link and switched tab");
	}

	// Close the Privacy Policy tab and return to the sign up page
	public void closePrivacyPolicyTab() {
		selenium.closeCurrentTabAndSwitchBack(privacyPolicyOriginalHandle);
		Reports.printInReport("info", "Closed privacy policy tab and returned");
	}

	// Verify that the register button is enabled and displays the correct text
	public void verifyRegisterButtonEnabled() {
		selenium.waitForElementToBeVisible(submitButton, 10);

		String actualButtonText = selenium.getAttributeValue(submitButton, "value").toLowerCase();
		String expectedButtonText = selenium.prop.getProperty("signup_register_button_text").toLowerCase();

		Assert.assertEquals(actualButtonText, expectedButtonText,
				"CRITICAL FAILURE: Submit button text is wrong. Found: " + actualButtonText);
		Assert.assertTrue(selenium.isElementEnabled(submitButton),
				"CRITICAL FAILURE: Register button is unexpectedly disabled");

		Reports.printInReport("pass", "Register button is enabled and displays correct text");
	}

	// Click the register button to submit the sign up form
	public void clickSubmitButton() {
		selenium.waitAndClick(submitButton, 10);
		Reports.printInReport("info", "Clicked submit button");
	}

	// Verify that error messages are properly displayed on failed validations
	public void verifyErrorMessages() {
		if (errorMessage.size() > 0) {
			selenium.waitForElementToBeVisible(errorMessage.get(0), 10);
			Reports.printInReport("info", "Error messages displayed:");
			for (WebElement error : errorMessage) {
				Reports.printInReport("info", "Error: " + selenium.getElementText(error));
			}
		} else {
			Assert.fail("CRITICAL FAILURE: Expected error messages but none were displayed");
		}
	}

	// Verify that password requirement guidelines pop up correctly
	public void verifyPasswordRequirements() {
		if (passwordRequirements.size() > 0) {
			selenium.waitForElementToBeVisible(passwordPopup, 10);
			Reports.printInReport("info", "Password requirements displayed:");
			for (WebElement req : passwordRequirements) {
				Reports.printInReport("info", "- " + selenium.getElementText(req));
			}
		} else {
			Assert.fail("CRITICAL FAILURE: Expected password requirements popup but none were displayed");
		}
	}

	// Click the link to log in using an existing account
	public void clickLoginExistingAccount() {
		selenium.waitAndClick(loginExistingAccount, 10);
		Reports.printInReport("info", "Clicked on 'Log in using your existing account' link");
	}

	// Verify footer links are visible and clickable
	public void verifyFooterLinks() {
		selenium.waitForElementToBeVisible(Community, 10);
		selenium.waitForElementToBeVisible(academy, 10);
		selenium.waitForElementToBeVisible(marketplace, 10);

		selenium.waitForElementToClickable(Community, 10);
		selenium.waitForElementToClickable(academy, 10);
		selenium.waitForElementToClickable(marketplace, 10);

		Assert.assertTrue(selenium.isElementEnabled(Community), "CRITICAL FAILURE: Community footer link is disabled");
		Assert.assertTrue(selenium.isElementEnabled(academy), "CRITICAL FAILURE: Academy footer link is disabled");
		Assert.assertTrue(selenium.isElementEnabled(marketplace),
				"CRITICAL FAILURE: Marketplace footer link is disabled");

		Reports.printInReport("pass", "All footer links (Community, Academy, Marketplace) are visible and clickable");
	}
}