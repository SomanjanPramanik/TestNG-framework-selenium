package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageElements {

	// Creatio logo on login page
	@FindBy(xpath = "//div[@class='creatio-logo']")
	public WebElement logo;

	// Login page title (e.g. "Log in")
	@FindBy(xpath = "//h1[@class='page-title']")
	public WebElement pageTitle;

	// Login page help text below the title
	@FindBy(xpath = "//div[@class='form-help-text']")
	public WebElement helpText;

	// Email input field
	@FindBy(xpath = "//input[@id='edit-name']")
	public WebElement emailField;

	// Password input field
	@FindBy(xpath = "//input[@id='edit-pass']")
	public WebElement passwordField;

	// Forgot password link
	@FindBy(xpath = "//div[@class='form-link']/a[@href='/user/password?destination=corp/com']")
	public WebElement forgotPasswordInitial;

	// Login submit button
	@FindBy(xpath = "//input[@id='edit-submit']")
	public WebElement loginButton;

	// Login invalid credential message
	@FindBy(xpath = "//div[@class='messages__content']")
	public WebElement errorMessage;

	// Forgot password message entering invalid credential
	@FindBy(xpath = "//div[@class='messages__content']")
	public WebElement forgotPasswordMessage;

	// Forgot password link after entering invalid credential
	@FindBy(xpath = "//a[@href='/user/password']")
	public WebElement forgotPasswordAfterError;

	// Create new account
	@FindBy(xpath = "//a[@href='/user/register?destination=corp/com']")
	public WebElement createNewAccount;

}