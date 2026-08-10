package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesExplorePageElement {
	
	  //header text
	  @FindBy(xpath="//h1[contains(@class,'ts-title ts-title_h1 sales cols-title typewriter')]")
	  public WebElement headerText;
	  
	  //Sub-header text
	  @FindBy(xpath="//h1[contains(@class,'ts-title ts-title_h1 sales cols-title typewriter')]/following-sibling::div[contains(@class,'cols-text ts-text ts-text_large')]")
	  public WebElement subHeaderText;

}
