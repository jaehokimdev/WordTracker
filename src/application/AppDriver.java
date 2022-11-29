package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import exception.TreeException;
import manager.*;

public class AppDriver {

	public static void main(String[] args) throws TreeException, FileNotFoundException {
		String inputFileName = args[0];
		String option = args[1];
		String outputFileName;
		
		if (!(option.equals("-pf") || option.equals("-pl") || option.equals("-po"))) {
			System.out.println(option);
			System.out.println("Option is invalid. Please enter -pf, -pl or -po");
			System.exit(0);
		}
		
		if (args.length == 2) {
			new WordTracker(inputFileName, option);
		} else if (args.length == 4 && args[2].equals("-f")) {
			outputFileName = args[3];
			
			File file = new File(outputFileName);
		    FileOutputStream fos = new FileOutputStream(file);
		    PrintStream ps = new PrintStream(fos);
		    System.setOut(ps);
		    
			new WordTracker(inputFileName, option, outputFileName);
			
			PrintStream console = System.out;
			System.setOut(console);
			ps.close();
				        
	        try {
	            String str;
				BufferedReader readFile = new BufferedReader(new FileReader(outputFileName));
	            
	            while((str = readFile.readLine()) != null) {
	                System.out.println(str);
	            }
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
		} else {
			System.out.println("Input method is invalid. Try again.");
			System.exit(0);
		}
		
	}

}
