package edu.handong.csee.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ErrorDataFile extends Exception{
	private String key;
        public ErrorDataFile(){
			super("This file is wrong data");
		}
		public ErrorDataFile(String key){
			super("This file is wrong data");
			this.key=key;
		}
		public void makeErrorFile(){
			File error =new File("error.csv");
			try {
				FileWriter fw = new FileWriter(error, true);
	            fw.write(key);
	            fw.close();
			}catch(IOException e){
				e.printStackTrace();
				}
		}


}

