package com.creatio.crm.api.tests;

import org.testng.annotations.Test;

import com.creatio.crm.framework.api.commons.JMeterCommons;

public class PerformanceRepositoryApiTest {

    @Test
    public void runGithubApiPerformanceTest() throws Exception {
    	
        JMeterCommons.runJMeterScript("Github repository API Performance testing.jmx");
        
    }
}