package retrieval.simpleRetrieval;

import java.util.ArrayList;
import index.Index;

public class BooleanRetriever extends SimpleRetriever {

	protected ArrayList<Integer> answer = new ArrayList<Integer>();

	public BooleanRetriever(Index index) {
		super(index);
	}
}
