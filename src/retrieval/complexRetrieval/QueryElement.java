package retrieval.complexRetrieval;

public class QueryElement {
	public QueryElement(String name, double tfIdf) {
		this.name=name;
		this.tfIdf=tfIdf;
	}
	String name;
	double tfIdf;
	double lenght;
}


