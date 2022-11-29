package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.StringTokenizer;
import exception.TreeException;
import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;
import wordDomain.Location;
import wordDomain.Word;

public class WordTracker {
	private String inputFileName;
	private String option;
	private String outputFileName;
	private int wordCount = 0;
	private int lineNumber = 0;
	private static int N;
	private BSTree<Word> BSTreeWord = new BSTree<Word>();
	private static final String serFile = "res/repository.ser";
	
	public WordTracker(String inputFile, String options) throws TreeException {
		this.inputFileName = inputFile;
		this.option = options;
		
		deserializeFromFile();
		populateFromTextFile();
		printWords();
	}
	
	public WordTracker(String inputFile, String options, String outputFile) throws TreeException {
		this.inputFileName = inputFile;
		this.option = options;
		this.outputFileName = outputFile;
		
		deserializeFromFile();
		populateFromTextFile();
	}
	
	public static void deserializeFromFile()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(serFile));
			
			for(int i = 0; i < N; i++)
			{
				Word word = (Word)ois.readObject();
				System.out.println(word);
			}
			ois.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("repository.ser file not found");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void populateFromTextFile() throws TreeException {
		try {
			BufferedReader file = new BufferedReader(new FileReader(inputFileName));
			
			String str = "";
			
			while ((str = file.readLine()) != null) {
				if (!str.equals("")) {
					lineNumber++;
				}
				str = str.replaceAll("'", "");
				StringTokenizer st = new StringTokenizer(str, "?()!,.;\"- ");
				
				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					LinkedList<Location> location = new LinkedList<>();
					location.add(new Location(inputFileName, lineNumber));
					Word newWord = new Word(word, location);
					
					if (BSTreeWord.contains(newWord)) {
						BSTreeNode<Word> existBSTree = BSTreeWord.search(newWord);
						LinkedList<Location> existlocation = existBSTree.getElement().getLocations();
						existlocation.add(new Location(inputFileName, lineNumber));
					} else {
						BSTreeWord.add(newWord);
					}
				
					wordCount++;
				}
			}

			file.close();
		
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
	}
	
	public void printWords() {
		Iterator<Word> it = BSTreeWord.inorderIterator();
		
		if (option.equals("-pf")) {
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-13s[File name: %s]\n", i, word.getWord(), word.getLocations().get(0).getFileName());
			}
		} else if (option.equals("-pl")) {
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-13s%s\n", i, word.getWord(), word.getLocations());
			}
		}else {
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-13s%s OCCURRENCE: %-3d\n", i, word.getWord(), word.getLocations(), word.getLocations().size());
			}
		}
		
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("Total Line: " + lineNumber);
		System.out.println("Total Words: " + wordCount);
	}
	
	
}
