package DP;

import java.util.*;

/**
 * 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class A0494 {
    public static void main(String[] args) {
        System.out.println(findTargetSumWays2(new int[]{0,0,0,0,0,0,0,0,1}, 1));
    }

    // 递归
    public static int res = 0;

    public static int findTargetSumWays(int[] nums, int S) {
        Arrays.sort(nums);
        backtrack(nums, S, 0, 0);
        return res;
    }

    public static void backtrack(int[] nums, int target, int sum, int index) {
        if (index == nums.length) {
            if (target == sum) {
                res++;
            }
            return;
        }
        backtrack(nums, target, sum - nums[index], index + 1);
        backtrack(nums, target, sum + nums[index], index + 1);
    }

    // 将nums分割成两个子集, 子集A为正号, 子集B为负号,
    // 可以得出sum(A) - sum(B) = target
    //       sum(A) = target + sum(B)
    //       2 * sum(A) = target + sum(B) + sum(A)
    //       2 * sum(A) = target + sum(nums)
    //       sum(A) = (target + sum(nums)) / 2
    // dp[i][j] = x表示若只在前i个物品选择, 若当前容量为j, 则最多有x种方法刚好装满背包
    // dp[0][j] = 0, dp[i][0] = 1
    // dp[i][j]的情况: 1. 不把nums[i]放进子集, dp[i][j] = dp[i-1][j]
    //                2. 把nums[i]放进子集, 只看前i-1个物品装满j-nums[i-1], dp[i][j] = dp[i-1][j-nums[i-1]] (nums[i-1]代表第i个物品)
    public static int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        if (sum < S || (sum + S) % 2 == 1)
            return 0;
        return subsets(nums, (sum + S) / 2);
    }

    private static int subsets(int[] nums, int sum) {
        int len = nums.length;
        int[][] dp = new int[len + 1][sum + 1];
        for (int i = 0; i <= len; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= nums[i - 1])
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[len][sum];
    }
}
