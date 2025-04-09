package subStringsFromLoopString;

import java.util.ArrayList;

public class SubStrings {

	public static ArrayList<String> getSubStringsRemoval(String src, String loopstring) {
		ArrayList<String> list = new ArrayList<String>();
		for (int l = 1; l <= src.length(); l++) {
			for (int i = 0; i < src.length() + 1 - l; i++) {
				String sub = src.substring(i, i + l);
				if (loopstring.contains(sub) && !list.contains(sub)) {
					list.add(sub);
				}
			}
		}
		return list;
	}

	public static ArrayList<String> getSubStrings(String src, String loopstring) {
		ArrayList<String> list = new ArrayList<String>();
		for (int l = 1; l <= src.length(); l++) {
			for (int i = 0; i < src.length() + 1 - l; i++) {
				System.out.println("l =" + l + "  i =" + i);
				String sub = src.substring(i, i + l);
				if (loopstring.contains(sub)) {
					list.add(sub);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		String loop = LoopString.genLoopString(100, 0, LoopString.LOWERCASE);
		System.out.println(loop);
		ArrayList<String> list = getSubStrings("cac", loop);
		ArrayList<String> listre = getSubStringsRemoval("cac", loop);
		System.out.println(list);
		System.out.println(listre);
	}
}
