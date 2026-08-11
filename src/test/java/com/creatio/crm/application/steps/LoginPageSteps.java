package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.LoginPageElements;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class LoginPageSteps extends LoginPageElements {

	WebCommons selenium = new WebCommons();

	public LoginPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Open the login application URL
	public void launchTheApp() {
		selenium.launchTheApplication();
		Reports.printInReport("info", "Launched application URL");
	}

	// Check if the application logo is displayed on the page
	public void verifyLogoIsVisible() {
		selenium.waitForElementToBeVisible(logo, 30);
		Reports.printInReport("pass", "Login page logo is visible");
	}

	// Verify the browser tab title matches the expected value
	public void verifyPageTitle() {
		Assert.assertEquals(selenium.getTitle(), selenium.prop.getProperty("title"));
		Reports.printInReport("pass", "Login page title matches expected");
	}

	// Verify the header title text on the login page
	public void verifyLoginHeader() {
		selenium.waitForElementToBeVisible(pageTitle, 10);
		Assert.assertEquals(selenium.getElementText(pageTitle), selenium.prop.getProperty("Login_page_header"));
		Reports.printInReport("pass", "Login page header text is correct");
	}

	// Verify the description or help text under the login header
	public void verifyHelpText() {
		selenium.waitForElementToBeVisible(helpText, 10);
		Assert.assertEquals(selenium.getElementText(helpText), selenium.prop.getProperty("Login_page_help_text"));
		Reports.printInReport("pass", "Login page help text is correct");
	}

	// Enter the passed username parameter into the email field
	public void enterEmail(String email) {
		selenium.waitForElementToClickable(emailField, 10);
		selenium.enterText(emailField, email);
		Reports.printInReport("info", "Entered email: " + email);
	}

	// Enter the passed password parameter into the password field
	public void enterPassword(String password) {
		selenium.waitForElementToClickable(passwordField, 10);
		selenium.enterText(passwordField, password);
		Reports.printInReport("info", "Entered password");
	}

	// Click the login button to submit credentials
	public void clickLoginButton() {
		selenium.waitAndClick(loginButton, 10);
		Reports.printInReport("info", "Clicked login button");
	}

	// Click the link to navigate to the sign-up page
	public void clickCreateNewAccountLink() {
		selenium.waitAndClick(createNewAccount, 10);
		Reports.printInReport("info", "Clicked create new account link");
	}

	// Click the initial forgot password link
	public void verifyForgotpassword() {
		selenium.waitAndClick(forgotPasswordInitial, 10);
		Reports.printInReport("info", "Clicked forgot password link");
	}

	// Click the forgot password link appearing after an error
	public void verifyForgotpasswordAfterError() {
		selenium.waitAndClick(forgotPasswordAfterError, 10);
		Reports.printInReport("info", "Clicked forgot password after error link");
	}

	// Verify that an error message is shown for invalid login attempts
	public void verifyInvalidLoginError() {
		selenium.waitForElementToBeVisible(errorMessage, 10);
		Reports.printInReport("info", "Error message displayed: " + selenium.getElementText(errorMessage));
	}
}