package index;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import tokenizer.Tokenizer;

public class Index implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SortedMap<String, PostingList> index = new TreeMap<String, PostingList>();
	public int docsCounter = 0;

	public Index() {
		// No argument constructor, mostly used to save/load data.
	}

	public Index(String path) throws FileNotFoundException {
		/*
		 * Index every file in the given @path
		 */
		File file = new File(path);
		if (file.isDirectory()) {
			// Index every file in the given path
			File[] docs = file.listFiles();
			for (int i = 0; i < docs.length; i++)
				indexDocument(docs[i]);
		} else {
			// Index the given document
			indexDocument(file);
		}
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

	// TODO
	public void printPostingList(String term) {
		System.out.println(index.get(term));
	}

	public Boolean save() {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"src/index/index.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			return false;
		}
		return true;
	}

	public Boolean load() {
		Index loadedIndex = null;
		try {
			FileInputStream fileIn = new FileInputStream("src/index/index.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			loadedIndex = (Index) in.readObject();
			in.close();
			fileIn.close();
			this.index = loadedIndex.index;
			this.docsCounter = loadedIndex.docsCounter;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return false;
		}
		return true;
	}
}
