package retrieval.vectorSpaceRetrieval;

import index.Index;
import java.util.Map;

public class LNCLTCRetriever extends VectorSpaceRetriever {

	public LNCLTCRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	public void normalizeQuery(QueryVector qv) {
		qv.normalize();
	}

	public void normalizeScores(Map<Integer, Double> score) {
		for (int docID : score.keySet()) {
			score.put(docID, score.get(docID) / index.docVectorLen.get(docID));
		}
	}
}
