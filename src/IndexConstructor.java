import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Collections;


public class IndexConstructor {
	
	
	
	public static void main(String args[]) throws IOException{
		IndexConstructor indexer= new IndexConstructor();
		indexer.index("/home/mahsa/workspace/dangerzone/Data/Time Test Collection/Docs");
	}

	// TODO
	public void index(String path) throws IOException {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        fileNames.add(file.getName());
		    }
		}
		
		for(int i=0; i<fileNames.size(); i++){
			int c=0;
			String input="", output="";
			String inputPath=path+ "/" +fileNames.get(i);
			String outputPath="/home/mahsa/workspace/dangerzone/Data/Time Test Collection/Tokenized/"+fileNames.get(i);
			FileWriter writer = new FileWriter(outputPath);
			BufferedReader reader = new BufferedReader(new FileReader(inputPath));
			
			String line = null;
			while ((line = reader.readLine()) != null) {
			    input+=line+'\n';
			}
			reader.close();
			Tokenizer tokenizer= new Tokenizer(input);
			while(tokenizer.hasNext()){
				output+=tokenizer.next()+'\n';
			}
			writer.write(output);
			writer.close();
		}
	}
	
	// TODO
	public void printDictionary() {

	}

	// TODO
	public void printPostingList(String term) {

	}

	// TODO
	public Boolean save(String path) {
		return false;
	}

	// TODO
	public Boolean load(String path) {
		return false;
	}
}
