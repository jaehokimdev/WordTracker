package application;

import manager.*;

public class AppDriver {

	public static void main(String[] args) {
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
			new WordTracker(inputFileName, option, outputFileName);
		} else {
			System.out.println("Input method is invalid. Try again.");
			System.exit(0);
		}
		
	}

}
