package leetcodeminimumsizesubarraysum;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	/**
	 * 最小长度子数组的内容个数
	 * 
	 * Given an array of n positive integers and a positive integer s, find the
	 * minimal length of a subarray of which the sum ≥ s. If there isn't one, return
	 * 0 instead.
	 * 
	 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has
	 * the minimal length under the problem constraint.
	 * 
	 * @see https://blog.csdn.net/lu597203933/article/details/46389491
	 * @see https://leetcode.com/problems/minimum-size-subarray-sum/
	 */

	public static int minSubArrayLen(int s, List<Integer> nums) { // 滑动窗口的形式+双指针
		int result = nums.size() + 1;// 当前最小数组的长度，最小的窗口的大小
		int frontPoint = 0, sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
			while (sum >= s) { // 找到了窗口
				result = Math.min(result, i - frontPoint + 1); // 对当前记录中最短长度和本次可用窗口的长度进行比较，更新当前最小长度为其中最小值，找到的第一个可用窗口时跟nums.size()
																// + 1比较
				sum -= nums.get(frontPoint++); // 缩减窗口
			}
		}
		return result == (nums.size() + 1) ? 0 : result;
	}

	public static void main(String[] args) {
		int[] a = { 2, 3, 1, 2, 4, 3 };
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : a) {
			list.add(i);
		}
		System.out.println(minSubArrayLen(7, list));
	}
}
