package lintcode362;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

	/**
	 * 362. 滑动窗口的最大值 给出一个可能包含重复的整数数组，和一个大小为 k 的滑动窗口, 从左到右在数组中滑动这个窗口，找到数组中每个窗口内的最大值。
	 * 
	 * @see https://www.lintcode.com/problem/sliding-window-maximum/description
	 * @see https://blog.csdn.net/u010429424/article/details/73692248
	 */

	public static ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
		// k的大小大于nums的大小的情况
		// nums大小为0的情况被包含在这种情况之内
		if (k > nums.length) {
			k = nums.length;
		}
		
		ArrayList<Integer> result = new ArrayList<Integer>(nums.length - k + 1);// 元素个数同窗口个数，找出每个窗口中的最大值
		LinkedList<Integer> list = new LinkedList<>();// 存储nums中每个元素的下标


		// 正常情况
		for (int i = 0; i < nums.length; i++) {

			// 1删除
			// 若下标不在当前窗口[i,i+k-1],[i,i+k)中，则删除
			if (list.size() != 0) {
				Integer first = list.getFirst();
				// 通过判断当前下标和队首的值所代表的下标之差是否大于等于窗口大小来决定该值是否出了窗口
				if (Math.abs(i - first) >= k) {
					list.pollFirst();
				}
			}

			// 2 添加
			while (list.size() != 0) {
				Integer last = list.getLast();
				if (nums[last] > nums[i]) {
					// 符合的情况
					list.addLast(i);
					break;
				} else {
					list.pollLast();
				}
			}
			if (list.size() == 0) {
				list.addLast(i);
			}

			// 3结果 只有当至少第一个窗口完整后才开始存储结果
			if (i >= k - 1) {
				result.add(nums[list.getFirst()]);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int[] a = {};
		int k = 0;
		System.out.println(maxSlidingWindow(a, k));
	}
}
