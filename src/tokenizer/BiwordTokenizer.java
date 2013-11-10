package tokenizer;

import index.Posting;
import index.PostingList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import utils.FileComparator;
import utils.SortHashMapByValue;

public class BiwordTokenizer extends Tokenizer{
	public Map<String, Integer> biwordsFq;

	public BiwordTokenizer(String document) {
		super(document);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void preprocess(){
		biwordsFq = new HashMap<String, Integer>();
		super.preprocess();
		for(int i=0; i<tokens.size()-1; i++){
			int prev=0;
			if(biwordsFq.containsKey(tokens.get(i) + " " + tokens.get(i+1)))
				prev = biwordsFq.get(tokens.get(i) + " " + tokens.get(i+1));
			biwordsFq.put(tokens.get(i) + " " + tokens.get(i+1), prev+1);
		}
	}
	
	public static ArrayList<Entry<String, Integer>> getCommonBiwords(String path, int len) throws FileNotFoundException{
		File file = new File(path);
		File[] docs = file.listFiles();
		Arrays.sort(docs, new FileComparator());
		BiwordTokenizer[] t = new BiwordTokenizer[docs.length];
		Map<String, Integer> globalBiwordsFq = new HashMap<String, Integer>();

		for (int i = 0; i < docs.length; i++) {
			String docString = "";
			Scanner scanner = new Scanner(docs[i]);
			while (scanner.hasNextLine())
				docString += scanner.nextLine() + " \n";
			t[i] = new BiwordTokenizer(docString);
		}
		
		for (int i=0; i < docs.length; i++){
			for(String term: t[i].biwordsFq.keySet()){
				int prev = (globalBiwordsFq.get(term) == null ? 0 : globalBiwordsFq.get(term));
				globalBiwordsFq.put(term, t[i].biwordsFq.get(term) + prev);
			}
		}
		
		ArrayList<Entry<String, Integer>> ret = SortHashMapByValue.getSorted(globalBiwordsFq, len);
		return ret;
	}
	
	public static void printCommonBiwords(String path, int len) throws FileNotFoundException{
		ArrayList<Entry<String, Integer>> ret = BiwordTokenizer.getCommonBiwords(path,  len);
		for(int i=0; i<ret.size(); i++)
			System.out.println(ret.get(i).getKey() + ": " + ret.get(i).getValue());
	}
	
	public static void printBiwordsIndex(String path, int len) throws FileNotFoundException{
		File file = new File(path);
		File[] docs = file.listFiles();
		Arrays.sort(docs, new FileComparator());
		BiwordTokenizer[] t = new BiwordTokenizer[docs.length];
		Map<String, PostingList> globalBiwordsFq = new HashMap<String, PostingList>();

		for (int i = 0; i < docs.length; i++) {
			String docString = "";
			Scanner scanner = new Scanner(docs[i]);
			while (scanner.hasNextLine())
				docString += scanner.nextLine() + " \n";
			t[i] = new BiwordTokenizer(docString);
		}
		
		for (int i=0; i < docs.length; i++){
			for(String term: t[i].biwordsFq.keySet()){
				if(globalBiwordsFq.containsKey(term))
					globalBiwordsFq.get(term).add(new Posting(i+1, t[i].biwordsFq.get(term)));
				else{
					PostingList pl = new PostingList();
					pl.add(new Posting(i+1, t[i].biwordsFq.get(term)));
					globalBiwordsFq.put(term, pl);
				}
			}
		}
		
		ArrayList<Entry<String, PostingList>> ret = SortHashMapByValue.getSorted(globalBiwordsFq, len);
		for(int i=0; i<ret.size() && i<len; i++){
			System.out.println(ret.get(i).getKey() + ": " + ret.get(i).getValue());
		}
	}
}

