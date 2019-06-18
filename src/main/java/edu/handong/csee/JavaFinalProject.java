package edu.handong.csee;

import java.util.ArrayList;
import java.io.File;

import org.apache.commons.cli.*;

import edu.handong.csee.datamodel.ExcelForPictureAndTable;
import edu.handong.csee.datamodel.ExcelForSummary;
import edu.handong.csee.utils.ExcelWriter;
import edu.handong.csee.utils.ZipReader;


public class JavaFinalProject {
	
	private String input;
	private String output;
	private boolean help;
	
	public void run(String[] args) {
		Options options = createOptions();
		if(parseOptions(options, args)){
			if (help) {
				printHelp(options);
				return;
			}
			
			
			File myFile = new File(input);
			if(!myFile.exists()) {
				System.out.println("Input path is wrong");
				System.exit(0);
			}
	
			ArrayList<ExcelForSummary> values1 = new ArrayList<ExcelForSummary>();
			ArrayList<ExcelForPictureAndTable> values2 = new ArrayList<ExcelForPictureAndTable>();
			
			File[] fileList = myFile.listFiles();

			for(File file : fileList) {
				ZipReader.readFileInZip(file, values1, values2);
			}
			ExcelWriter.WriteAFile1(values1, output);
			ExcelWriter.WriteAFile2(values2, output);
		}
	}
	
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output file path")
				.hasArg()     
				.argName("Output path")
				.required() 
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show a Help page")
				.argName("Help")
				.build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "JavaFinalProject";
		String footer ="";
		formatter.printHelp("JavaFinalProject", header, options, footer, true);
	}
	
}