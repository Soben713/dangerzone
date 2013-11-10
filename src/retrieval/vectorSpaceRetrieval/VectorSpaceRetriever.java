package retrieval.vectorSpaceRetrieval;

import index.Index;
import index.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrieval.Query;
import retrieval.Retriever;
import utils.SortHashMapByValue;

public class VectorSpaceRetriever extends Retriever {

	public VectorSpaceRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	public void normalizeQuery(QueryVector qv) {

	}

	public void normalizeScores(Map<Integer, Double> score) {

	}

	public ArrayList<Integer> retrieve(int docsNum, Query query) {
		QueryVector queryVector = new QueryVector(query, index);
		this.normalizeQuery(queryVector);

		Map<Integer, Double> score = new HashMap<Integer, Double>();

		for (int i = 0; i < query.tokens.size(); i++) {
			String token = query.tokens.get(i);
			if (index.index.containsKey(token))
				for (int j = 0; j < index.index.get(token).size(); j++) {
					Posting p = index.index.get(token).get(j);
					double prevScore = (score.containsKey(p.docID) ? score.get(p.docID) : 0.0);
					double qw = queryVector.field.get(token);
					score.put(p.docID, (1.0 + Math.log10(p.tf)) * qw + prevScore);
				}
		}

		this.normalizeScores(score);

		ArrayList<Integer> docs = SortHashMapByValue.getKeysSorted(score, docsNum);
		return docs;
	}

}
