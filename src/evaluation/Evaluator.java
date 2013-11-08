package evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import retrieval.Manager;
import retrieval.Query;

import utils.FileComparator;

import index.Index;
import index.PostingList;

public class Evaluator {
	ArrayList<Query> queries = new ArrayList<Query>();
	SortedMap<Integer, ArrayList<Integer>> relevantsOf = new TreeMap<Integer, ArrayList<Integer>>(); //Query index to relevants, 1based
	double[] recall = new double[6];
	double[] precision = new double[6];
	Manager retManager;

	public Evaluator(String docsPath, String queriesPath, String relevantsPath)
			throws FileNotFoundException {
		this.retManager = new Manager(docsPath);
		File queriesDir = new File(queriesPath);
		File[] queryFiles = queriesDir.listFiles();
		Arrays.sort(queryFiles, new FileComparator());
		for (File queryFile : queryFiles) {
			queries.add(new Query(queryFile));
		}

		Scanner rScanner = new Scanner(new File(relevantsPath));

		while (rScanner.hasNextLine()) {
			// TODO check out empty lines
			String line = rScanner.nextLine();
			String[] splitted = line.split(" ");
			int queryIndex = Integer.parseInt(splitted[0]) - 1;
			ArrayList<Integer> relevants = new ArrayList<Integer>();
			for (int i = 1; i < splitted.length; i++)
				relevants.add(Integer.parseInt(splitted[i]));
			relevantsOf.put(queryIndex,  relevants);
		}
	}

//	public void evaluate() {
//		for (Query query : queries) {
//			for (int i = 1; i <= 5; i++) {
//				ArrayList<Integer> retrieved = retManager.retrieve(i, 20, query);
//			}
//		}
//	}
//
//	public double getRecall(ArrayList<Integer> docs) {
//
//	}
}
