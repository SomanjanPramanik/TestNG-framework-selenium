package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.SalesExplorePageElement;
import com.creatio.crm.framework.web.commons.WebCommons;

public class SalesExplorePageSteps extends SalesExplorePageElement {

	WebCommons selenium = new WebCommons();

	public SalesExplorePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		selenium.verifyPageLoaded(headerText, subHeaderText);
	}
}