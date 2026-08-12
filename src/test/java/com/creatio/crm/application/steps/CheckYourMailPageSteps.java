package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CheckYourMailPageElement;
import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.utilities.PropUtils;
import com.creatio.crm.framework.web.commons.WebCommons;

public class CheckYourMailPageSteps extends CheckYourMailPageElement{
	
	WebCommons selenium = new WebCommons();
	public CheckYourMailPageSteps(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	
	// Verify header text
	public void verifyHeaderText() {
		String headerText = PropUtils.propReadData("config.properties").getProperty("Check_you_email_header").trim().toLowerCase();
		selenium.wait(10);
		String actualText = selenium.getElementText(header).trim().toLowerCase();
		Assert.assertEquals(actualText, headerText);
		Reports.printInReport("pass", "Check your mail page loaded successfully . subheader : " + actualText);
	}
	
	// Verify sub header text
	public void verifySubHeaderText() {
	    String subHeaderText1 = PropUtils.propReadData("config.properties").getProperty("Check_you_email_subheader").trim().toLowerCase();
	    String subHeaderText2 = PropUtils.propReadData("config.properties").getProperty("Check_you_email_subheader2").trim().toLowerCase();
	    selenium.wait(10);
	    String actualText = selenium.getElementText(subheader).trim().toLowerCase();

	    if (actualText.contains(subHeaderText1) && actualText.contains(subHeaderText2)) {
	        Reports.printInReport("pass", "Check your mail page loaded successfully. subheader: " + actualText);
	    } else {
	        Reports.printInReport("fail", "Check your mail subheader mismatch. Actual: " + actualText);
	        Assert.fail("Subheader text did not match expected values.");
	    }
	}
}
