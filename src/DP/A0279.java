package DP;

/**
 * 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例 1:
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
//        System.out.println(numSquares(1));
//        System.out.println(numSquares(2));
//        System.out.println(numSquares(3));
        System.out.println(numSquares(4));
        System.out.println(numSquares(5));
        System.out.println(numSquares(6));
        System.out.println(numSquares(7));
        System.out.println(numSquares(8));
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
