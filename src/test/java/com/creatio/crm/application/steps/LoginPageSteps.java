package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.LoginPageElements;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class LoginPageSteps extends LoginPageElements {

	// Accesing all selenium methods
	WebCommons selenium = new WebCommons();

	// PageFactory to connect elements with driver
	public LoginPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// launch the app and verify
	public void launchTheApp() {
		selenium.launchTheApplication();
		selenium.waitForElementToBeVisible(logo, 30);
		Assert.assertEquals(selenium.getTitle(), selenium.prop.getProperty("title"));
		Reports.printInReport("pass", "Application launched succesfully");
	}

	// Verify login header
	public void verifyLoginHeader() {
		selenium.waitForElementToBeVisible(pageTitle, 10);
		Assert.assertEquals(selenium.getElementText(pageTitle), selenium.prop.getProperty("Login_page_header"));
		Reports.printInReport("pass", "Login page Header Loaded as expected");
	}

	// Verify help text
	public void verifyHelpText() {
		selenium.waitForElementToBeVisible(helpText, 10);
		Assert.assertEquals(selenium.getElementText(helpText), selenium.prop.getProperty("Login_page_help_text"));
		Reports.printInReport("pass", "Login page help text Loaded as expected");
	}

	// Enter credential
	public void enterCredential() {
		selenium.waitForElementToClickable(emailField, 10);
		selenium.enterText(emailField, selenium.prop.getProperty("username"));
		selenium.waitForElementToClickable(passwordField, 10);
		selenium.enterText(passwordField, selenium.prop.getProperty("password"));
		Reports.printInReport("info", "Credential enterd as expected");
	}

	// Error message pop up after entering invalid credential
	public void showErrorMessage() {
		selenium.waitForElementToBeVisible(forgotPasswordMessage, 10);
		Reports.printInReport("info", "Error message shown as : " + selenium.getElementText(forgotPasswordMessage));
	}
	
	// Error message pop up after entering invalid credential in login
	public void verifyInvalidLoginError() {
	    selenium.waitForElementToBeVisible(errorMessage, 10);
	    Reports.printInReport("info", "Error message shown as : " + selenium.getElementText(errorMessage));
	}

	// verify forgot password link there visible and clickable
	public void verifyForgotpasswordAfterError() {
		selenium.waitAndClick(forgotPasswordAfterError, 10);
		Reports.printInReport("info", "forgot password is there as expected and clicked");
	}

	// verify forgot password visibile and clickable
	public void verifyForgotpassword() {
		selenium.waitAndClick(forgotPasswordInitial, 10);
		Reports.printInReport("info", "forgot password is there as expected and clicked");
	}

	// click login button
	public void clickLogin() {
		selenium.waitAndClick(loginButton, 10);
		Reports.printInReport("info", "Login button is clicked");
	}

	// click create new Account link
	public void clickSignin() {
		selenium.waitAndClick(createNewAccount, 10);
		Reports.printInReport("info", "Create new account link is clicked");
	}

}
