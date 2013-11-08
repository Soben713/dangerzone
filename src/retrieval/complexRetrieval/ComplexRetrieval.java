package retrieval.complexRetrieval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import index.Index;
import index.PostingList;
import retrieval.Retrieval;

public class ComplexRetrieval extends Retrieval{
	private ArrayList<DocElement> docsVector=new ArrayList<DocElement>();
	private ArrayList<QueryElement> queryVector=new ArrayList<QueryElement>();
	public ComplexRetrieval(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}
	
	public void retrieve(int docsNum, String query){
		preprocess(docsNum, query);
		makeQueryVector();
		for(int i=0; i<queryVector.size(); i++){
			PostingList postingList=index.index.get(queryVector.get(i).name);
			for(int j=0; postingList!=null && j<postingList.size(); j++){
				int pl=postingList.get(j).docID;
				for(int k=docsVector.size(); docsVector.size()<=pl; k++){
					docsVector.add(new DocElement(k));
				}
				DocElement vector= docsVector.get(pl);
				double LNN=(1+Math.log10(postingList.get(j).tf));
				double LTN=(1+Math.log10(queryVector.get(i).tf))/Math.log10(postingList.size());
				vector.similarity+=LTN*LNN;
				docsVector.set(pl, vector);
			}
		}
        Collections.sort(docsVector,new Comparator<DocElement>() {
            public int compare(DocElement p1, DocElement p2) {
            	return (p2.similarity).compareTo(p1.similarity);
            	
            }
        });
        System.out.println("here  "+docsVector.size());
        outputWriter();
	}
	
	private void outputWriter() {
		for(int i=0; i<docsVector.size()&& i<docsNum && docsVector.get(i).similarity!=(double)0; i++){
			System.out.print(docsVector.get(i).ID+": "+docsVector.get(i).similarity+",  ");
		}
	}

	private void makeQueryVector() {
		ArrayList<Boolean> mark= new ArrayList<Boolean>();
		for(int i=0; i<queryTokens.size(); i++)
			mark.add(false);
		for(int i=0; i<queryTokens.size(); i++){
			int counter=0;
			for(int j=i; mark.get(j).equals(false) && j<queryTokens.size(); j++){
				if(queryTokens.get(i).equals(queryTokens.get(j)) ){
					counter++;
					mark.add(j, true);
				}		
			}
			queryVector.add(new QueryElement(queryTokens.get(i), counter));
		}
	}
	
}
