package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CookieBotPage {

	//header text
	  @FindBy(xpath="//div[@id='CybotCookiebotDialogHeader']")
	  public WebElement headerText;
	
}
