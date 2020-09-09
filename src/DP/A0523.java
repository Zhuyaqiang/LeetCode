package DP;

/**
 * 连续的子数组和
 *
 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 示例 1：
 输入：[23,2,4,6,7], k = 6
 输出：True
 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 示例 2：
 输入：[23,2,6,4,7], k = 6
 输出：True
 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 说明：
 数组的长度不会超过 10,000 。
 你可以认为所有数字总和在 32 位有符号整数范围内。
 */
public class A0523 {
    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{0,1,0}, 0));
    }
    // 暴力法
    public static boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                int sum = 0;
                for (int l = i; l <= j; l++)
                    sum += nums[l];
                if (sum == k || (k != 0 && sum % k == 0))
                    return true;
            }
        }
        return false;
    }
    // 优化暴力
    public static boolean checkSubarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++)
            sum[i] = sum[i-1] + nums[i];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int val = sum[j] - sum[i] + nums[i];
                if(val == k || (k != 0 && val % k == 0))
                    return true;
            }
        }
        return false;
    }
}
