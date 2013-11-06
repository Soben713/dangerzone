package tokenizer;

public class Tokenizer {
	private String document;
	private int pointer = 0;

	public Tokenizer(String document) {
		this.document = document;
	}

	public Boolean hasNext() {
		for (int i = pointer; i < document.length(); i++) {
			char c = document.charAt(i);
			if ((c <= 'Z' && c >= 'A') || ('a' <= c && c <= 'z')
					|| ('0' <= c && c <= '9')) {
				return true;
			}
		}
		return false;
	}

	public String next() {
		String nextToken = "";
		for (; pointer < document.length(); pointer++) {
			char c = document.charAt(pointer);
			if ((c >= 'A' && c <= 'Z') || ('a' <= c && c <= 'z')
					|| ('0' <= c && c <= '9')) {
				break;
			}
		}
		for (int i = pointer - 1; i >= 0 && document.charAt(i) == '.'; i--) {
			pointer--;
		}
		for (; pointer < document.length(); pointer++) {
			char c = document.charAt(pointer);

			if (c >= 'A' && c <= 'Z')
				nextToken += (char) (c + ('a' - 'A'));
			else if ((c >= 'a' && c <= 'z') || ('0' <= c && c <= '9')
					|| c == '.' || c == '@')
				nextToken += c;
			else {
				break;
			}
		}
		return nextToken;
	}
}
