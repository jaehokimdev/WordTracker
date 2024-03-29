package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;
import exception.TreeException;
import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;
import wordDomain.Location;
import wordDomain.Word;

/**
 *  WordTracker manager
 * 
 * @author Jaeho Kim, Valentin Morales, Ho Chun Sun, Miller Gee
 * @version 1.1
 * Created: Nov 15, 2022
 * Updated: Dec 02, 2022
 * 
 */
public class WordTracker {
	
	// Constants
	private static final String serFile = "res/repository.ser";

	// Attributes
	private long populateFromFileTime, deserializeFromFileTime, serializeToFileTime;
	private String inputFileName;
	private String option;
	private String outputFileName;
	private int wordCount = 0;
	private int lineNumber = 0;
	private boolean existSerFile;
	private BSTree<Word> BSTreeWord = new BSTree<Word>();
	
	/**
	 * WordTracker with 2 parameters.
	 * 
	 * @param inputFile inputFile name
	 * @param options print options
	 * @throws TreeException if the root is empty.
	 * 
	 */
	public WordTracker(String inputFile, String options) throws TreeException {
		this.inputFileName = inputFile;
		this.option = options;
		
		deserializeWordQueueFromFile();
		printWords();
		serializeWordQueueToFile();
	}
	
	/**
	 * WordTracker with 3 parameters.
	 * 
	 * @param inputFile inputFile name
	 * @param options print options
	 * @param outputFile print output file name
	 * @throws TreeException if the root is empty.
	 * 
	 */
	public WordTracker(String inputFile, String options, String outputFile) throws TreeException {
		this.inputFileName = inputFile;
		this.option = options;
		this.outputFileName = outputFile;
		
		deserializeWordQueueFromFile();
		printWords();
		serializeWordQueueToFile();
		System.out.println("Result was saved in " + outputFile + ".");
	}
	
	/**
	 * Deserialize if ser file exists
	 * 
	 * @throws TreeException if the root is empty.
	 * 
	 */
	public void deserializeWordQueueFromFile() throws TreeException
	{
		long start, stop;
		start = System.currentTimeMillis();
		
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(serFile));
			
			@SuppressWarnings("rawtypes")
			ConcurrentLinkedQueue queue = 
				(ConcurrentLinkedQueue)ois.readObject();
			
			ois.close();

			while (queue.iterator().hasNext())
			{
				Word word = (Word) queue.poll();
				BSTreeWord.add(word);
				wordCount += word.getLocations().size();
			}
			System.out.println("repository.ser file found!");
			System.out.println("------------------------------------------------------------------------------");
			existSerFile = true;
			inputFileName = serFile;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("repository.ser file not found!");
			System.out.println("------------------------------------------------------------------------------");
			existSerFile = false;
			populateFromTextFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		deserializeFromFileTime = stop - start;
	}
	
	/**
	 * Populate from text file
	 * 
	 * @throws TreeException if the root is empty.
	 * 
	 */
	public void populateFromTextFile() throws TreeException {
		long start, stop;
		start = System.currentTimeMillis();
		
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
			System.out.println(inputFileName + " file not found!");
			System.exit(0);
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
		
		stop = System.currentTimeMillis();
		populateFromFileTime = stop - start;
	}
	
	/**
	 * 
	 * Print words on console
	 * 
	 */
	public void printWords() {
		Iterator<Word> it = BSTreeWord.inorderIterator();
		
		if (option.equals("-pf")) {
			System.out.println("Print in alphabetic order all words along with the corresponding list of files in which the words occur.");
			System.out.println("");
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-15s[File name: %s]\n", i + 1, word.getWord(), inputFileName);
			}
		} else if (option.equals("-pl")) {
			System.out.println("Print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur.");
			System.out.println("");
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-15s[File name: %s] [Line number: %s \n", i + 1, word.getWord(), inputFileName, word.getLocations());
			}
		}else {
			System.out.println("Print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.");
			System.out.println("");
			for (int i = 0; i < BSTreeWord.size(); i++) {
				Word word = it.next();
				
				System.out.printf("%-6d%-15s[File name: %s] [Line number: %s [OCCURRENCE: %d]\n", i + 1, word.getWord(), inputFileName, word.getLocations(), word.getLocations().size());
			}
		}
		
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("Total Words: " + wordCount);
		if (existSerFile) {
			System.out.println("Deserialized time: " + deserializeFromFileTime);
		} else {
			System.out.println("Populate time: " + populateFromFileTime);
		}
	}
	
	/**
	 * 
	 * Serialize BSTreeWord to ser file
	 * 
	 */
	public void serializeWordQueueToFile() {
		
		long start, stop;
		start = System.currentTimeMillis();

		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(serFile));
			ConcurrentLinkedQueue<Word> queue = 
									new ConcurrentLinkedQueue<Word>(); 
			
			Iterator<Word> it = BSTreeWord.inorderIterator();

			for(int i = 0; i < BSTreeWord.size(); i++)
			{
				Word word = it.next();
				queue.add(word);
			}
			oos.writeObject(queue);
			oos.close();
			
			System.out.println("Word BSTree was Serialized in res/repository.ser file.");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		stop = System.currentTimeMillis();
		serializeToFileTime = stop - start;
		System.out.println("Serialized time: " + serializeToFileTime);
	}
}
