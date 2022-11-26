package wordDomain;

public class Location {
	private String fileName;
	private int lineNumber;
	
	public Location(String fileName, int lineNumber) {
		super();
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
