import java.io.FileNotFoundException;

import index.Index;

import tokenizer.Tokenizer;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
//		Index index = new Index("../Data/T")
//		Tokenizer tokenizer = new Tokenizer("salam soheil khoobi");
//		while(tokenizer.hasNext())
//			System.out.println(tokenizer.next());
		Index index = new Index("src/Data/Time Test Collection/Docs");
		index.printDictionary();
		index.printPostingList("their");
	}
}
