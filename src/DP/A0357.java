package DP;

/**
 * 计算各个位数不同的数字个数
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10的n次方 。
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 */
public class A0357 {
    public static void main(String[] args) {
        System.out.println(rCountNumbersWithUniqueDigits(3));
    }

    public static int rCountNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 10;
        int[] dp = new int[n + 1];
        dp[1] = 10;
        int sum = 10;
        for (int i = 2; i <= n; i++) {
            dp[i] = 9;
            for (int j = 0; j < i-1; j++) {
                dp[i] *= (9-j);
            }
            sum += dp[i];
        }
        System.out.println(dp[2]);
        return sum;
    }




    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        if (n == 1)
            return 10;
        int[] dp = new int[n + 1];
        // dp[i]表示各位都不相同的i位数
        // dp[1] = 10
        // dp[2] = [1,9] * [0-9](除十位选中的) = 9*9
        // dp[3] = [1-9] * [0-9](除百位选中的) * [0-9](除百位和十位选中的) = 9*9*8 = dp[2] * (10-3+1)
        // dp[4] = [1-9] * [0-9](除千位选中的) * [0-9](除千位和百位选中的) * [0-9](除千位和百位和十位选中的) = 9*9*8*7 = dp[3] * (10-4+1)
        dp[1] = 10;
        dp[2] = 81;
        int sum = 91;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] * (10-i+1);
            sum += dp[i];
        }
        return sum;
    }
}
