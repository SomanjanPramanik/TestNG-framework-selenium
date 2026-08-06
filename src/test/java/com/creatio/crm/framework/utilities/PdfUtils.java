package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfUtils {
	public static String pdfReadData(String filePath) throws IOException {
		String data = null;
		try (FileInputStream file = new FileInputStream(filePath)) {
			PDDocument document = Loader.loadPDF(file.readAllBytes());
			PDFTextStripper text = new PDFTextStripper();
			data = text.getText(document);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
