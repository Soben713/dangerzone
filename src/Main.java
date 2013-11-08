import java.io.FileNotFoundException;

import index.Index;

import retrievalFactory.RetrievalManager;
import tokenizer.Tokenizer;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
//		Index index = new Index("../Data/T")
//		Tokenizer tokenizer = new Tokenizer("salam soheil khoobi");
//		while(tokenizer.hasNext())
//			System.out.println(tokenizer.next());
		Index index = new Index("src/Data/Time Test Collection/Docs");
		index.printDictionary();
		System.out.println("man:  "+ index.index.get("man"));
		System.out.println("Time:  "+index.index.get("time"));
		System.out.println("fast:    "+index.index.get("fast"));
		RetrievalManager manager=new RetrievalManager(index);
		manager.retrieve(3, 300, " man Time fast");
	}
}
