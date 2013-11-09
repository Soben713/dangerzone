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

	public void normalizeScores(Map<Integer, Pair> score) {
		for (int docID : score.keySet()) {
			score.put(docID, new Pair(docID, score.get(docID).score / index.docVectorLen.get(docID)));
		}
	}
}
