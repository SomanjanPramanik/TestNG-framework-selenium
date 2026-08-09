package com.creatio.crm.application.elements;

import java.util.List;

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

	// -----Cookie pop up------

	// Details
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogNavDetails']")
	public WebElement details;

	// Consent
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogNavDeclaration']")
	public WebElement consent;

	// About
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogNavAbout']")
	public WebElement about;

	// About paragraph text
	@FindBy(xpath = "//div[@role='paragraph']")
	public WebElement aboutParagraph;

	// Privacy policy link text
	@FindBy(xpath = "//a[@href='https://www.creatio.com/privacy-policy']")
	public WebElement privacyPolicyLink;

	// Necessary text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieContainerNecessaryCard']")
	public WebElement necessaryCookieCard;

	// Necessary Dialog
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogDetailBodyContentCookieContainerNecessary']")
	public WebElement necessaryDialogButton;

	// Grab ALL cookie group list items
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieTabsNecessary']/div/ul/li")
	public List<WebElement> necessaryCookieItems;

	// Preferences text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieContainerPreferenceCard']")
	public WebElement preferencesCookieCard;

	// Preferences Dialog
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogDetailBodyContentCookieContainerPreference']")
	public WebElement preferencesDialogButton;

	// Grab ALL cookie group list items
	@FindBy(xpath = "//div[@ip='CybotCookiebotDialogDetailBodyContentCookieTabsPreference']/div/ul/li")
	public List<WebElement> PreferencesCookieItems;

	// Marketing text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieContainerAdvertisingCard']")
	public WebElement marketingCookieCard;

	// Marketing Dialog
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogDetailBodyContentCookieContainerAdvertising']")
	public WebElement marketingDialogButton;

	// Grab ALL cookie group list items
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieTabsAdvertising']/div/ul/li")
	public List<WebElement> marketingCookieItems;

	// Unclassified text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieContainerUnclassifiedCard']")
	public WebElement unclassifiedCookieCard;

	// Unclassified Dialog
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogDetailBodyContentCookieContainerUnclassified']")
	public WebElement unclassifiedDialogButton;

	// Grab ALL cookie group list items
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBodyContentCookieTabsUnclassified']/div/ul/li")
	public List<WebElement> unclassifiedCookieItems;

	// Cross domain consent text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBulkConsent']")
	public WebElement crossDomainconsentCard;

	// Cross domain consent dialog
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogDetailBulkConsentLink']")
	public WebElement crossDomainconsentDialogButton;

	// Cross domain consent dialog text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBulkConsentListWrapper']/span")
	public WebElement crossDomainconsentDialogText;

	// Grab ALL cookie group list items
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailBulkConsentListWrapper']/dl/dt")
	public List<WebElement> crossDomainconsentItems;

	// Cookie update text
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailFooter']")
	public WebElement cookieUpdateText;
	
	// CookieBot link in update
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogDetailFooter']/a[contains(@href,'www.cookiebot.com')]")
	public WebElement cookieBotLink;
}
