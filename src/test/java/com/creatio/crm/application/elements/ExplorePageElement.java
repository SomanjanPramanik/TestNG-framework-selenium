package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExplorePageElement {
	
	  //header text
	  @FindBy(xpath="//h1[contains(@class,'product-main-title cols-title')]")
	  public WebElement headerText;
	  
	  //Sub-header text
	  @FindBy(xpath="//h1[contains(@class,'product-main-title cols-title')]/following-sibling::div[@class='cols-text ts-section__text']")
	  public WebElement subHeaderText;

}
