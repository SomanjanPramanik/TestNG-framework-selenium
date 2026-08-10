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

	public void verifyHomepageLoaded() {
		selenium.verifyPageLoaded(headerText, null);
	}

	public void verifyLogo() {
		selenium.waitForElementToBeVisible(logo, 10);
		Reports.printInReport("pass", "Homepage logo is visible");
	}

	public void clickLogin() {
		selenium.waitAndClick(login, 10);
		Reports.printInReport("info", "Clicked Login link on homepage");
	}

	public void clickGetADemo() {
		selenium.waitAndClick(demoButton, 10);
		Reports.printInReport("info", "Clicked 'Get a demo' button on homepage");
	}

	public void clickExplore() {
		selenium.waitAndClick(exploreOrangeButton, 10);
		Reports.printInReport("info", "Clicked 'Explore' button on homepage");
	}

	public void verifyHeyAudreyImg() {
		selenium.waitForElementToBeVisible(heyAudreyImg, 10);
		Reports.printInReport("pass", "Hey Audrey image is visible");
	}
	
	public void clickMarketingTab() {
		selenium.waitAndClick(marketingButton, 10);
		Reports.printInReport("info", "Clicked Marketing product tab");
	}

	public void verifyMarketingTabContent() {
	    selenium.waitForElementToBeVisible(marketingText, 10);
	    Reports.printInReport("pass", "Marketing tab text is visible: " + selenium.getElementText(marketingText));
	}
	
	public void clickSalesTab() {
		selenium.waitAndClick(salesButton, 10);
		Reports.printInReport("info", "Clicked Sales product tab");
	}
	
	public void verifySalesTabContent() {
	    selenium.waitForElementToBeVisible(salesText, 10);
	    Reports.printInReport("pass", "Sales tab text is visible: " + selenium.getElementText(salesText));
	}

	public void clickServiceTab() {
		selenium.waitAndClick(serviceButton, 10);
		Reports.printInReport("info", "Clicked Service product tab");
	}
	public void verifyServiceTabContent() {
	    selenium.waitForElementToBeVisible(serviceText, 10);
	    Reports.printInReport("pass", "Service tab text is visible: " + selenium.getElementText(serviceText));
	}
	

	public void verifyProductImg() {
		selenium.waitForElementToBeVisible(productImg, 10);
		Reports.printInReport("pass", "Product image is visible for the active tab");
	}

	public void clickExploreProduct() {
		selenium.waitAndClick(exploreproduct, 10);
		Reports.printInReport("info", "Clicked Explore under active product tab");
	}
}