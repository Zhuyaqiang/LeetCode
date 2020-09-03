package DP;

/**
 * 组合总和
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 示例:
 * nums = [1, 2, 3]
 * target = 4
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 因此输出为 7。
 * 进阶：
 * 如果给定的数组中含有负数会怎么样？
 * 问题会产生什么变化？
 * 我们需要在题目中添加什么限制来允许负数的出现？
 */
public class A0377 {
    public int rCombinationSum4(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i - nums[j] >= 0)
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // dp[i] 表示nums能组成的和为i的组合的个数
        // dp[i] =     dp[i-nums[0]]       +      dp[i-nums[1]]      +     dp[i-nums[2]] + ... + dp[i-nums[len-1]]
        //        nums[0]和(i-nums[0])的结果    nums[1]和(i-nums[1])的结果
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0)
                    dp[i] += dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}
