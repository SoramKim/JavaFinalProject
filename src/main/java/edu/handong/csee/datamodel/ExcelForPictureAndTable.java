package edu.handong.csee.datamodel;

public class ExcelForPictureAndTable {
	private String fileName;
	private String row1;
	private String row2;
	private String row3;
	private String row4;
	private String row5;
	
	public ExcelForPictureAndTable (String fileName, String[] line) {
		this.fileName = fileName;
		
		row1 = line[0];
		row2 = line[1];
		row3 = line[2];
		row4 = line[3];
		row5 = line[4];
	}
	public String getFileName() {
		return fileName;
	}
	public String getRow1() {
		return row1;
	}
	public String getRow2() {
		return row2;
	}
	public String getRow3() {
		return row3;
	}
	public String getRow4() {
		return row4;
	}
	public String getRow5() {
		return row5;
	}
}