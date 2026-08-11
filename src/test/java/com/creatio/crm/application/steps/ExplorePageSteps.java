package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.creatio.crm.application.elements.ExplorePageElement;
import com.creatio.crm.framework.web.commons.WebCommons;

public class ExplorePageSteps extends ExplorePageElement {
	WebCommons selenium = new WebCommons();
	public ExplorePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void verifyPageLoaded() {
		selenium.verifyPageLoaded(headerText, subHeaderText);
	}
}