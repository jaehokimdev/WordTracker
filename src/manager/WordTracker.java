package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import utilities.BSTree;
import wordDomain.Location;
import wordDomain.Word;

public class WordTracker {
	private String inputFileName;
	private String option;
	private String outputFileName;
	private static int N;
	private BSTree<Word> BSTreeWord = new BSTree<Word>();
	private static final String serFile = "res/repository.ser";
	
	public WordTracker(String inputFile, String options) {
		this.inputFileName = inputFile;
		this.option = options;
		
		deserializeFromFile();
		populateFromTextFile(inputFile);
	}
	
	public WordTracker(String inputFile, String options, String outputFile) {
		this.inputFileName = inputFile;
		this.option = options;
		this.outputFileName = outputFile;
		
		deserializeFromFile();
		populateFromTextFile(inputFile);
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
	
	public void populateFromTextFile(String inputFile) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(inputFile));
			
			String str = "";
			
			int wordCount = 0;
						
			int lineNumber = 0;
			
			while ((str = file.readLine()) != null) {
				if (!str.equals("")) {
					lineNumber++;
				}
				str = str.replaceAll("'", "");
				StringTokenizer st = new StringTokenizer(str, "!,.;\"- ");
				
				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					LinkedList<Location> location = new LinkedList<>();
					location.add(new Location(inputFile, lineNumber));
					Word newWord = new Word(word, location);
					BSTreeWord.add(newWord);
				
					wordCount++;
				}
			}

			System.out.println(lineNumber);
			System.out.println(wordCount);
	
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
	
	
}
