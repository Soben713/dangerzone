package index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class PostingList extends ArrayList<Posting> implements Serializable {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret = this.size() + " - ";
		for (int i = 0; i < this.size(); i++) {
			ret += this.get(i).toString();
			if (i < this.size() - 1)
				ret += ", ";
		}
		return ret;
	}

	public boolean addDoc(int docID) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).docID == docID) {
				this.get(i).tf++;
				return true;
			}
		}
		this.add(new Posting(docID, 1));
		for (int i = size() - 1; i > 0 && get(i).lt(get(i - 1)); i--) {
			Collections.swap(this, i - 1, i);
		}
		return true;
	}
}
