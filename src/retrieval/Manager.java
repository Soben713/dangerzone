package retrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import index.Index;
import retrieval.simpleRetrieval.BooleanRetriever;
import retrieval.simpleRetrieval.CfRetriever;
import retrieval.simpleRetrieval.DfRetriever;
import retrieval.vectorSpaceRetrieval.LNCLTCRetriever;
import retrieval.vectorSpaceRetrieval.LNNLTNRetriever;

public class Manager {
	Retriever retriever0, retriever1, retriever2, retriever3, retriever4;
	public Index index;

	public Manager(String path) throws FileNotFoundException {
		index = null;
		File file = new File(path);
		if (file.isDirectory()) {
			index = new Index(path);
		} else {
			index = Index.load(path);
		}
		retriever0 = new BooleanRetriever(index);
		retriever1 = new DfRetriever(index);
		retriever2 = new CfRetriever(index);
		retriever3 = new LNNLTNRetriever(index);
		retriever4 = new LNCLTCRetriever(index);
	}

	public ArrayList<Integer> retrieve(int model, int docsNum, Query query) {
		switch (model) {
		case 0:
			return retriever0.retrieve(docsNum, query);
		case 1:
			return retriever1.retrieve(docsNum, query);

		case 2:
			return retriever2.retrieve(docsNum, query);
		case 3:
			return retriever3.retrieve(docsNum, query);

		case 4:
			return retriever4.retrieve(docsNum, query);
		}
		return null;
	}
	
	public ArrayList<Integer> retrieve(int model, int docsNum, String query) {
		Query q = new Query(query);
		return retrieve(model, docsNum, q);
	}
}
