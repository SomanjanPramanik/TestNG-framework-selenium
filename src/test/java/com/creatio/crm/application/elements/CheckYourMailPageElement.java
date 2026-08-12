package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckYourMailPageElement {
	
//Check your mail header
	@FindBy(xpath="//h1[@class='page-title']")
	public WebElement header;
	
	//Subheader
	@FindBy(xpath="//div[@class='sso-password-message__description']")
	public WebElement subheader;
}
