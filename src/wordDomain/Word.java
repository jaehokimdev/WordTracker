package wordDomain;

import java.io.Serializable;
import java.util.LinkedList;

public class Word implements Comparable<Word>, Serializable{
	private static final long serialVersionUID = 1L;
	
	private String word;
	private LinkedList<Location> locations;
	
	public Word() {
	}
	
	public Word(String word, LinkedList<Location> locations) {
		this.word = word;
		this.locations = locations;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public LinkedList<Location> getLocations() {
		return locations;
	}
	public void setLocations(LinkedList<Location> locations) {
		this.locations = locations;
	}
	
	public int compareTo(Word other) {
		
		if (this.word.compareToIgnoreCase(other.getWord()) > 0) {
			return 1;
		} else if (this.word.equalsIgnoreCase(other.getWord())) {
			return 0;
		} else {
			return -1;
		}
	}
}
