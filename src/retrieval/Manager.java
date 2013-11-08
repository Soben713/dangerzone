package retrieval;

import index.Index;
import retrieval.complexRetrieval.LncLtcRetriever;
import retrieval.complexRetrieval.LnnLtnRetriever;
import retrieval.simpleRetrieval.BooleanRetriever;
import retrieval.simpleRetrieval.CfRetriever;
import retrieval.simpleRetrieval.DfRetriever;

public class Manager {
	Retriever retriever0, retriever1, retriever2, retriever3, retriever4;
	public Manager(Index index ){
        retriever0=new BooleanRetriever(index);
    	retriever1=new DfRetriever(index);
    	retriever2=new CfRetriever(index);
    	retriever3=new LnnLtnRetriever(index);
    	retriever4=new LncLtcRetriever(index);
	}
	public void retrieve(int model, int docsNum, String query ){
		switch(model) {
	    case 0:
	        retriever0.retrieve(docsNum, query);
	        break;
	    case 1:
	    	retriever1.retrieve(docsNum, query);
	        break;
	    case 2:
	    	retriever2.retrieve(docsNum, query);
	    	break;
	    case 3:
	    	retriever3.retrieve(docsNum, query);
	    	break;
	    case 4:
	    	retriever4.retrieve(docsNum, query);
	    	break;
		}
	}
}
