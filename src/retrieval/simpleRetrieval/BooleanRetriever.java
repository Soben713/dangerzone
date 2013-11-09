package retrieval.simpleRetrieval;

import java.util.ArrayList;

import retrieval.Query;
import index.Index;

public class BooleanRetriever extends SimpleRetriever {

	protected ArrayList<Integer> answer = new ArrayList<Integer>();

	@Override
	public void updateDocsInfos(Query query){
		ArrayList<DocInfo> newDocsInfo = new ArrayList<DocInfo>();
		for(int i=0; i<docsInfo.size(); i++)
			if(docsInfo.get(i).info == query.tokens.size())
				newDocsInfo.add(docsInfo.get(i));
		this.docsInfo = newDocsInfo;
	}
	
	public BooleanRetriever(Index index) {
		super(index);
	}
}
