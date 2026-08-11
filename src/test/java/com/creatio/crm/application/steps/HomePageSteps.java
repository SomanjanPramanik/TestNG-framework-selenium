package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.HomepageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.web.commons.WebCommons;

public class HomePageSteps extends HomepageElement {

	WebCommons selenium = new WebCommons();
	WebDriver driver;

	public HomePageSteps(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Verify homepage is loaded
	public void verifyHomepageLoaded() {
		selenium.verifyPageLoaded(headerText, null);
	}

	// Verify logo is visible on homepage
	public void verifyLogo() {
		selenium.waitForElementToBeVisible(logo, 10);
		Reports.printInReport("pass", "Homepage logo is visible");
	}

	// Click Login link on homepage
	public void clickLogin() {
		selenium.waitAndClick(login, 10);
		Reports.printInReport("info", "Clicked Login link on homepage");
	}

	// Click Get a Demo button
	public void clickGetADemo() {
		selenium.waitAndClick(demoButton, 10);
		Reports.printInReport("info", "Clicked 'Get a demo' button on homepage");
	}

	// Click Explore button
	public void clickExplore() {
		selenium.waitAndClick(exploreOrangeButton, 10);
		Reports.printInReport("info", "Clicked 'Explore' button on homepage");
	}

	// Verify 'Hey Audrey' image is visible
	public void verifyHeyAudreyImg() {
		selenium.waitForElementToBeVisible(heyAudreyImg, 10);
		Reports.printInReport("pass", "Hey Audrey image is visible");
	}

	// Click Marketing tab
	public void clickMarketingTab() {
		selenium.waitAndClick(marketingButton, 10);
		Reports.printInReport("info", "Clicked Marketing product tab");
	}

	// Verify Marketing tab content
	public void verifyMarketingTabContent() {
		selenium.waitForElementToBeVisible(marketingText, 10);
		Reports.printInReport("pass", "Marketing tab text is visible: " + selenium.getElementText(marketingText));
	}

	// Click Sales tab
	public void clickSalesTab() {
		selenium.waitAndClick(salesButton, 10);
		Reports.printInReport("info", "Clicked Sales product tab");
	}

	// Verify Sales tab content
	public void verifySalesTabContent() {
		selenium.waitForElementToBeVisible(salesText, 10);
		Reports.printInReport("pass", "Sales tab text is visible: " + selenium.getElementText(salesText));
	}

	// Click Service tab
	public void clickServiceTab() {
		selenium.waitAndClick(serviceButton, 10);
		Reports.printInReport("info", "Clicked Service product tab");
	}

	// Verify Service tab content
	public void verifyServiceTabContent() {
		selenium.waitForElementToBeVisible(serviceText, 10);
		Reports.printInReport("pass", "Service tab text is visible: " + selenium.getElementText(serviceText));
	}

	// Verify product image under active tab
	public void verifyProductImg() {
		selenium.waitForElementToBeVisible(productImg, 10);
		Reports.printInReport("pass", "Product image is visible for the active tab");
	}

	// Click Explore product button
	public void clickExploreProduct() {
		selenium.waitAndClick(exploreproduct, 10);
		Reports.printInReport("info", "Clicked Explore under active product tab");
	}
}