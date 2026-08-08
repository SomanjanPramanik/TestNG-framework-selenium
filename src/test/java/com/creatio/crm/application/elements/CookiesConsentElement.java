package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookiesConsentElement {

	// Cookies consent title
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyContentTitle']")
	public WebElement title;

	// Cookies consent text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyContentText']")
	public WebElement text;

	// Cookies consent logo
	@FindBy(xpath = "//img[@id='CybotCookiebotDialogPoweredbyImage']")
	public WebElement logo;

	// Cookies consent cybotCookieBot
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogPoweredbyCybot']")
	public WebElement cybotCookieBot;

	// Cookies consent allowAllButton
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")
	public WebElement allowAllButton;

	// Cookies consent allowSelectionButton
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection']")
	public WebElement allowSelectionButton;

	// Cookies consent denyButton
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
	public WebElement denyButton;

	// Cookies consent necessaryButton
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonNecessary']")
	public WebElement necessaryButton;

	// Cookies consent preferenceButton
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonPreferences']")
	public WebElement preferenceButton;

	// Cookies consent statisticButton
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonStatistics']")
	public WebElement statisticButton;

	// Cookies consent marketingButton
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonMarketing']")
	public WebElement marketingButton;

	// Cookies consent showDetailsButton
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogBodyEdgeMoreDetailsLink']")
	public WebElement showDetailsButton;
	
}
