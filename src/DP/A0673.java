package DP;

import java.util.Arrays;

/**
 * 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 */
public class A0673 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2, 2};
//        int[] nums = {1,3,5,4,7};
//        int[] nums = {1,2,4,3,5,4,7,2}; // 12457 12357 12347
        int numberOfLIS = findNumberOfLIS(nums);
        System.out.println(numberOfLIS);
    }
    public static int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return len;
        int[] length = new int[len];
        int[] count = new int[len];
        Arrays.fill(count, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (length[i] <= length[j]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    } else if (length[j] + 1 == length[i])
                        count[i] += count[j];
                }
            }
        }
        int longest = 0, ans = 0;
        for (int i = 0; i < len; i++)
            longest = Math.max(longest, length[i]);
        for (int i = 0; i < len; i++) {
            if (length[i] == longest)
                ans += count[i];
        }
        return ans;
    }
}
