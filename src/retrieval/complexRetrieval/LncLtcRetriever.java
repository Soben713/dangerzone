package retrieval.complexRetrieval;


import index.Index;

public class LncLtcRetriever extends ComplexRetriever{

	public LncLtcRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}
	protected void normalize() {
		for(int i=0; i<docsVector.size(); i++){
			DocElement element= docsVector.get(i);
			element.similarity=element.similarity/(Math.sqrt(element.Sqrlenght) * this.queryLength);
			docsVector.set(i,element);
			if(i==273 || i==230){
				System.out.println("DocLength"+i+": "+Math.sqrt(docsVector.get(i).Sqrlenght));
			}
		}
	}
}
