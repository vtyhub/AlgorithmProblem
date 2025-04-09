package lintcode91;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	/**
	 * 91. 最小调整代价
	 * 给一个整数数组，调整每个数的大小，使得相邻的两个数的差不大于一个给定的整数target，调整每个数的代价为调整前后的差的绝对值，求调整代价之和最小是多少。
	 * 
	 * @see https://www.lintcode.com/problem/minimum-adjustment-cost/description
	 * @see https://blog.csdn.net/gzy13269561397/article/details/51010567
	 */

	public static int MinAdjustmentCost(List<Integer> A, int target) {
		if (A.size() < 2) {
			return 0;
		}
		int m = A.size();
		long[][] dp = new long[m][101];
		int i = 0, j = 0;
		for (i = 0; i < 101; i++) {
			dp[0][i] = Math.abs(A.get(0) - i);
		}
		for (i = 1; i < m; i++) {
			for (j = 0; j < 101; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				int dif = Math.abs(j - A.get(i));
				int max = Math.min(100, j + target);
				int min = Math.max(0, j - target);
				for (int k = min; k <= max; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + dif);
				}
			}
		}
		long ans = Integer.MAX_VALUE;
		for (j = 0; j < 101; j++) {
			ans = Math.min(ans, dp[m - 1][j]);
		}
		return (int) ans;
	}

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10000000; i++) {
			list.add(i);
		}
		// list.add(1);
		// list.add(4);
		// list.add(2);
		// list.add(3);
		long start = System.currentTimeMillis();
		int result = MinAdjustmentCost(list, 1);
		long end = System.currentTimeMillis();
		System.out.println(result);
		System.out.println(end - start);
	}

}
