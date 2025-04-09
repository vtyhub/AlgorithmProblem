package bracketsMatchingWithPass;

import java.util.ArrayList;

public class Matching {

	public static boolean match(String s) {
		if (s == null || !s.matches("^[\\(\\)\\*]+$")) {
			return false;
		}
		ArrayList<String> leftlist = new ArrayList<String>(s.length());
		ArrayList<String> passlist = new ArrayList<String>(s.length());
		for (int i = 0; i < s.length(); i++) {
			String si = String.valueOf(s.charAt(i));
			if ("(".equals(si)) {
				leftlist.add(si);
			} else if (")".equals(si)) {
				if (leftlist.isEmpty()) {
					if (passlist.isEmpty()) {
						return false;
					} else {
						passlist.remove(passlist.size() - 1);
					}
				} else {
					leftlist.remove(leftlist.size() - 1);
				}
			} else {
				passlist.add(si);
			}
		}
		if (passlist.size() >= leftlist.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(match(""));
	}
}
