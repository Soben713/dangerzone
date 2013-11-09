package retrieval.vectorSpaceRetrieval;

import index.Index;
import index.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrieval.Query;
import retrieval.Retriever;

public class VectorSpaceRetriever extends Retriever {

	public VectorSpaceRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	class Pair implements Comparable<Pair> {
		int docID;
		double score;

		public Pair(int docID, double score) {
			this.docID = docID;
			this.score = score;
		}

		@Override
		public int compareTo(Pair o) {
			return Double.compare(this.score, o.score);
		}
		
		@Override
		public String toString() {
			return Integer.toString(docID) + " " + Double.toString(score);
		}
	}

	public void normalizeQuery(QueryVector qv) {

	}

	public void normalizeScores(Map<Integer, Pair> score) {

	}

	public ArrayList<Integer> retrieve(int docsNum, Query query) {
		QueryVector queryVector = new QueryVector(query, index);
		this.normalizeQuery(queryVector);

		Map<Integer, Pair> score = new HashMap<Integer, Pair>();

		for (int i = 0; i < query.tokens.size(); i++) {
			String token = query.tokens.get(i);
			if (index.index.containsKey(token))
				for (int j = 0; j < index.index.get(token).size(); j++) {
					Posting p = index.index.get(token).get(j);
					double prevScore = (score.containsKey(p.docID) ? score.get(p.docID).score : 0.0);
					double qw = queryVector.field.get(token);
					score.put(p.docID, new Pair(p.docID, 
							(1.0 + Math.log(p.tf)) * qw + prevScore));
				}
		}

		this.normalizeScores(score);

		ArrayList<Pair> mapValues = new ArrayList<Pair>(score.values());
		Collections.sort(mapValues);
		Collections.reverse(mapValues);

		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < docsNum && i < mapValues.size(); i++)
			ret.add(mapValues.get(i).docID);

		return ret;
	}

}
