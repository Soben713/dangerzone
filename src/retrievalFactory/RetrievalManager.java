package retrievalFactory;

import index.Index;
import retrieval.BooleanRetrieval;
import retrieval.CfRetrieval;
import retrieval.DfRetrieval;
import retrieval.LncLtcRetrieval;
import retrieval.LnnLtnRetrieval;
import retrieval.Retrieval;

public class RetrievalManager {
	Retrieval retrieval0, retrieval1, retrieval2, retrieval3, retrieval4;
	public RetrievalManager(Index index ){
        retrieval0=new BooleanRetrieval(index);
    	retrieval1=new DfRetrieval(index);
    	retrieval2=new CfRetrieval(index);
    	retrieval3=new LnnLtnRetrieval(index);
    	retrieval4=new LncLtcRetrieval(index);
	}
	public void retrieve(int model, int docsNum, String query ){
		switch(model) {
	    case 0:
	        retrieval0.retrieve(docsNum, query);
	        break;
	    case 1:
	    	retrieval1.retrieve(docsNum, query);
	        break;
	    case 2:
	    	retrieval2.retrieve(docsNum, query);
	    	break;
	    case 3:
	    	retrieval3.retrieve(docsNum, query);
	    	break;
	    case 4:
	    	retrieval4.retrieve(docsNum, query);
	    	break;
		}
	}
}
