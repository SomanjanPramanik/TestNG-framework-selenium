package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {
	public static Properties propReadData(String configFileName) {
        //"C:\\AutomationTraining\\CreatioCRM\\Config\\config.properties"
		String filepath = System.getProperty("user.dir")+"\\Config\\"+configFileName;
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(filepath);

			try {
				prop.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return prop;

	}
}
