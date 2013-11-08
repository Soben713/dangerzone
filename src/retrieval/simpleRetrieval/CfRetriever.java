package retrieval.simpleRetrieval;
import index.Index;

public class CfRetriever extends SimpleRetriever{

	public CfRetriever(Index index) {
		super(index);
	}
	public void updateDocsInfo(int pl, int tf){
		docsInfo.set(pl, new DocInfo(pl, docsInfo.get(pl).info+tf));
	}
	/*
	public void retrieve(int docsNum, String query){
		preProsess(docsNum, query);
		for(int i=0; i<queryTokens.size(); i++){
			PostingList postingList=index.index.get(queryTokens.get(i));
			for(int j=0; postingList!=null && j<postingList.size(); j++){
				int pl=postingList.get(j).docID;
				for(int k=answer.size(); answer.size()<=pl; k++){
					answer.add(new Pair(k,0));
				}
				answer.set(pl, new Pair(pl, answer.get(pl).second+postingList.get(j).tf));
			}
		}
        Collections.sort(answer,new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return (p2.second).compareTo(p1.second);
            }
        });
        for(int i=0; i<docsNum && i<answer.size() && answer.get(i).second!=0; i++){	
        	System.out.print(answer.get(i).first+", ");
        }
	} */

}
