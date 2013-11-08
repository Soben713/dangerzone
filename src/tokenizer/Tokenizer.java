package tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
	private String[] regex = {
			"(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", // Matches URL's
			"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", // Matches emails
			"[a-zA-Z0-9.][a-zA-Z0-9.]+", //Tokens with at least 2 characters
			"[a-zA-Z0-9]", //Tokens with only one character
	};
	private Pattern pattern;
	private Matcher matcher;
	int index = 0;

	public Tokenizer(String document) {
		String reg = "";
		for (int i = 0; i < regex.length; i++) {
			reg += regex[i];
			if (i != regex.length - 1)
				reg += "|";
		}
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(document);
	}

	public Boolean hasNext() {
		return matcher.find();
	}

	public String next() {
		return matcher.group().toLowerCase();
	}
}
