package com.creatio.crm.application.tests;

import org.testng.annotations.Test;

public class ApplicationTest extends TestPageStepsObjects {

	// Test 1: Verify Login page default UI elements and headers
	@Test(priority = 1, description = "Verify Login page default UI elements and headers")
	public void verifyLoginPageUI() {
		// Launch application and verify initial branding elements
		loginPageSteps.launchTheApp();
		loginPageSteps.verifyLogoIsVisible();
		loginPageSteps.verifyPageTitle();
		loginPageSteps.verifyLoginHeader();
		loginPageSteps.verifyHelpText();
	}

	// Test 2: Verify Cookie dialog content, details, and navigation tabs
	@Test(priority = 2, description = "Verify Cookie dialog content, details, and tabs")
	public void verifyCookieDialogContentAndTabs() {
		// Launch application and inspect the cookie consent banner
		loginPageSteps.launchTheApp();
		cookiePageSteps.verifyCookieDialogTitle();
		cookiePageSteps.verifyCookieDialogText();
		cookiePageSteps.verifyCookiebotLogo();
		cookiePageSteps.clickShowDetailsLink();
		cookiePageSteps.clickDetailsTab();
		cookiePageSteps.clickConsentTab();
	}

	// Test 3: Verify Privacy Policy page navigation from the cookie dialog
	@Test(priority = 3, dependsOnMethods = { "verifyLoginPageUI",
			"verifyCookieDialogContentAndTabs" }, description = "Verify Privacy Policy navigation from Cookie dialog")
	public void verifyCookiePopUpAndPrivacyPolicyNavigation() {
		// Launch application, open privacy policy from cookie pop-up, and return
		loginPageSteps.launchTheApp();
	    cookiePageSteps.verifyCookieDialogTitle();
	    cookiePageSteps.clickShowDetailsLink(); 
	    cookiePageSteps.clickAboutTab();        
	    cookiePageSteps.clickPrivacyPolicyLink();
	    privacyPolicyPageSteps.verifyPageLoaded();
	    privacyPolicyPageSteps.verifyIntroText();
	    cookiePageSteps.closePrivacyPolicyTab();
	}

	// Test 4: Verify Password Recovery page navigation and elements
	@Test(priority = 4, description = "Verify Password Recovery page functionality")
	public void verifyPasswordRecoveryNavigation() {
		// Launch application, click forgot password, and verify recovery page
		loginPageSteps.launchTheApp();
		loginPageSteps.verifyForgotpassword();
		passwordRecoveryPageSteps.verifyPageLoaded();
		passwordRecoveryPageSteps.verifyLogo();
	}

	// Test 5: Verify cookie consent pop-up acceptance and disappearance flow
	@Test(priority = 5, description = "Verify Cookie pop-up disappears upon accepting cookies")
	public void verifyCookiesPopupDismissWhenUserAcceptCookies() {
		// Launch application, accept all cookies, and verify banner closes
		loginPageSteps.launchTheApp();
		cookiePageSteps.verifyCookieDialogTitle();
		cookiePageSteps.clickAllowAllButton();
		cookiePageSteps.verifyCookiePopupDisappeared();
	}

	// Test 6: Verify End-to-End Sign Up flow using data provider and atomic steps
	@Test(priority = 6, dependsOnMethods = {
			"verifyLoginPageUI" }, dataProvider = "Excel_data", description = "Verify Application Sign Up")
	public void verifyApplicationSignUp(String user, String pass, String mail, String company, String country,
			String city, String phone) {
		// Launch application and handle cookie consent
		loginPageSteps.launchTheApp();
		cookiePageSteps.verifyCookieDialogTitle();
		cookiePageSteps.clickAllowAllButton();
		cookiePageSteps.verifyCookiePopupDisappeared();

		// Navigate from login to sign up page
		loginPageSteps.clickCreateNewAccountLink();
		signUpPageSteps.verifySignUpPageIsLaunched();

		// Fill out registration fields step-by-step
		signUpPageSteps.enterName(user);
		signUpPageSteps.enterEmail(mail);
		signUpPageSteps.enterPassword(pass);
		signUpPageSteps.enterCompany(company);

		// Select country and location information
		signUpPageSteps.clickCountryDropdown();
		signUpPageSteps.enterCountrySearchText(country);
		signUpPageSteps.selectCountryOption(country);
		signUpPageSteps.enterCity(city);

		// Enter phone country code and digits
		signUpPageSteps.clickPhoneCountryDropdown();
		signUpPageSteps.selectPhoneCountryOption(country);
		signUpPageSteps.enterPhone(phone);

		// Verify policy text and submit the registration form
		signUpPageSteps.verifyPrivacyPolicyText();
		signUpPageSteps.verifyRegisterButtonEnabled();
		signUpPageSteps.clickSubmitButton();
	}

	@Test(priority = 7, dependsOnMethods = {
			"verifyLoginPageUI" }, dataProvider = "Excel_data", description = "Verify Application Login ")
	public void verifyApplicationLogin(String user, String pass) {
		loginPageSteps.launchTheApp();
		cookiePageSteps.verifyCookieDialogTitle();
		cookiePageSteps.clickAllowAllButton();
		cookiePageSteps.verifyCookiePopupDisappeared();

		loginPageSteps.verifyLoginHeader();
		loginPageSteps.enterEmail(user);
		loginPageSteps.enterPassword(pass);
		loginPageSteps.clickLoginButton();

		// If the Excel data is an invalid user, verify the error message
		if (user.contains("Invalid")) {
			loginPageSteps.verifyInvalidLoginError();
		}
		// If the Excel data is a valid user, verify the homepage loaded
		else {
			homePageSteps.verifyHomepageLoaded();
		}
	}
}