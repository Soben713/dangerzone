package retrieval;

import index.Index;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import retrieval.vectorSpaceRetrieval.QueryVector;
import retrieval.vectorSpaceRetrieval.QueryVector;
import tokenizer.Tokenizer;

public class Query {
	public ArrayList<String> tokens = new ArrayList<String>();

	public Query(File queryFile) throws FileNotFoundException {
		String query = "";
		Scanner scanner = new Scanner(queryFile);
		while (scanner.hasNextLine())
			query += scanner.nextLine() + " ";
		scanner.close();
		Tokenizer tokenizer = new Tokenizer(query);
		while (tokenizer.hasNext())
			tokens.add(tokenizer.next());
	}

	public Query(String query) {
		Tokenizer tokenizer = new Tokenizer(query);
		while (tokenizer.hasNext())
			tokens.add(tokenizer.next());
	}
}
