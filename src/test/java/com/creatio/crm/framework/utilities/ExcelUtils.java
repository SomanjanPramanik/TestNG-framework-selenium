package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static String[][] excelReadData(String filePath, String sheetName) {
		String[][] data = null;
		try (FileInputStream file = new FileInputStream(filePath);
				XSSFWorkbook workbook = new XSSFWorkbook(file)) {
			XSSFSheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				System.out.println("Empty sheet");
			} else {
				int noOfRows = sheet.getPhysicalNumberOfRows();
				int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();

				// Printing Column names :
//		for(int i = 0 ; i < noOfColumns ; i++) {
//			System.out.println(sheet.getRow(0).getCell(i).getStringCellValue());
//		}

				// Getting actual data
				data = new String[noOfRows - 1][noOfColumns];
				DataFormatter formatter = new DataFormatter();
				for (int r = 1; r < noOfRows; r++) {
					for (int c = 0; c < noOfColumns; c++) {
						data[r - 1][c] = formatter.formatCellValue(sheet.getRow(r).getCell(c));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
