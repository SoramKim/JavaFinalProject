package edu.handong.csee.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import edu.handong.csee.datamodel.ExcelForSummary;
import edu.handong.csee.datamodel.ExcelForPictureAndTable;

public class ExcelReader {
	
	public void getDataOfFile1(InputStream is, ArrayList<ExcelForSummary> values, String fileName) {
		ExcelForSummary excel1 = null;
		String[] cellInfo = new String[7];
		try (InputStream inp = is) {
			Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    int rowNum = sheet.getPhysicalNumberOfRows();
		    System.out.println("File Name : " + fileName + ", NumOfRows : " + rowNum);
		    int rowIndex;
		    for(rowIndex = 1; rowIndex<rowNum; rowIndex++) {
		    	Row row = sheet.getRow(rowIndex);
			    for(int i=0; i<7; i++) {
			    	Cell cell = row.getCell(i);
			    	if (cell == null)
				    	cell = row.createCell(i);
			    	
			    	CellType type = cell.getCellType();
				    if(type.toString().contentEquals("STRING")) {
				    	cellInfo[i] = cell.getStringCellValue();
				    }
				    else if(type.toString().equals("NUMERIC")){
				    	int n = (int) cell.getNumericCellValue();
				    	cellInfo[i] = Integer.toString(n);
				    }
			    }
			    excel1 = new ExcelForSummary(fileName, cellInfo);
			    values.add(excel1);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void getDataOfFile2(InputStream is, ArrayList<ExcelForPictureAndTable> values, String fileName) {
		ExcelForPictureAndTable excel2 = null;
		String[] cellInfo = new String[5];
		try (InputStream inp = is) {
			Workbook wb = WorkbookFactory.create(inp);
		    Sheet sheet = wb.getSheetAt(0);
		    int rowNum = sheet.getPhysicalNumberOfRows();
		    int rowIndex;
		    for(rowIndex = 2; rowIndex < rowNum; rowIndex++) {
		    	Row row = sheet.getRow(rowIndex);
			    for(int i=0; i<5; i++) {
			    	Cell cell = row.getCell(i);
			    	if (cell == null)
				    	cell = row.createCell(i);
			    	
			    	CellType type = cell.getCellType();
				    if(type.toString().contentEquals("STRING")) {
				    	cellInfo[i] = cell.getStringCellValue();
				    }
				    else if(type.toString().equals("NUMERIC")){
				    	int n = (int) cell.getNumericCellValue();
				    	cellInfo[i] = Integer.toString(n);
				    }
			    }
			    excel2 = new ExcelForPictureAndTable(fileName, cellInfo);
			    values.add(excel2);
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
		}
	}
}