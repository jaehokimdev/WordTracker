package manager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.StringTokenizer;

import wordDomain.Word;

public class WordTracker {
	private String inputFileName;
	private String option;
	private String outputFileName;
	private static int N;
	
	public WordTracker(String inputFile, String options) {
		this.inputFileName = inputFile;
		this.option = options;
		
		populateFromTextFile(inputFile);
	}
	
	public WordTracker(String inputFile, String options, String outputFile) {
		this.inputFileName = inputFile;
		this.option = options;
		this.outputFileName = outputFile;
		populateFromTextFile(inputFile);
	}
	
	public static void deserializeWordFromFile()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream("res/repository.ser"));
			
			for(int i = 0; i < N; i++)
			{
				Word word = (Word)ois.readObject();
				System.out.println(word);
			}
			ois.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
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
		
			String line = file.readLine();
			
			StringTokenizer st = new StringTokenizer(line);
									
			while (st.hasMoreTokens()) {
				System.out.println(st.nextToken());
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
	
	
}
