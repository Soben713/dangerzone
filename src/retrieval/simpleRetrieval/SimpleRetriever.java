package retrieval.simpleRetrieval;

import index.Index;
import index.PostingList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrieval.Retriever;

public class SimpleRetriever extends Retriever {

	protected ArrayList<DocInfo> docsInfo = new ArrayList<DocInfo>();

	public SimpleRetriever(Index index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	public void retrieve(int docsNum, String query) {
		preprocess(docsNum, query);
		for (int i = 0; i < queryTokens.size(); i++) {
			PostingList postingList = index.index.get(queryTokens.get(i));
			for (int j = 0; postingList != null && j < postingList.size(); j++) {
				int pl = postingList.get(j).docID;
				for (int k = docsInfo.size(); docsInfo.size() <= pl; k++) {
					docsInfo.add(new DocInfo(k, 0));
				}
				updateDocsInfo(pl, postingList.get(j).tf);
			}
		}
		Collections.sort(docsInfo, new Comparator<DocInfo>() {
			public int compare(DocInfo p1, DocInfo p2) {
				if ((p2.info).equals(p1.info))
					return (p1.docID).compareTo(p2.docID);
				return (p2.info).compareTo(p1.info);
			}
		});
		outputWriter();
	}

	protected void outputWriter() {
		for (int i = 0; i < docsNum && i < docsInfo.size()
				&& docsInfo.get(i).info != 0; i++) {
			System.out.print(docsInfo.get(i).docID + ": "
					+ docsInfo.get(i).info + ",  ");
		}
	}

	protected void updateDocsInfo(int pl, int tf) {
		docsInfo.set(pl, new DocInfo(pl, docsInfo.get(pl).info + 1));
	}
}