package kmp;

import java.util.ArrayList;

public class KMP {

	/**
	 * abcabc作为参数结果为{-1,0,0,0,1,2,3},为了在KMP中使用,前两位置为-1和0.同时该算法无法直接计算abcabc的输入结果3,要计算
	 * 一个字符串的结果必须先计算其去掉最后一个字符的字符串的结果,因此顺便返回其前面所有子串计算的结果
	 * 
	 * @see https://www.cnblogs.com/ganhang-acm/p/4060508.html
	 * 
	 * @param str
	 * @return str及其所有前缀子串前后缀的最大相同长度
	 */
	// public static int[] next(String str) {
	// char[] chararray = str.toCharArray();
	//
	// int[] next = new int[str.length() + 1];
	// next[0] = -1;// 0位匹配相同,后移一位
	// next[1] = 0;// 1位不存在公共前后缀,必定为0
	//
	// // 对于chararray来说，i=1为第二个字符，对于next来说，i=1为第1个字符
	// for (int i = 1; i < chararray.length; i++) {
	// if (chararray[i] == chararray[next[i]]) {
	// next[i + 1] = next[i] + 1;
	// } else {
	// for (int j = 0; j <= next[i]; j++) {
	// // 理解有误,寻找子对称的方式不对
	// // 当失配时，我们就要像KMP一样，把共同前缀的长度缩小到上一个可能位置继续比较,char[next[next[j]]]。不理解
	//
	// if (chararray[i] == chararray[j]) {
	// next[i + 1] = j + 1;// 直接等于匹配时的字符坐标?j+1也不对，以后再考虑
	// }
	// } // 跑完循环还没有相等,那么不用管,就是0
	// }
	// }
	// return next;
	// }

	/**
	 * 模型为首位比对,失配时回溯法的next
	 *
	 * @param str
	 * @return
	 */
	public static int[] next(String str) {
		char[] chararray = str.toCharArray();

		int[] next = new int[str.length() + 1];
		next[0] = -1;// 0位匹配相同,后移一位,1无需赋值0,默认0

		// 对于chararray来说，i=1为第二个字符，对于next来说，i=1为第1个字符
		for (int i = 1; i < chararray.length; i++) {
			if (chararray[i] == chararray[next[i]]) {
				next[i + 1] = next[i] + 1;
			} else if (next[i] == 0) {
				// 若失配且没有可继承的匹配,则直接到下一轮
				continue;
			} else {
				// 失配,且存在可继承的匹配(之前匹配到的next[i]不为0),令chararray[i]和next[i]-next[i-1]匹配,直到
				// next[i] - (next[i] - next[next[i]])为应该回溯到的位置,简化为next[next[i]]
				int k = next[next[i]];
				do {// while的改进版,通过让第一次必定比较,将k>=0的条件变为k>0,防止第一次k为0之后k仍然为0的无意义循环
					if (chararray[i] == chararray[k]) {
						next[i + 1] = k + 1;
					}
					k = next[k];
				} while (k > 0);
				// while (k >= 0) {
				// if (chararray[i] == chararray[k]) {
				// next[i + 1] = k + 1;
				// }
				// if (k == 0) {
				// break;
				// }
				// k = next[k];
				// }
			}
		}
		return next;
	}

	/**
	 * 模型为自己和自己KMP的next
	 * agctagcagctagctg=======================================================
	 * =agctagcagctagctg =========================================================
	 * =====agctagca =012345678
	 * 
	 * @param str
	 * @return next数组的含义是,next[i]为str前i个字符组成的子字符串的最长公共前后缀长度. 例如对aaabbdda,next[0]
	 *         =-1,next[1]a=0,next[2]aa=1,next[3]aaa=2,next[4]aaab=0,next[5]aaabb=0
	 *         当然,对于数组和字符串来说,第i个字符的索引为i-1
	 */
	// public static int[] next(String str) {
	// char[] chararray = str.toCharArray();
	//
	// int[] next = new int[str.length() + 1];
	// next[0] = -1;// 0位匹配相同,后移一位
	//
	// for (int i = 1; i < chararray.length;) {
	// for (int j = 0; j < chararray.length; j++) {
	// if (chararray[j] == chararray[i + j]) {// 若匹配，i也随着j向后移动
	// // 最简单的情况，顺利的一直匹配下去
	// next[i + j + 1] = next[i + j] + 1;
	// continue;
	// } else {
	// // 不匹配，此时已经有j位是匹配的，故应该查看next[j]
	// i += j - next[j];
	// if (chararray[i] != chararray[0]) {
	// next[i + 1] = 0;
	// } else {
	// next[i + 1] = 1;
	// }
	// break;
	// }
	// }
	//
	// }
	// return next;
	// }

	/**
	 * 
	 * @param src
	 *            模板串
	 * @param dst
	 *            目标串
	 * @return dst在src中出现过的索引
	 */
	public static ArrayList<Integer> kmp(String src, String dst) {
		int[] next = next(dst);
		ArrayList<Integer> result = new ArrayList<Integer>();
		char[] srcc = src.toCharArray();
		char[] dstc = dst.toCharArray();

		for (int i = 0; i < srcc.length;) {

			for (int j = 0; j < dstc.length; j++) {
				if (dstc[j] != srcc[i]) {
					// 第j位不相等,代表第j+1个字符不匹配,已经有j个字符匹配,应该用next[j]
					i += j - next[j];
					// j = -1;和break;的区别是,j=-1还在本次循环内,break跳出了本次循环，虽然再次进入循环都是从j=0开始
					// 但多了一次判断外层i循环是否满足条件的机会
					break;// 缺乏break条件导致即使失配,j也会继承上次的值而不是从0开始
				} else if (j == dstc.length - 1) {
					// 相等,并且是最后一位都相等
					result.add(++i - dstc.length);
				} else {
					i++;
				}
			}

		}
		return result;
	}

	public static void main(String[] args) {
		String s1 = "EXAMPLE";
		String s2 = "HERE IS A SIMPLE EXAMPLE";
		
		System.out.println(kmp(s2, s1));
	}

}
