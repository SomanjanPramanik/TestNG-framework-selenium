package com.creatio.crm.application.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyPolicyPageElement {

	// Page header (e.g. "PRIVACY POLICY, LAST UPDATED: November 1, 2025")
	@FindBy(xpath = "//div[@class='agreements']//h1")
	public WebElement header;

	// Intro paragraph at the top of the policy body
	@FindBy(xpath = "//div[@class='content tab-2 new-style-content']//ul[@class='content']/li/p")
	public WebElement introText;

	// All major section headings (e.g. "A. GENERAL PRIVACY POLICY", "B. EUROPEAN PRIVACY POLICY")
	@FindBy(xpath = "//h2[starts-with(@id,'policy-')]")
	public List<WebElement> sectionHeadings;

	// All subsection headings (e.g. "1. INFORMATION WE MAY COLLECT")
	@FindBy(xpath = "//h3[starts-with(@id,'policy-')]")
	public List<WebElement> subsectionHeadings;
}