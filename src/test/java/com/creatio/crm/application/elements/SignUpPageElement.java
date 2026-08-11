package com.creatio.crm.application.elements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPageElement {

	// Logo of creatio 
	@FindBy(xpath = "//div[@class='creatio-logo']")
	public WebElement logo;

	// Sign Up page title (e.g. "Sign Up")
	@FindBy(xpath = "//h1[@class='page-title']")
	public WebElement pageTitle;

	// Sign Up page help text below the title
	@FindBy(xpath = "//div[@class='form-help-text']")
	public WebElement helpText;

	// Name input field
	@FindBy(xpath = "//input[@id='edit-field-name-0-value']")
	public WebElement nameField;

	// Name input field label
	@FindBy(xpath = "//label[@for='edit-field-name-0-value']")
	public WebElement nameFieldLabel;

	// Email input field
	@FindBy(xpath = "//input[@id='edit-mail']")
	public WebElement emailField;

	// Email input field label
	@FindBy(xpath = "//label[@for='edit-mail']")
	public WebElement emailFieldLabel;

	// Password input field
	@FindBy(xpath = "//input[@id='edit-pass']")
	public WebElement passwordField;

	// Password input field label
	@FindBy(xpath = "//label[@for='edit-pass']")
	public WebElement passwordFieldLabel;

	// Password pop-up message
	@FindBy(xpath = "//div[@class='password-requirements']")
	public WebElement passwordPopup;

	// Password requirements
	@FindBy(xpath = "//div[@class='password-requirements']/ul/li")
	public List<WebElement> passwordRequirements;

	// Company fields
	@FindBy(xpath = "//input[@id='edit-field-company-0-value']")
	public WebElement companyField;

	// Company fields label
	@FindBy(xpath = "//label[@for='edit-field-company-0-value']")
	public WebElement companyFieldLabel;
	
	// Country field dropdown toggle (the visible "Country" box, not the phone flag one)
	@FindBy(xpath = "//span[@role='combobox' and @aria-labelledby='select2-edit-field-country-container']")
	public WebElement countryDropdownToggle;

	// Search box that appears inside the dropdown after opening
	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement countrySearchInput;
	
	// Filtered country options shown in the results list
	@FindBy(xpath = "//ul[@id='select2-edit-field-country-results']/li[contains(@class,'select2-results__option--selectable')]")
	public List<WebElement> countryOptions;
	public By countryResultsLocator = By.xpath("//ul[@id='select2-edit-field-country-results']/li[contains(@class,'select2-results__option--selectable')]");

	// City field
	@FindBy(xpath = "//input[@id='edit-field-city-0-value']")
	public WebElement cityField;

	// City Field label
	@FindBy(xpath = "//label[@for='edit-field-city-0-value']")
	public WebElement cityFieldlabel;
	
	// Click this to open the country code dropdown
	@FindBy(xpath = "//div[contains(@class, 'iti__selected-flag')]")
	public WebElement phoneCountryDropdownToggle;
	
	// Grabs only the actual clickable countries, ignoring the divider!
	@FindBy(xpath = "//ul[@id='country-listbox']/li[contains(@class, 'iti__country')]")
	public List<WebElement> phoneCountryOptions;

	// Phone field
	@FindBy(xpath = "//input[@id='edit-field-phone-0-value']")
	public WebElement phoneField;

	// Phone Field label
	@FindBy(xpath = "//label[@for='edit-field-phone-0-value']")
	public WebElement phoneFieldlabel;

	// Submit button
	@FindBy(xpath = "//input[@id='edit-submit']")
	public WebElement submitButton;

	// Submit button label
	@FindBy(xpath = "//label[@for='edit-submit']")
	public WebElement submitButtonLabel;

	// Privacy policy text
	@FindBy(xpath = "//div[@class='form-privacy-policy']")
	public WebElement privacypolicyText;

	// Privacy policy link
	@FindBy(xpath = "//div[@class='form-privacy-policy']/a")
	public WebElement privacypolicyLink;

	// Login to existing account
	@FindBy(xpath = "//div[@class='creatio-links']/a")
	public WebElement loginExistingAccount;

	// Community site
	@FindBy(xpath = "//div[@class='sites']/a[contains(@href,'community')]")
	public WebElement Community;

	// Academy site
	@FindBy(xpath = "//div[@class='sites']/a[contains(@href,'academy')]")
	public WebElement academy;

	// Marketplace site
	@FindBy(xpath = "//div[@class='sites']/a[contains(@href,'marketplace')]")
	public WebElement marketplace;
	
	// Error message pop up
	@FindBy(xpath="//div[@class='messages messages--error']/div/div/ul/li")
	public List<WebElement> errorMessage;
}
