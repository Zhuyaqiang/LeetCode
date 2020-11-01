package DP;

import java.util.Arrays;

/**
 * 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class A0416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    // 递归, 合理剪枝
    public static boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        if (sum % 2 != 0) return false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (backtrack(nums, i, sum/2))
                return true;
        }
        return false;
    }
    public static boolean backtrack(int[] nums, int index, int target) {
        if (target == 0)
            return true;
        for (int i = index; i < nums.length; i++) {
            // 如果有重复, 第一次以i开头找不到结果, 之后肯定都找不到
            if (i > index && nums[i] == nums[i-1])
                continue;
            if (target - nums[i] < 0)
                return false;
            if (backtrack(nums, i + 1, target - nums[i]))
                return true;
        }
        return false;
    }
    // 0-1背包, 动态规划
    public static boolean canPartition2(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return false;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0)
            return  false;
        int target = sum / 2;
        // dp[i][j]表示前i个数字中任选几个数能使和恰好为j
        // 状态转移: 1. 不选nums[i], 即[0,i-1]子区间内已经有一部分元素和为j, 那么dp[i][j] = true
        //         2. 选nums[i], 那么就需要[0,i-1]子区间内有一部分元素和为j-nums[i]
        //   所以dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]] || true
        //                  情况1             情况2             nums[i] == j
        boolean[][] dp = new boolean[len][target+1];

        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j)
                    dp[i][j] |= dp[i-1][j-nums[i]];
            }
        }
        return dp[len-1][target];
    }
    // 动态规划优化
    public static boolean canPartition3(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return false;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        // 不选nums[0], 则满足和为0
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            // 逆序的原因是状态转移方程中dp[j] = dp[j] || dp[j-nums[i]]
            //                       新       旧           旧
            // 如果使用正序, 则对每个j来说, dp[j-nums[i]]就可能在之前就被更新过, 即 当前j-nums[i] == 之前某一个更新过的j
            for (int j = target; j >= nums[i]; j--) {
                if (dp[target])
                    return true;
                dp[j] |= dp[j-nums[i]];
            }
        }
        return dp[target];
    }
}
