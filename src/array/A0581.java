package array;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

/**
 * 最短无序连续子数组
 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 你找到的子数组应是最短的，请输出它的长度。
 示例 1:
 输入: [2, 6, 4, 8, 10, 9, 15]
 输出: 5
 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 说明 :
 输入的数组长度范围在 [1, 10,000]。
 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class A0581 {
    public static void main(String[] args) {
        int[] nums = {2,3,3,2,4};
//        int[] nums = {1,2,4,5,3};
        int unsortedSubarray = findUnsortedSubarray4(nums);
        System.out.println(unsortedSubarray);
    }
    // 先排序
    public static int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int[] copy = Arrays.copyOf(nums, len);
        Arrays.sort(copy);
        int i, j;
        for (i = 0; i < len; i++) {
            if (nums[i] != copy[i])
                break;
        }
        if (i == len)
            return 0;
        for (j = len-1; j >= 0; j--) {
            if (nums[j] != copy[j])
                break;
        }
        return j-i+1;
    }
    // 基于简单排序, 对数组中所有满足0<=i<j<len的元素nums[i]和nums[j]进行两两比较,
    // 若nums[i]>nums[j]则说明这两个数字不在正确的位置, 记录最小的i和最大的j
    public static int findUnsortedSubarray2(int[] nums) {
        int len = nums.length;
        int l = len, r = 0;
        for (int i = 0; i < len-1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] > nums[j]) {
                    l = Math.min(l, i);
                    r = Math.max(r, j);
                }
            }
        }
        return r-l < 0 ? 0 : r - l + 1;
    }
    // 基于选择排序, 使用单调栈, 先找出最小的左边界, 再找出最大的右边界
    public static int findUnsortedSubarray3(int[] nums) {
        int len = nums.length;
        int l = len, r = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                // l即是nums[i]的正确位置
                l = Math.min(l, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = len-1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                r = Math.max(r, stack.pop());
            }
            stack.push(i);
        }
        return r - l < 0 ? 0 : r - l + 1;
    }
    // 先找出最大无序数组中的最小值, 再找出最大无需数组中的最大值
    // 再对比他们的正确位置
    public static int findUnsortedSubarray4(int[] nums) {
        int len = nums.length;
        boolean flag = false;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i-1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = len-2; i >= 0; i--) {
            if (nums[i] > nums[i+1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < len; l++) {
            if (nums[l] > min)
                break;
        }
        for (r = len-1; r >= 0; r--) {
            if (nums[r] < max)
                break;
        }
        return l < r ? r - l + 1: 0;
    }
}
