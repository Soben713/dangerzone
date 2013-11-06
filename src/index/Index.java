package index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import tokenizer.Tokenizer;

public class Index {
	public Map<String, PostingList> index = new HashMap<String, PostingList>();
	public int docsCounter = 0;

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
		while(scanner.hasNextLine())
			docString+= scanner.nextLine() + " \n";
		Tokenizer tokenizer = new Tokenizer(docString);
		while (tokenizer.hasNext()) {
			updatePostingList(tokenizer.next(), docID);
		}
	}

	public void printDictionary() {
		Set<String> dict = index.keySet();
		for(String term:dict)
			System.out.println(term);
	}

	// TODO
	public void printPostingList(String term) {
		System.out.println(index.get(term));
	}

	// TODO
	public Boolean save(String path) {
		return false;
	}

	// TODO
	public Boolean load(String path) {
		return false;
	}
}
