package edu.handong.csee.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.csee.datamodel.ExcelForSummary;
import edu.handong.csee.datamodel.ExcelForPictureAndTable;

public class ZipReader {

	public static void readFileInZip(File file, ArrayList<ExcelForSummary> values1, ArrayList<ExcelForPictureAndTable> values2) {
		try {
			ZipFile zipFile = new ZipFile(file);

		    Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
		   int i = 0 ;
		    while(entries.hasMoreElements()){
		    	i++;

		        ZipArchiveEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		        String fileType = entry.getName().substring(0,entry.getName().indexOf("."));
		        //System.out.println(entry);
		        //System.out.println("fileType: "+ fileType);
		        
		        ExcelReader myReader = new ExcelReader();
		        
		        if(i==1) {
		        	myReader.getDataOfFile1(stream, values1, file.getName());
		        }
		        else if(i==2) {
		        	myReader.getDataOfFile2(stream, values2, file.getName());
		        }
		        
//		        if(fileType.contains("요약문")){
//		        	myReader.getDataOfFile1(stream, values1, file.getName());
//		        }
//		        else if(fileType.contains("표")){
//		        	myReader.getDataOfFile2(stream, values2, file.getName());
//		        }
		    }
		    zipFile.close();
		} catch (IOException e) {
			e.getMessage();
		}
		
	}


}
