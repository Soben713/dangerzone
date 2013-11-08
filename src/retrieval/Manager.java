package retrieval;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import index.Index;
import retrieval.complexRetrieval.LncLtcRetriever;
import retrieval.complexRetrieval.LnnLtnRetriever;
import retrieval.simpleRetrieval.BooleanRetriever;
import retrieval.simpleRetrieval.CfRetriever;
import retrieval.simpleRetrieval.DfRetriever;

public class Manager {
	Retriever retriever0, retriever1, retriever2, retriever3, retriever4;
	public Manager(String path ) throws FileNotFoundException{
		Index index = null;
		File file = new File(path);
		if (file.isDirectory()) {
			index=new Index(path);
		}
		else{
			index=Index.load(path);
		}
        retriever0=new BooleanRetriever(index);
    	retriever1=new DfRetriever(index);
    	retriever2=new CfRetriever(index);
    	retriever3=new LnnLtnRetriever(index);
    	retriever4=new LncLtcRetriever(index);
	}
	public ArrayList<Integer> retrieve(int model, int docsNum, String query ){
		switch(model) {
	    case 0:
	        return retriever0.retrieve(docsNum, query);
	    case 1:
	    	return retriever1.retrieve(docsNum, query);
	      
	    case 2:
	    	return retriever2.retrieve(docsNum, query);
	    case 3:
	    	return retriever3.retrieve(docsNum, query);
	    
	    case 4:
	    	return retriever4.retrieve(docsNum, query);
	   
		}
		return null;
	}
}
