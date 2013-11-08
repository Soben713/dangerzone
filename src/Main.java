import java.io.FileNotFoundException;

import index.Index;

import retrieval.Manager;
import tokenizer.Tokenizer;

public class Main {
	public static void main(String args[]) throws FileNotFoundException{
		Index index = new Index("src/Data/Time Test Collection/Docs");
		Index.save(index, "src/Data/Time Test Collection/save");
		index.printDictionary();
		System.out.println("man:  "+ index.index.get("man"));
		System.out.println("Time:  "+index.index.get("time"));
		System.out.println("fast:    "+index.index.get("fast"));
		Manager manager=new Manager("src/Data/Time Test Collection/save");
		System.out.print(manager.retrieve(2, 300, "KENNEDY ADMINISTRATION PRESSURE ON NGO DINH DIEM TO STOP SUPPRESSING THE BUDDHISTS ."));
	}
}
