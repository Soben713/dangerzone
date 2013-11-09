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
	SortedMap<Integer, ArrayList<Integer>> relevantsOf = new TreeMap<Integer, ArrayList<Integer>>();
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
			if(line.length()==0)
				continue;
			String[] splitted = line.split(" +");
			int queryIndex = Integer.parseInt(splitted[0]) - 1;
			ArrayList<Integer> relevants = new ArrayList<Integer>();
			for (int i = 1; i < splitted.length; i++)
				relevants.add(Integer.parseInt(splitted[i]));
			relevantsOf.put(queryIndex, relevants);
		}
	}

	public void evaluate() {
		for (int qi = 0; qi<queries.size(); qi++) {
			Query query = queries.get(qi);
			for (int i = 0; i < 5; i++) {
				ArrayList<Integer> retrieved = retManager.retrieve(i, 20, query);
				updatePnR(i, retrieved, qi);
			}
		}
		for(int i=0; i<5; i++){
			recall[i] /= queries.size();
			precision[i] /= queries.size();
			System.out.println("Model " + i + ": R="+recall[i]+ " P=" + precision[i]);
		}
	}

	public void updatePnR(int model, ArrayList<Integer> docs, int qind) {
		if(!relevantsOf.containsKey(qind))
			return;
		int tp=0;
		for(int i=0; i<docs.size(); i++){
			if(relevantsOf.get(qind).contains(docs.get(i)))
				tp++;
		}
		if(relevantsOf.get(qind).size() > 0)
			recall[model] += (double)(tp) / (double)(relevantsOf.get(qind).size());
		else
			recall[model] += 1;
			
		if(docs.size() > 0)
			precision[model] += (double)(tp) / docs.size();
		else
			precision[model] += 1;
	}
}
