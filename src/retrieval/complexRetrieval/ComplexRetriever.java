package retrieval.complexRetrieval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import index.Index;
import index.PostingList;
import retrieval.Retriever;

public class ComplexRetriever extends Retriever {
	protected ArrayList<DocElement> docsVector = new ArrayList<DocElement>();
	protected ArrayList<QueryElement> queryVector = new ArrayList<QueryElement>();
	protected double queryLength=(double)1;

	public ComplexRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Integer>  retrieve(int docsNum, String query) {
		preprocess(docsNum, query);
		makeQueryVector();
		for (int i = 0; i < queryVector.size(); i++) {
			PostingList postingList = index.index.get(queryVector.get(i).name);
			for (int j = 0; postingList != null && j < postingList.size(); j++) {
				int docID = postingList.get(j).docID;
				for (int k = docsVector.size(); docsVector.size() <= docID; k++) {
					docsVector.add(new DocElement(k));
				}
				DocElement element = docsVector.get(docID);
				double LN = (1 + Math.log10(postingList.get(j).tf));
				element.similarity += queryVector.get(i).tfIdf * LN;
				element.Sqrlenght+=Math.pow(LN, 2);
				docsVector.set(docID, element);
			}
		}
		normalize();
		Collections.sort(docsVector, new Comparator<DocElement>() {
			public int compare(DocElement p1, DocElement p2) {
				return (p2.similarity).compareTo(p1.similarity);

			}
		});
		return outputMaker();

	}

	protected void normalize() {
		//Nothing
	}

	private ArrayList<Integer> outputMaker() {
		ArrayList<Integer> output=new ArrayList<Integer> ();
		for (int i = 0; i < docsVector.size() && i < docsNum
				&& docsVector.get(i).similarity != (double) 0; i++) {
					output.add(docsVector.get(i).ID);
		}
		return output;
	}

	private void makeQueryVector() {
		ArrayList<Boolean> mark = new ArrayList<Boolean>();
		for (int i = 0; i < queryTokens.size(); i++)
			mark.add(false);
		for (int i = 0; i < queryTokens.size(); i++) {
			int counter = 0;
			if(mark.get(i).equals(true))
				continue;
			for (int j = i; j < queryTokens.size(); j++) {
				if (queryTokens.get(i).equals(queryTokens.get(j))) {
					counter++;
					mark.add(j, true);
				}
			}
			double df=index.index.get(queryTokens.get(i)).size();
			double lIdf=Math.log10(index.docsCounter/df);
			queryVector.add(new QueryElement(queryTokens.get(i), (1 + Math.log10(counter))* lIdf ));
		}
		makeQueryLength();
	}

	protected void makeQueryLength() {
		for(int i=0; i<this.queryVector.size(); i++){
			this.queryLength+=Math.pow(this.queryVector.get(i).tfIdf, 2);
		}
		this.queryLength=Math.sqrt(this.queryLength);
		System.out.println("qyerylength:  "+queryLength);
	}
}
