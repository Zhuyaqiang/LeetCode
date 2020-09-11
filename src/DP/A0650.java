package DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 只有两个键的键盘
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 * 示例 1:
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * 说明:
 * n 的取值范围是 [1, 1000] 。
 */
public class A0650 {
    public static void main(String[] args) {
        System.out.println(minStep3(18));
    }
    public static int res = Integer.MAX_VALUE;
    // 递归
    public static int c = 0;
    public static int minSteps(int n) {
        if (n < 2)
            return 0;
        backtrack(n, 1, 1, -1);
        return res;
    }
    // flag < 0: 前一次是复制操作, flag > 0: 粘贴操作
    public static void backtrack(int n, int curr, int count, int flag) {
        c ++;
        if (curr > n)
            return;
        if (curr == n) {
            res = Math.min(res, count);
            return;
        }
        if (flag < 0) {
            backtrack(n, curr * 2, count + 1, curr);
        } else {
            backtrack(n, curr + flag, count + 1, flag);
            backtrack(n, curr, count +1, -1);
        }
    }

    // 递归, 记忆化搜索
    public static Map<String, Integer> map = new HashMap<>();
    public static int minSteps2(int n) {
        if (n < 2)
            return 0;
        return backtrack2(n, n-1, 1, -1);
    }
    public static int backtrack2(int n, int remain, int count, int flag) {
        if (remain < 0)
            return Integer.MAX_VALUE;
        if (remain == 0) {
            return count;
        }
        String key = remain + "," + flag;
        if (map.containsKey(key))
            return map.get(key);
        if (flag < 0) {
            int val = backtrack2(n, remain - (n - remain), count + 1, n - remain);
            map.put(key, val);
            return val;
        } else {
            int val1 = backtrack2(n, remain, count + 1, -1);
            int val2 = backtrack2(n, remain - flag, count + 1, flag);
            map.put(key, Math.min(val1, val2));
            return Math.min(val1, val2);
        }
    }

    // 动态规划
    public static int minStep3(int n) {
        int[][] dp = new int[n+1][n+1];
        // dp[i][j]表示复制有 j 个'A', 已经完成了 i 个'A', 因此j <= i, 最小操作数
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], n + 1);
        dp[1][1] = 0;
        for (int i = 1; i <= n; i++) {
            int min = dp[i][1];
            for (int j = 1; j <= i; j++) {
                if (i - j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-j][j] + 1);
                    min = Math.min(min, dp[i][j]);
                }
                // 代表复制所有 A
                if (i == j)
                    dp[i][j] = min + 1;
            }
        }
        return dp[n][n] - 1;
    }
}
