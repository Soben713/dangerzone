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

	public Manager(String path) throws FileNotFoundException {
		Index index = null;
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

	public ArrayList<Integer> retrieve(int model, int docsNum, String query) {
		Query q = new Query(query);
		switch (model) {
		case 0:
			return retriever0.retrieve(docsNum, q);
		case 1:
			return retriever1.retrieve(docsNum, q);

		case 2:
			return retriever2.retrieve(docsNum, q);
		case 3:
			return retriever3.retrieve(docsNum, q);

		case 4:
			return retriever4.retrieve(docsNum, q);
		}
		return null;
	}
}
