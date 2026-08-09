package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomepageElement {

	// Header text
	@FindBy(xpath = "//h1[contains(@class,'typewriter_completed')]")
	public WebElement headerText;

	// Demo Button @FindBy(xpath="//a[contains(text(),'Get a demo') and @class =
	// 'ts_btn ts_btn__theme-orange link-anchor']")
	public WebElement demoButton;

	// Explore Orange Button
	@FindBy(xpath = "//a[contains(text(),'Explore') and @class = 'ts_btn ts_btn__theme-orange link-anchor']")
	public WebElement exploreOrangeButton;

	// Hey Audrey img
	@FindBy(xpath = "//img[@class = 'ts-media__image ts-media__image_br']")
	public WebElement heyAudreyImg;

	// marketing button
	@FindBy(xpath = "//div[@class='cr-products__tab']/img[contains(@src,'pages/front/products/marketing-logo.svg')]")
	public WebElement marketingButton;

	// marketing text
	@FindBy(xpath = "//div[@class='cr-products__text typewriter typewriter_completed' "
			+ "and text()='Accelerate the lead-to-revenue cycle.']")
	public WebElement marketingText;

	// sales button
	@FindBy(xpath = "//div[@class='cr-products__tab']/img[contains(@src,'pages/front/products/sales-logo.svg')]")
	public WebElement salesButton;

	// marketing text
	@FindBy(xpath = "//div[@class='cr-products__text typewriter typewriter_completed' "
			+ "and text()='Manage end-to-end sales cycle.']")
	public WebElement salesText;

	// service button
	@FindBy(xpath = "//div[@class='cr-products__tab']/img[contains(@src,'pages/front/products/service-logo.svg')]")
	public WebElement serviceButton;

	// service text
	@FindBy(xpath = "//div[@class='cr-products__text typewriter typewriter_completed' "
			+ "and text()='Delight customers and drive service excellence.']")
	public WebElement serviceText;
	
	// img for each button selected
	@FindBy(xpath="//div[contains(@class,'cr-products__item_default') "
			+ "and contains(@class,'active')]//div[@class='ts-product-screen__bg']//img")
	public WebElement productImg;
	
	// explore button under marketing sales service 
	@FindBy(xpath="//div[contains(@class,'cr-products__item cr-products__item_default') "
			+ "and contains(@class,'active')]/div//div[contains(@class,'cr-products__link')]//a")
	public WebElement exploreproduct;
}
