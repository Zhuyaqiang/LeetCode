package DP;

/**
 * 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class A0343 {
    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }


    // 暴力法
    public static int integerBreak(int n) {
        if (n == 2)
            return 1;
        int res = -1;
        for (int i = 1; i <= n-1; i++) {
            // 判断拆分的另一半如果F(n-i) < n-i, 那就不需要继续拆分了
            res = Math.max(res, Math.max(i * (n - i), i * integerBreak(n - i)));
        }
        return res;
    }

    // 暴力法, 记忆化搜索
    public static int integerBreak2(int n) {
        if (n == 2)
            return 1;
        int[] memo = new int[n + 1];
        memo[2] = 1;
        return backtrack(n, memo);
    }
    public static int backtrack(int n, int[] memo) {
        if (memo[n] != 0)
            return memo[n];
        int res = -1;
        for (int i = 1; i <= n-1; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * backtrack(n - i, memo)));
        }
        memo[n] = res;
        return res;
    }

    // 动态规划
    public static int integerBreak3(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i-j]));
            }
        }
        return dp[n];
    }
}
