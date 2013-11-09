package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class SortHashMapByValue {
	public static <T, T2 extends Comparable> ArrayList<T> getKeysSorted(Map<T, T2> map) {
		ArrayList<Entry<T, T2>> list = new ArrayList<Entry<T, T2>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<T, T2>>() {
			public int compare(Entry<T, T2> o1,
					Entry<T, T2> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		ArrayList<T> ret = new ArrayList<T>();
		for(int i=0; i<list.size(); i++)
			ret.add(list.get(i).getKey());
		return ret;
	}
}
