package manager;

public class WordTracker {
	private String inputFileName;
	private String option;
	private String outputFileName;
	
	public WordTracker(String inputFile, String options) {
		this.inputFileName = inputFile;
		this.option = options;
	}
	
	public WordTracker(String inputFile, String options, String outputFile) {
		this.inputFileName = inputFile;
		this.option = options;
		this.outputFileName = outputFile;
	}
	
	public void readWordFromText() {
		
	}
}
