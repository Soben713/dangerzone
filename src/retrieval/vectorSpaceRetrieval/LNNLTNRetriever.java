package retrieval.vectorSpaceRetrieval;

import index.Index;
import index.Posting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrieval.Query;
import retrieval.Retriever;

public class LNNLTNRetriever extends VectorSpaceRetriever{

	public LNNLTNRetriever(Index index) {
		super(index);
	}

}
