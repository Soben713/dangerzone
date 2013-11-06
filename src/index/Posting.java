package index;

public class Posting {
	public int docID, tf;
	
	public Posting(int docID, int tf){
		this.docID = docID;
		this.tf = tf;
	}
	
	@Override
	public String toString() {
		return docID + " [" + tf + "]";
	}
	
	public boolean lt(Posting p){
		return docID < p.docID;
	}
}
