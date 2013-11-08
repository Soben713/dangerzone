package retrieval;

import index.Index;

import index.PostingList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import tokenizer.Tokenizer;

public abstract class Retriever {
	protected int docsNum;
	protected String query;
	protected Index index;
	protected ArrayList<String> queryTokens=new ArrayList<String>(); 

	public Retriever(Index index) {
		this.index=index;
	}
	
	protected void preprocess(int docsNum, String query) {
		this.docsNum=docsNum;
		this.query=query;
		tokenize();
		//System.out.println("query:  "+queryTokens.size());
	}
	
	private void tokenize(){
		Tokenizer tokenizer= new Tokenizer(query);
		while(tokenizer.hasNext()){
			queryTokens.add(tokenizer.next());	
		}
	}

	public ArrayList<Integer>  retrieve(int docsNum2, String query2) {
		return null;
		// TODO Auto-generated method stub
	}
}
