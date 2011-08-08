package com.codereanimator.scrum.documentation.export;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.codereanimator.scrum.documentation.model.Story;
import com.codereanimator.scrum.documentation.model.Test;


public class ExcelExporter {
	
	/**
	 * Exports test cases in the given story to a MS Excel document. 
	 * 
	 * @param story
	 * @param outputStream
	 * @throws IOException
	 */
	public void export(Story story, OutputStream outputStream) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		
		int rowCount = 0;
		for(Test test : story.getTests()){
			HSSFRow row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(story.getNumber());
			row.createCell(1).setCellValue(story.getDescription());
			row.createCell(2).setCellValue(test.getName());
			row.createCell(3).setCellValue(test.getDescription());
			row.createCell(4).setCellValue(test.getType().toString());
		}
		
		workbook.write(outputStream);
	}
}
