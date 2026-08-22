package com.creatio.crm.framework.api.commons;

import java.io.File;
import java.nio.file.Paths;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import com.creatio.crm.framework.web.commons.WebCommons;

public class JMeterCommons {

	// run the jmeter script
	public static void runJMeterScript(String jmxFile) throws Exception {
		
		String timestamp = new WebCommons().uniqueId("yyyyMMdd_HHmmss");

		// 1. get the path of apache Jmeter libraries
		String jmeterHome = "src/test/resources/apache-jmeter-5.6.3";

		// 2. get the report path where you want to store your html report
		String htmlReport = Paths.get(jmeterHome, "JMeter reports", "JMeter html reports", "Report_" + timestamp)
				.toString();

		// 3. get the path for csv file where resultCollector would store all result
		// data
		String csvReport = Paths.get(jmeterHome, "JMeter reports", "Summary reports",
				"Performance testing(Github)_" + timestamp + ".csv").toString();

		// 4. get the .jmx file path
		String testPlanJmx = Paths.get(jmeterHome, "JMeter Testing Script jmx file", jmxFile).toString();

		// 5. get jmeter properties file where all the jmeter related configurations are
		// stored
		String jmeterPropertiesFile = Paths.get(jmeterHome, "bin", "jmeter.properties").toString();

		// 6. load JmeterHome
		JMeterUtils.setJMeterHome(jmeterHome);

		// 7. load Jmeter properties
		JMeterUtils.loadJMeterProperties(jmeterPropertiesFile);

		// 8. load jmx file
		File jmxScriptFile = new File(testPlanJmx);
		HashTree testPlanTree = SaveService.loadTree(jmxScriptFile);

		// 9. add resultCollector
		ResultCollector resultCollect = new ResultCollector();
		resultCollect.setFilename(csvReport);

		// 10a. Construct ReportGenerator BEFORE running (csvReport must be empty/non-existent here)
		JMeterUtils.setProperty("jmeter.reportgenerator.outputdir", htmlReport);
		ReportGenerator report = new ReportGenerator(csvReport, resultCollect);

		// 10b. run the script — this is when data actually gets written
		StandardJMeterEngine jmeterRun = new StandardJMeterEngine();
		testPlanTree.add(testPlanTree.getArray(), resultCollect);
		jmeterRun.configure(testPlanTree);
		jmeterRun.run();

		// 11. NOW generate the report — after data exists
		report.generate();

	}

}
