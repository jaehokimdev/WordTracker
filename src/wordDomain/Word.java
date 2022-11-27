package wordDomain;

import java.util.LinkedList;

public class Word {
	private String word;
	private LinkedList<Location> locations;
	
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
	
	public int compareTo(Word otherWord) {
		
		if (this.word.compareToIgnoreCase(otherWord.getWord()) > 0) {
			return 1;
		} else if (this.word.equalsIgnoreCase(otherWord.getWord())) {
			return 0;
		} else {
			return -1;
		}
	}
}
