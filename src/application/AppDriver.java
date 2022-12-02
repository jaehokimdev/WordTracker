package application;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import exception.TreeException;
import manager.*;

/**
 *  WordTracker Application
 * 
 * @author Jaeho Kim, Valentin Morales, Ho Chun Sun, Miller Gee
 * @version 1.1
 * Created: Nov 15, 2022
 * Updated: Dec 02, 2022
 * 
 */
public class AppDriver {
	
	/**
	 * Main of Application.
	 * @throws TreeException if the root is empty.
	 * 
	 */
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
			
			class DualStream extends PrintStream {
			    public PrintStream consoleOutput = null;
			    public PrintStream fileOutput = null;

			    public DualStream(final PrintStream consoleOutput, final PrintStream fileOutput) throws FileNotFoundException {
			        super(fileOutput, true);
			        this.consoleOutput = consoleOutput;
			        this.fileOutput = fileOutput;
			    }

			    @Override
			    public void println(final String output) {
			        consoleOutput.println(output);
			        super.println(output);
			    }

			    @Override
			    public PrintStream printf(final String output, final Object... variables) {
			        consoleOutput.printf(output, variables);
			        super.printf(output, variables);
			        return this;
			    }
			}
			
			File file = new File(outputFileName);		    
		    PrintStream CombinedOutput = null;
		    try {
		        CombinedOutput = new DualStream(System.out, new PrintStream(new BufferedOutputStream(new FileOutputStream(file))));
		    } catch (final FileNotFoundException e) {
		        e.printStackTrace();
		    }
		    System.setOut(CombinedOutput);
			new WordTracker(inputFileName, option, outputFileName);
							       
		} else {
			System.out.println("Input method is invalid. Try again.");
			System.exit(0);
		}
		
	}

}
