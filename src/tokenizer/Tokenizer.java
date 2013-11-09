package tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
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
	private static final int MAXBIWORDS = 1000;
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
	Map<String, Integer> biwordsFq = new HashMap<String, Integer>();
	ArrayList<String> commonBiwords = new ArrayList<String>();
	
	public Tokenizer(String document, boolean biwords){
		String reg = "";
		for (int i = 0; i < regex.length; i++) {
			reg += regex[i];
			if (i != regex.length - 1)
				reg += "|";
		}
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(document);
		preprocess(biwords);
	}
	
	public Tokenizer(String document){
		this(document, false);
	}

	private void preprocess(boolean biwords) {
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
			for(int i=0; i<tokens.size()-1; i++){
				Integer prev = biwordsFq.get(tokens.get(i) + " " + tokens.get(i+1));
				prev = (prev == null ? 0 : prev);
				biwordsFq.put(tokens.get(i) + " " + tokens.get(i+1), prev+1);
			}
//	        commonBiwords = SortHashMapByValue.getKeysSorted(biwordsFq).subList(0, MAXBIWORDS);
	        
		} catch (Exception e) {
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