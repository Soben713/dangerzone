import java.io.FileNotFoundException;

import index.Index;

import retrieval.Manager;
import tokenizer.Tokenizer;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
		Index index = new Index("src/Data/Time Test Collection/Docs");
		index.printDictionary();
		System.out.println("man:  "+ index.index.get("man"));
		System.out.println("Time:  "+index.index.get("time"));
		System.out.println("fast:    "+index.index.get("fast"));
		Manager manager=new Manager(index);
		manager.retrieve(4, 300, " MAN  Time  fast fast ");
	}
}
