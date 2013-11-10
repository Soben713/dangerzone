package tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.SortHashMapByValue;


public class Tokenizer {
	private String[] regex = {
			"(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", // Matches URL's
			"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", // Matches emails
			"[a-zA-Z0-9.][a-zA-Z0-9.]+", //Tokens with at least 2 characters
			"[a-zA-Z0-9]", //Tokens with only one character
	};
	private Pattern pattern;
	private Matcher matcher;
	int index = 0;
	Set<String> stopWords=new HashSet<String>();
	private int counter=0;
	ArrayList<String> tokens= new ArrayList<String>();
	
	public Tokenizer(String document){
		String reg = "";
		for (int i = 0; i < regex.length; i++) {
			reg += regex[i];
			if (i != regex.length - 1)
				reg += "|";
		}
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(document);
		preprocess();
	}
	
	protected void preprocess() {
		try{
			Scanner scanner = new Scanner(new File("src/Data/Time Test Collection/Stopword List/stopword"));
			while (scanner.hasNextLine()){
				String word=scanner.nextLine();
				stopWords.add(word.toLowerCase());
			}
			scanner.close();
			while(matcher.find()){
				String word=matcher.group().toLowerCase();
				if(!stopWords.contains(word)){
					tokens.add(word);
				}
			}
		} catch (IOException e) {
			System.err.println("NO STOPWORD");
		}
	}

	public Boolean hasNext() {
		if(counter<tokens.size())
			return true;
		return false;
	}

	public String next() {
		counter++;
		return tokens.get(counter-1);
	}
}