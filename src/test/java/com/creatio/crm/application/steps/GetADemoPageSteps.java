package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.GetADemoPageElement;
import com.creatio.crm.framework.web.commons.WebCommons;

public class GetADemoPageSteps extends GetADemoPageElement {

	WebCommons selenium = new WebCommons();

	public GetADemoPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void verifyPageLoaded() {
		selenium.verifyPageLoaded(headerText, null);
	}
}