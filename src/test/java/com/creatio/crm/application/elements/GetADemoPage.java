package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetADemoPage {
	
  //Header text
  @FindBy(xpath="//h1[contains(@class,'text-center cols-title ts-section__title ts-section__title_black')]")
  public WebElement headerText;
	
}
