package DP;

import java.util.Arrays;

/**
 * 猜数字大小2
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * 示例:
 * n = 10, 我选择了8.
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 * 游戏结束。8 就是我选的数字。
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class A0375 {
    public static void main(String[] args) {
        System.out.println(rGetMoneyAmount(7));
    }

    public static int rGetMoneyAmount(int n) {
        int[][] memo = new int[n][n];
        for (int i = n-1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int res = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int right = k + 1 >= n-1 ? 0 : memo[k+1][j];
                    int left = k - 1 < 0 ? 0 : memo[i][k-1];
                    int max = k + 1 + Math.max(left, right);
                    res = Math.min(res, max);
                    memo[i][j] = res;
                }
                memo[i][j] = memo[i][j] == 0 ? res : Math.min(res, memo[i][j]);
            }
        }
        return memo[0][n-1];
    }

    public static int rBacktrack(int l, int r, int[][] memo) {
        if (l >= r)
            return 0;
        if (memo[l-1][r-1] != -1)
            return memo[l-1][r-1];
        int res = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            int max = 0;
            max = i + Math.max(rBacktrack(l, i-1, memo), rBacktrack(i+1, r, memo));
            res = Math.min(res, max);
            memo[l-1][r-1] = res;
        }
        return res;
    }

    public static int getMoneyAmount(int n) {
        return backtrack(1, n);
    }
    // 暴力, 超时
    // 对于[l, r], 每次从l到r中循环选定i=l,l+1,l+2,...r, 计算[l, i-1]和[i+1, r]两个值的最大值, 加上i本身, 每次取最小值
    public static int backtrack(int l, int r) {
        if (l >= r)
            return 0;
        int max = 0, res = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            max = i + Math.max(backtrack(l, i-1), backtrack(i+1, r));
            res = Math.min(res, max);
        }
        return res;
    }
    // 递归, 记忆化搜索
    public static int getMoneyAmount2(int n) {
        int[][] memo = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        for (int i = 1; i <= n; i++)
            memo[i][i] = 0;
        return backtrack2(1, n, memo);
    }
    public static int backtrack2(int l, int r, int[][] memo) {
        if (l >= r)
            return 0;
        int max = 0, res = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            int left, right;
            if (i-1 >= l && memo[l][i-1] != -1)
                left = memo[l][i-1];
            else
                left = backtrack2(l, i-1, memo);
            if (i+1 <= r && memo[i+1][r] != -1)
                right = memo[i+1][r];
            else
                right = backtrack2(i+1, r, memo);
            max = i + Math.max(right, left);
            res = Math.min(res, max);
            memo[l][r] = res;
        }
        return res;
    }

    // 动态规划
    // 画dp表, 从左往右, 从下往上
    public static int getMoneyAmount3(int n) {
        int[][] memo = new int[n+1][n+1];
        for (int i = n; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                int max = 0, res = Integer.MAX_VALUE, left, right;
                for (int k = i; k <= j; k++) {
                    left = memo[i][k-1];
                    right = k+1 > n ? 0 : memo[k+1][j];
                    max = k + Math.max(left, right);
                    res = Math.min(res, max);
                    memo[i][j] = res;
                }
            }
        }
        return memo[1][n];
    }
}
