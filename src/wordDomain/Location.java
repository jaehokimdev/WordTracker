package wordDomain;

import java.io.Serializable;

/**
 *  Location class
 * 
 * @author Jaeho Kim, Valentin Morales, Ho Chun Sun, Miller Gee
 * @version 1.1
 * Created: Nov 15, 2022
 * Updated: Dec 02, 2022
 * 
 */
public class Location implements Serializable{
	private static final long serialVersionUID = 103125396562901601L;
	private String fileName;
	private int lineNumber;
	
	public Location() {
	}
	
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
	
	public String toString() {
		return " " + lineNumber;
		
	}
}
