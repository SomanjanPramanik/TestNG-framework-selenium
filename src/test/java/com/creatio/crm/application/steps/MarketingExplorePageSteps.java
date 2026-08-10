package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.MarketingExplorePageElement;
import com.creatio.crm.framework.web.commons.WebCommons;

public class MarketingExplorePageSteps extends MarketingExplorePageElement {

	WebCommons selenium = new WebCommons();

	public MarketingExplorePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		selenium.verifyPageLoaded(headerText, subHeaderText);
	}
}