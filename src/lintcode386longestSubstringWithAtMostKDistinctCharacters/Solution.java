package lintcode386longestSubstringWithAtMostKDistinctCharacters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	/**
	 * @see https://www.lintcode.com/problem/longest-substring-with-at-most-k-distinct-characters/description
	 *      lintcode 386. 给定一个字符串，找到最多有k个不同字符的最长子字符串。
	 */

	// 超出内存限制
	// public static int lengthOfLongestSubstringKDistinct(String s, int k) {
	// String[] substrings = getAllSubstrings(s);
	// for (int i = substrings.length - 1; i >= 0; i--) {
	// if (getNumberOfDistinctCharacters(substrings[i]) <= k) {
	// return substrings[i].length();
	// }
	// }
	// return 0;
	// }

	// 超出时间限制，难道说要O(n)解法才可能通过
	// public static int lengthOfLongestSubstringKDistinct(String s, int k) {
	// if (s.length() == 0 || k == 0) {
	// return 0;
	// }
	// for (int i = s.length(); i >= 1; i--) {
	// // 本次循环找长度为i的串
	// int maxJ = getNumberOfSlidingWindow(s.length(), i);
	// for (int j = 0; j < maxJ; j++) {
	// String tmp = s.substring(j, j + i);
	// if (getNumberOfDistinctCharacters(tmp) <= k) {
	// return tmp.length();
	// }
	// }
	// }
	// return 0;
	// }

	/**
	 * 找到了窗口双指针的解法，不过这个和minsize不同，那个是想找出一个尽可能小的窗口，窗口越大越可能满足条件，这个则是要找出一个尽可能大的窗口，窗口越小越可能满足条件
	 * 左指针移动不是为了进行压力测试，让已经满足条件的窗口看看能不能在满足条件的前提下更小一点，而是为了让当前不满足条件的窗口变小一点，看看能不能满足条件
	 * 使用左右指针，不使用队列 在万级字符串输入下getNumberOfDistinctCharacters中的集合超出最大空间，改为调用默认构造器成功通过
	 * sb和左右指针的方式在十万级字符串的输入下依然超时，目测依然是getNumberOfDistinctCharacters，需要在找到窗口的同时检测出不同字符串长度
	 * 最终使用双指针+映射表，省去队列的空间，再度优化不知可否行通
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		int left = 0, result = 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>(100);// 存储每个字符串出现的次数
		for (int right = 0; right < s.length(); right++) {
			char cr = s.charAt(right);
			if (map.containsKey(cr)) {
				map.replace(cr, map.get(cr) + 1);
			} else {
				map.put(cr, 1);
				// 需要左指针移动
				while (map.size() > k) {
					char cl = s.charAt(left++);
					Integer count = map.get(cl);
					if (count == 1) {
						map.remove(cl, 1);
					} else {
						map.replace(cl, count - 1);
					}
				}
			}
			result = Math.max(right - left + 1, result);
		}
		return result;
	}

	/**
	 * 使用链表,不好统计元素内容，不如sb
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	// public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
	// int result = 0;
	// LinkedList<Character> list = new LinkedList<>();
	// for (int i = 0; i < s.length(); i++) {
	// list.addLast(s.charAt(i));
	// Character[] array = list.toArray(new Character[0]);
	// }
	// }

	/**
	 * 使用sb
	 * 
	 * @param s
	 * @param k
	 * @return
	 */
	public static int lengthOfLongestSubstringKDistinct3(String s, int k) {
		int result = 0;
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();// 存储每个字符串出现的次数
		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			// 加入队列
			sb.append(c);

			// 查看哈希表是否已经有该元素
			if (map.containsKey(c)) {
				map.replace(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}

			if (map.size() <= k) {
				result = Math.max(result, sb.toString().length());
			} else {
				while (map.size() > k) {
					char tmp = sb.charAt(0);

					sb.deleteCharAt(0);
					Integer count = map.get(tmp);
					if (count == 1) {
						map.remove(tmp, 1);
					} else {
						map.replace(tmp, count - 1);
					}

				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param lengthOfS
	 * @return
	 */
	public static int getNumberOfAllSubstringsNew(int lengthOfS) {
		if (lengthOfS <= 1) {
			throw new IllegalArgumentException("lengthOfs = " + lengthOfS + " <= 1");
		}
		return lengthOfS * (lengthOfS + 1) / 2;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String[] getAllSubstrings(String input) {
		ArrayList<String> result = new ArrayList<>(getNumberOfAllSubstringsNew(input.length()));
		for (int i = 1; i <= input.length(); i++) {
			// 本次循环找长度为i的串
			int maxJ = getNumberOfSlidingWindow(input.length(), i);
			for (int j = 0; j < maxJ; j++) {
				result.add(input.substring(j, j + i));
			}
		}
		return result.toArray(new String[0]);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static int getNumberOfDistinctCharacters(String input) {
		HashSet<Character> result = new HashSet<>();// 不能创建和input一样大的集合，会空间溢出
		for (int i = 0; i < input.length(); i++) {
			result.add(input.charAt(i));
		}
		return result.size();
	}

	public static int getNumberOfSlidingWindow(int len, int k) {
		if (len < 0) {
			throw new IllegalArgumentException("len = " + len + " < 0");
		}
		if (k < 0) {
			throw new IllegalArgumentException("k = " + k + " < 0");
		}
		return len >= k ? len - k + 1 : 1;
	}

	public static class StringWithDistinct {
		private StringBuilder ab;
		private HashMap<Character, Integer> map;

	}

	public static void main(String[] args) throws FileNotFoundException {
		String path = "C:\\Users\\SandyBridge\\Desktop\\13.in";
		Scanner scanner = new Scanner(new File(path));
		StringBuilder sb = new StringBuilder();
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine());
		}
		System.out.println(lengthOfLongestSubstringKDistinct(sb.toString(), 31));
		System.out.println(
				lengthOfLongestSubstringKDistinct("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11));

		scanner.close();
	}
}
