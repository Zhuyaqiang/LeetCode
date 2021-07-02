package DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 */
public class A0322 {
    public static void main(String[] args) {
        int[] coins = {328,122,26,397,252,455,250,252};
        System.out.println(rCoinChange(coins, 7121));
//        System.out.println(rCoinChange(new int[] {1, 2, 5}, 11));
    }

    public static int rCoinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static int rBacktrack(int[] coins, int remain, int[] memo) {
        if (remain == 0)
            return 0;
        if (remain < 0)
            return -1;
        if (memo[remain] != -1)
            return memo[remain];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (remain - coins[i] >= 0) {
                int res = rBacktrack(coins, remain - coins[i], memo);
                if (res != -1) {
                    min = Math.min(min, res + 1);
                    memo[remain] = min;
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }


    // 回溯, 超时
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        Arrays.sort(coins);
        backtrack(coins, 0, amount);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    static int ans = Integer.MAX_VALUE;

    public static void backtrack(int[] coins, int count, int target) {
        for (int i = coins.length - 1; i >= 0; i--) {
            if (count + 1 >= ans)
                return;
            count++;
            int newTarget = target - coins[i];
            if (newTarget == 0) {
                ans = Math.min(count, ans);
                return;
            } else if (newTarget < 0) {
            } else {
                backtrack(coins, count, newTarget);
            }
            count--;
        }
    }

    // 回溯记忆化搜索, 自上而下
    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        if (coins.length == 0)
            return -1;
        return backtrack(coins, new int[amount], amount);
    }

    // memo储存的是对应对应面额的remain需要的最少硬币数量
    public static int backtrack(int[] coins, int[] memo, int remain) {
        if (remain == 0) {
            return 0;
        }
        if (remain < 0)
            return -1;
        if (memo[remain - 1] != 0)
            return memo[remain - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = backtrack(coins, memo, remain - coin);
            if (res >= 0 && res < min)
                min = res + 1;
        }
        memo[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return memo[remain - 1];
    }

    // 动态规划, 自下而上
    public static int coinChange3(int[] coins, int amount) {
        if (coins.length == 0)
            return -1;
        // memo[n]表示组成数额为n需要最少的硬币数
        int[] memo = new int[amount + 1];
        memo[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && memo[i - coins[j]] < min)
                    min = memo[i - coins[j]] + 1;
            }
            memo[i] = min;
        }
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}
