import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import evaluation.Evaluator;

import index.Index;

import retrieval.Manager;
import tokenizer.BiwordTokenizer;
import tokenizer.Tokenizer;
import utils.SortHashMapByValue;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
//		Index index = new Index("src/Data2/Docs");
//		System.out.println(index.idf);
//		index.printDictionary();
//		Manager manager=new Manager("src/Data2/Docs");
//		System.out.println(manager.index.idf);
//		for(int i=0; i<5; i++)
//			System.out.println(manager.retrieve(i, 20, "agha soheil salam"));
		String pre = "src/Data/Time Test Collection";
		Evaluator eval = new Evaluator(pre+"/Docs", pre+"/Queries", pre+"/Relevancy Judgments/relevance");
		eval.evaluate();
//		Index index = new Index(pre + "/Docs");
//		BiwordTokenizer bt = new BiwordTokenizer("salam soheil xubi");
//		System.out.println(bt.biwordsFq);
//		BiwordTokenizer.printBiwordsIndex(pre+"/Docs", 10);
	}
}
