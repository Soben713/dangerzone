package retrieval;

import java.util.ArrayList;
import index.Index;
import index.PostingList;

public class BooleanRetrieval extends Retrieval{

	protected ArrayList<Integer> answer=new ArrayList<Integer>(); 	
	public BooleanRetrieval(Index index) {
		super(index);
	}
	public void retrieve(int docsNum, String query ){
		preProsess(docsNum, query);
	//	System.out.println("boolean"+ queryTokens.size());
		
		for(int i=0; i<(index.index.get(queryTokens.get(0))).size(); i++){
			answer.add(index.index.get(queryTokens.get(0)).get(i).docID);
		}
		ArrayList<Integer> newAnswer=new ArrayList<Integer>();
		for(int i=1; i<queryTokens.size(); i++){
			int k=0;
			newAnswer.clear();
			PostingList postingList=index.index.get(queryTokens.get(i));
			for(int j=0; postingList!=null && j<postingList.size(); j++){
				for(; k<answer.size() && answer.get(k)<postingList.get(j).docID; k++){}
				if(k==answer.size())
					break;
				if(answer.get(k)==postingList.get(j).docID)
					newAnswer.add(answer.get(k));
			}
			answer=newAnswer;
		}
		for(int i=0; i<answer.size(); i++){
			System.out.print(answer.get(i)+", ");
		}
	}
}
