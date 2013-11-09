package index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import tokenizer.Tokenizer;
import utils.FileComparator;


public class Index implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SortedMap<String, PostingList> index = new TreeMap<String, PostingList>();
	public Map<String, Double> idf = new HashMap<String, Double>();
	public Map<Integer, Double> docVectorLen = new HashMap<Integer, Double>();
	public int docsCounter = 0;

	public Index(String path) throws FileNotFoundException {
		/*
		 * Index every file in the given @path
		 */
		File file = new File(path);
		if (file.isDirectory()) {
			// Index every file in the given path
			File[] docs = file.listFiles();
			Arrays.sort(docs, new FileComparator());
			for (int i = 0; i < docs.length; i++) {
				indexDocument(docs[i]);
			}
		} else {
			// Index the given document
			indexDocument(file);
		}
		
		double N = (double)(index.keySet().size());
		for(String term: index.keySet()){
			//Set idf
			double df = (double)(index.get(term).size());
			idf.put(term,  N/df);
			
			//Set docVectorLen
			for(int i=0; i<index.get(term).size(); i++){
				Posting p = index.get(term).get(i);
				if(docVectorLen.containsKey(p.docID))
					docVectorLen.put(p.docID, docVectorLen.get(p.docID) + Math.pow(p.tf, 2));
				else
					docVectorLen.put(p.docID, Math.pow(p.tf, 2));
			}
		}
		for(Integer docID: docVectorLen.keySet())
			docVectorLen.put(docID, Math.sqrt(docVectorLen.get(docID)));
	}

	public void updatePostingList(String token, int docID) {
		if (index.get(token) != null) {
			PostingList postingList = index.get(token);
			postingList.addDoc(docID);
		} else {
			PostingList pl = new PostingList();
			pl.addDoc(docID);
			index.put(token, pl);
		}
	}

	public void indexDocument(File document) throws FileNotFoundException {
		int docID = ++docsCounter;
		String docString = "";
		Scanner scanner = new Scanner(document);
		while (scanner.hasNextLine())
			docString += scanner.nextLine() + " \n";
		Tokenizer tokenizer = new Tokenizer(docString);
		while (tokenizer.hasNext()) {
			updatePostingList(tokenizer.next(), docID);
		}
	}

	public void printDictionary() {
		Set<String> dict = index.keySet();
		for (String term : dict)
			System.out.println(term);
	}

	public void printPostingList(String term) {
		System.out.println(index.get(term));
	}

	public static Boolean save(Index index, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(index);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			return false;
		}
		return true;
	}

	public static Index load(String path) {
		Index loadedIndex = null;
		try {
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedIndex = (Index) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
		}
		return loadedIndex;
	}
}
