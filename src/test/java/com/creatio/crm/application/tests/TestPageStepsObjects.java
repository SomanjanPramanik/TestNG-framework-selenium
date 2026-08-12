package com.creatio.crm.application.tests;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.creatio.crm.application.steps.CheckYourMailPageSteps;
import com.creatio.crm.application.steps.CookieBotPageSteps;
import com.creatio.crm.application.steps.CookiePageSteps;
import com.creatio.crm.application.steps.ExplorePageSteps;
import com.creatio.crm.application.steps.GetADemoPageSteps;
import com.creatio.crm.application.steps.HomePageSteps;
import com.creatio.crm.application.steps.LoginPageSteps;
import com.creatio.crm.application.steps.MarketingExplorePageSteps;
import com.creatio.crm.application.steps.PasswordRecoveryPageSteps;
import com.creatio.crm.application.steps.PrivacyPolicyPageSteps;
import com.creatio.crm.application.steps.SalesExplorePageSteps;
import com.creatio.crm.application.steps.ServiceExplorePageSteps;
import com.creatio.crm.application.steps.SignUpPageSteps;
import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.utilities.ExcelUtils;


public class TestPageStepsObjects extends BasePage{
	
	public CookieBotPageSteps cookieBotPageSteps = null;
	public CookiePageSteps cookiePageSteps = null;
	public ExplorePageSteps explorePageSteps = null;
	public GetADemoPageSteps getADemoPageSteps = null;
	public HomePageSteps homePageSteps = null;
	public LoginPageSteps loginPageSteps = null;
	public MarketingExplorePageSteps marketingExplorePageSteps = null;
	public PasswordRecoveryPageSteps passwordRecoveryPageSteps = null;
	public PrivacyPolicyPageSteps privacyPolicyPageSteps = null;
	public SalesExplorePageSteps salesExplorePageSteps = null;
	public ServiceExplorePageSteps serviceExplorePageSteps = null;
	public SignUpPageSteps signUpPageSteps = null;
	public CheckYourMailPageSteps checkYourMailPageSteps = null;
	@BeforeMethod(dependsOnMethods = {"setupBrowser"} )
	public void initDriver () {
		WebDriver driver = getDriver();
		cookieBotPageSteps = new CookieBotPageSteps(driver);
		cookiePageSteps = new CookiePageSteps(driver);
		explorePageSteps = new ExplorePageSteps(driver);
		getADemoPageSteps = new GetADemoPageSteps(driver);
		homePageSteps = new HomePageSteps(driver);
		loginPageSteps = new LoginPageSteps(driver);
		marketingExplorePageSteps = new MarketingExplorePageSteps(driver);
	    passwordRecoveryPageSteps = new PasswordRecoveryPageSteps(driver);
		privacyPolicyPageSteps = new PrivacyPolicyPageSteps(driver);
        salesExplorePageSteps = new SalesExplorePageSteps(driver);
		serviceExplorePageSteps = new ServiceExplorePageSteps(driver);
		signUpPageSteps = new SignUpPageSteps(driver);
		checkYourMailPageSteps = new CheckYourMailPageSteps(driver);
	}
	
	@DataProvider(name="Excel_data")
	public String[][] data(Method method){
		String[][] data = ExcelUtils.excelReadData("Test Data1.xlsx", method.getName());
		return data;
		
	}

}
