package utils;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> {
	@Override
	public int compare(File arg0, File arg1) {
		int l1 = arg0.getName().length();
		int l2 = arg1.getName().length();
		if (l1 != l2)
			return Integer.compare(l1, l2);
		return arg0.getName().compareTo(arg1.getName());
	}
}
