package retrieval;

import index.Index;

import java.util.ArrayList;

public abstract class Retriever {
	protected Index index;

	public Retriever(Index index) {
		this.index = index;
	}

	public abstract ArrayList<Integer> retrieve(int docsNum, Query query);
}
