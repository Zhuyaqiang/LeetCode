package DP;

import java.util.Arrays;

/**
 * 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class A0279 {
    public static void main(String[] args) {
        System.out.println(rNumSquares(4));
        System.out.println(rNumSquares(5));
        System.out.println(rNumSquares(6));
        System.out.println(rNumSquares(7));
        System.out.println(rNumSquares(8));
    }

    public static int rNumSquares(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public static int numSquares(int n) {
        // 以12为例
        // 12 = 1 + 11
        // 12 = 4 + 8
        // 12 = 9 + 3
        // dp[i]中存的是组成i的最小平方数的个数
        // 即dp[12] = 1 + MIN(dp[11], dp[8], dp[3])
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++)
            dp[i] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
