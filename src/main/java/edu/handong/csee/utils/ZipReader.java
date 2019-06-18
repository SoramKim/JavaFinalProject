package edu.handong.csee.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.csee.ErrorDataFile;

public class ZipReader implements Runnable {

	private String input;
	private String output;
	private String zip;
	
	public ZipReader(String input, String output, String zip) {
		this.input = input;
		this.output = output;
		this.zip = zip;
	}
	
	public void run() {
		ZipFile zipFile;
		String input = this.input + "/" + zip;
		try {
			
			zipFile = new ZipFile(input, "EUC-KR");
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			
			int count = 0;
			
		    while(entries.hasMoreElements()){
		    	ZipArchiveEntry entry = entries.nextElement();
		    	InputStream stream = zipFile.getInputStream(entry);	
		        String fileType = entry.getName().substring(0,entry.getName().indexOf("."));
		        
		        System.out.println("filetype: "+fileType);
		        
		        int type = -1;
		        String outputZipName = "";
		        
		        if(fileType.contains("요약문")) {			//type == 0
		        	type = 0;
		        	outputZipName = "result1.csv";
		        } else if(fileType.contains("표")) { 	//type == 1
		        	type = 1;
		        	outputZipName = "result2.csv";
		        } else {
		        	zipFile.close();
		        	throw new ErrorDataFile(fileType);
		        }
		        
		        ExcelWriter writer = new ExcelWriter(output+outputZipName, type);
		        ExcelReader reader = new ExcelReader(stream, zip, type);
		        
		        for(String[] value : reader.getData()) {
		        	for(String str : value) {
		        		System.out.println("str : "+str);
		        	}
		        	writer.writeFile(value);
		        }
		    }
		    
			zipFile.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorDataFile e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}