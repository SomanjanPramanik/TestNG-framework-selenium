package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryPageElement {
	
	//Logo
	@FindBy(xpath = "//div[@class='creatio-logo']//img")
	public WebElement logo;
	
	//Header
	@FindBy(xpath = "//h1[@class='page-title']")
	public WebElement header;

}
