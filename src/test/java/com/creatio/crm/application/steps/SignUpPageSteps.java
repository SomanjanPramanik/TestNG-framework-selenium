package com.creatio.crm.application.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.SignUpPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class SignUpPageSteps extends SignUpPageElement {

	WebCommons selenium = new WebCommons();

	public SignUpPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Verifies that the main Sign Up page header matches the expected text
	public void verifySignUpHeader() {
		selenium.waitForElementToBeVisible(pageTitle, 10);
		String actualHeader = selenium.getElementText(pageTitle);
		String expectedHeader = selenium.prop.getProperty("Signup_page_header");

		Assert.assertEquals(actualHeader, expectedHeader, "CRITICAL FAILURE: Sign Up page header mismatch!");
		Reports.printInReport("pass", "Sign Up page header loaded as expected");
	}

	// Verifies the help text displayed right below the main header
	public void verifyHelpText() {
		selenium.waitForElementToBeVisible(helpText, 10);
		String actualHelpText = selenium.getElementText(helpText);
		String expectedHelpText = selenium.prop.getProperty("Signup_page_help_text");

		Assert.assertEquals(actualHelpText, expectedHelpText, "CRITICAL FAILURE: Sign Up page help text mismatch!");
		Reports.printInReport("pass", "Sign Up page help text loaded as expected");
	}

	// verify all field labels display correct text
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
	
	// Waits for the Name field to be visible and enters the name
	public void enterName() {
		selenium.waitForElementToBeVisible(nameField, 10);
		String name = selenium.prop.getProperty("signup_name");
		selenium.enterText(nameField, name);
		Reports.printInReport("info", "Entered Name: " + name);
	}

	// Enters the user's email address into the Email field
	public void enterEmail() {
		String email = selenium.prop.getProperty("signup_email");
		selenium.enterText(emailField, email);
		Reports.printInReport("info", "Entered Email: " + email);
	}

	// Enters the password and logs a masked version to the report for security
	public void enterPassword() {
		selenium.enterText(passwordField, selenium.prop.getProperty("signup_password"));
		Reports.printInReport("info", "Entered Password: *****");
	}

	// Enters the company name into the Company field
	public void enterCompany() {
		String company = selenium.prop.getProperty("signup_company");
		selenium.enterText(companyField, company);
		Reports.printInReport("info", "Entered Company: " + company);
	}

	// Opens the Country dropdown, types to filter, and selects the matching country
	public void selectCountry() {
		String targetCountry = selenium.prop.getProperty("signup_country");

		selenium.waitAndClick(countryDropdownToggle, 10);
		selenium.waitForElementToBeVisible(countrySearchInput, 10);
		selenium.enterText(countrySearchInput, targetCountry);

		selenium.waitForElementToBeMoreThan(By.xpath(
				"//ul[@id='select2-edit-field-country-results']/li[contains(@class,'select2-results__option--selectable')]"),
				10);
		selenium.selectFromList(countryOptions, targetCountry);

		Reports.printInReport("info", "Selected Country from dropdown: " + targetCountry);
	}

	// Enters the city name into the City field
	public void enterCity() {
		String city = selenium.prop.getProperty("signup_city");
		selenium.enterText(cityField, city);
		Reports.printInReport("info", "Entered City: " + city);
	}

	// Opens the custom phone country dropdown and selects the matching country
	public void selectCountryForPhone() {
		selenium.scrollToElement(phoneCountryDropdownToggle);
		selenium.waitAndClick(phoneCountryDropdownToggle, 10);
		String targetCountry = selenium.prop.getProperty("signup_country");
		selenium.selectFromList(phoneCountryOptions, targetCountry);
		Reports.printInReport("info", "Selected Country from dropdown: " + targetCountry);
	}

	// Enters the actual phone number digits into the phone field
	public void enterPhone() {
		String phone = selenium.prop.getProperty("signup_phone");
		selenium.enterText(phoneField, phone);
		Reports.printInReport("info", "Entered Phone Number: " + phone);
	}

	// Master step method: Calls all the individual data entry methods
	public void enterAllSignUpDetails() {
		enterName();
		enterEmail();
		enterPassword();
		enterCompany();
		selectCountry();
		enterCity();
		selectCountryForPhone();
		enterPhone();

		Reports.printInReport("info", "All Sign Up details form fields were populated successfully");
	}

	// verify the privacy policy text at the bottom of the form
	public void verifyPrivacyPolicyText() {
		selenium.waitForElementToBeVisible(privacypolicyText, 10);
		String actualPrivacyText = selenium.getElementText(privacypolicyText);
		String expectedPrivacyText = selenium.prop.getProperty("signup_privacy_text");

		Assert.assertTrue(actualPrivacyText.contains(expectedPrivacyText),
				"CRITICAL FAILURE: Privacy policy text mismatch. Found: " + actualPrivacyText);
		Reports.printInReport("pass", "Privacy policy text is displayed as expected");
	}

	// verify the Privacy Policy hyperlink is enabled
	public void verifyPrivacyPolicyLinkClickable() {
		selenium.waitForElementToClickable(privacypolicyLink, 10);

		Assert.assertTrue(selenium.isElementEnabled(privacypolicyLink),
				"CRITICAL FAILURE: Privacy Policy link is disabled on the page");
		Reports.printInReport("pass", "Privacy Policy link is visible and clickable");
	}

	// verify submit button shows "REGISTER" and is enabled
	public void verifyRegisterButton() {
		selenium.waitForElementToBeVisible(submitButton, 10);

		String actualButtonText = selenium.getAttributeValue(submitButton, "value");
		String expectedButtonText = selenium.prop.getProperty("signup_register_button_text");

		Assert.assertEquals(actualButtonText, expectedButtonText,
				"CRITICAL FAILURE: Submit button text is wrong. Found: " + actualButtonText);
		Assert.assertTrue(selenium.isElementEnabled(submitButton),
				"CRITICAL FAILURE: Register button is unexpectedly disabled");

		Reports.printInReport("pass", "Register button is enabled and displays the correct text");
	}

	// clicks the final Register/Submit button to create the account
	public void clickSubmit() {
		selenium.waitAndClick(submitButton, 10);
		Reports.printInReport("info", "Submit button is clicked");
	}

	// scans for any validation error messages displayed on the UI and logs them
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

	// validates that the password strength requirements pop-up is shown
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

	// clicks the text link to route the user back to the standard Login page
	public void clickLoginExistingAccount() {
		selenium.waitAndClick(loginExistingAccount, 10);
		Reports.printInReport("info", "Clicked on 'Log in using your existing account' link");
	}

	// verify links in the footer are present and clickable
	public void verifyFooterLinks() {
		// wait to check visibility
		selenium.waitForElementToBeVisible(Community, 10);
		selenium.waitForElementToBeVisible(academy, 10);
		selenium.waitForElementToBeVisible(marketplace, 10);

		// wait to check clickable state
		selenium.waitForElementToClickable(Community, 10);
		selenium.waitForElementToClickable(academy, 10);
		selenium.waitForElementToClickable(marketplace, 10);

		// Assert they are actually enabled
		Assert.assertTrue(selenium.isElementEnabled(Community), "CRITICAL FAILURE: Community footer link is disabled");
		Assert.assertTrue(selenium.isElementEnabled(academy), "CRITICAL FAILURE: Academy footer link is disabled");
		Assert.assertTrue(selenium.isElementEnabled(marketplace),
				"CRITICAL FAILURE: Marketplace footer link is disabled");

		Reports.printInReport("pass", "All footer links (Community, Academy, Marketplace) are visible and clickable");

	}

}