package retrieval.vectorSpaceRetrieval;

import index.Index;

import java.util.HashMap;
import java.util.Map;
import retrieval.Query;


public class QueryVector{
	Map<String, Double> field = new HashMap<String, Double>();
	
	public QueryVector(Query q, Index index){
		for(String term: q.tokens){
			if(this.field.containsKey(term))
				this.field.put(term, this.field.get(term) + 1);
			else
				this.field.put(term, 1.0);
		}
		for(String term: field.keySet()){
			double tf = 1.0 + Math.log(this.field.get(term));
			double idf;
			if(index.idf.containsKey(term))
				idf = index.idf.get(term);
			else 
				idf = index.index.keySet().size(); // not really important
			this.field.put(term,  tf*idf);
		}
	}
	
	public void normalize(){
		for(String term: field.keySet())
			field.put(term, field.get(term) / this.length());
	}
	
	public double length(){
		double ret = 0;
		for(String key: field.keySet()){
			ret+=Math.pow(field.get(key), 2);
		}
		ret=Math.sqrt(ret);
		return ret;
	}
}
