package edu.handong.csee.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.handong.csee.datamodel.ExcelForPictureAndTable;
import edu.handong.csee.datamodel.ExcelForSummary;

public class ExcelWriter {
	public static void WriteAFile1(ArrayList<ExcelForSummary> lines, String path) {
		String fileNameToSave = path.split(".xlsx")[0] + "1.xlsx";
		String[] columns = {"Zip 파일 이름", "제목", "요약문 (300자 내외)", "\"핵심어\n" + "(keyword,쉽표로 구분)\"", "조회날짜", "\"실제자료조회\n" + 
						"출처 (웹자료링크)\"", "원출처 (기관명 등)", "\"제작자\n" + "(Copyright 소유처)\""};
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("File1");
		
		Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        Row headerRow = sheet.createRow(0);
        
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        
        for(ExcelForSummary e : lines) {
        	Row row = sheet.createRow(rowNum++);
        	
        	row.createCell(0).setCellValue(e.getFileName());
        	row.createCell(1).setCellValue(e.getRow1());
        	row.createCell(2).setCellValue(e.getRow2());
        	row.createCell(3).setCellValue(e.getRow3());
        	row.createCell(4).setCellValue(e.getRow4());
        	row.createCell(5).setCellValue(e.getRow5());
        	row.createCell(6).setCellValue(e.getRow6());
        	row.createCell(7).setCellValue(e.getRow7());
        }
        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try {
        	FileOutputStream fileOut = new FileOutputStream(fileNameToSave);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (IOException e1) {
			e1.getMessage();
		}
	}
	
	
	public static void WriteAFile2(ArrayList<ExcelForPictureAndTable> lines, String path) {
		String fileNameToSave = path.split(".xlsx")[0] + "2.xlsx";
		String[] columns = {"Zip 파일 이름", "제목(반드시 요약문 양식에 입력한 제목과 같아야 함.)", "표/그림 일련번호", "자료유형(표,그림,…)", "자료에 나온 표나 그림 설명(캡션)", "자료가 나온 쪽번호"};
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("File2");
		
		Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        Row headerRow = sheet.createRow(0);
        
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        
        for(ExcelForPictureAndTable e : lines) {
        	Row row = sheet.createRow(rowNum++);
        	
        	row.createCell(0).setCellValue(e.getFileName());
        	row.createCell(1).setCellValue(e.getRow1());
        	row.createCell(2).setCellValue(e.getRow2());
        	row.createCell(3).setCellValue(e.getRow3());
        	row.createCell(4).setCellValue(e.getRow4());
        	row.createCell(5).setCellValue(e.getRow5());
        }
        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try {
        	FileOutputStream fileOut = new FileOutputStream(fileNameToSave);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (IOException e1) {
			e1.getMessage();
		}    
	}