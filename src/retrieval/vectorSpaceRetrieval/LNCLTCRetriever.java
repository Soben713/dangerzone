package retrieval.vectorSpaceRetrieval;

import index.Index;
import index.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrieval.Query;
import retrieval.Retriever;
import retrieval.vectorSpaceRetrieval.LNNLTNRetriever.Pair;

public class LNCLTCRetriever extends Retriever{

	public LNCLTCRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	class Pair implements Comparable<Pair>{
		int docID;
		double score;
		
		public Pair(int docID, double score){
			this.docID = docID;
			this.score = score;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Double.compare(this.score, o.score);
		}
	}
	
	public ArrayList<Integer> retrieve(int docsNum, Query query) {
		QueryVector queryVector = new QueryVector(query, index);
		queryVector.normalize();
		Map<Integer, Pair> score = new HashMap<Integer, Pair>();
		
		for(int i=0; i<query.tokens.size(); i++){
			String token = query.tokens.get(i);
			for(int j=0; i<index.index.get(token).size(); i++){
				Posting p = index.index.get(token).get(j);
				double prevScore = (score.containsKey(p.docID) ? score.get(p.docID).score : 0.0);
				score.put(p.docID, new Pair(p.docID, 1.0 + Math.log(p.tf) + prevScore));
			}
		}
		for(int docID: score.keySet()){
			score.put(docID, new Pair(docID, score.get(docID).score / index.docVectorLen.get(docID)));
		}
		
		ArrayList<Pair> mapValues = new ArrayList<Pair>(score.values());
		Collections.sort(mapValues);
		Collections.reverse(mapValues);
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i=0; i<docsNum; i++)
			ret.add(mapValues.get(i).docID);
		
		return ret;
	}

}
