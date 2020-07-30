package DP;

import java.util.Arrays;

/**
 * 一和零
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 * 现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 * 你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * 注意:
 * 给定 0 和 1 的数量都不会超过 100。
 * 给定字符串数组的长度不会超过 600。
 * 示例 1:
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 */
public class A0474 {
    // 递归
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0))
            return 0;
        return backtrack(strs, strs.length - 1, m, n);
    }
    // 返回m个0和n个1能组成strs[0,i]的最大字符串数量
    public int backtrack(String[] strs, int index, int m, int n) {
        if (index < 0)
            return 0;
        String str = strs[index];
        int zeros = 0, ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0')
                zeros ++;
            else
                ones ++;
        }
        if (zeros <= m && ones <= n) {
            return Math.max(1 + backtrack(strs, index - 1, m - zeros, n - ones), backtrack(strs, index - 1, m, n));
        } else
            return backtrack(strs, index - 1, m, n);
    }

    public static void main(String[] args) {
        String[] strs = {"11111","100","1101","1101","11000"};
        System.out.println(findMaxForm2(strs,5,7));
    }
    // 递归, 记忆化搜索
    public static int findMaxForm2(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0))
            return 0;
        int[][][] memo = new int[strs.length][m+1][n+1];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++)
                Arrays.fill(memo[i][j], -1);
        }
        return backtrack2(strs, strs.length-1, m, n, memo);
    }
    public static int backtrack2(String[] strs, int index, int m, int n, int[][][] memo) {
        if (index < 0)
            return 0;
        if (memo[index][m][n] != -1)
            return memo[index][m][n];
        String str = strs[index];
        int zeros = 0, ones = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0')
                zeros ++;
            else
                ones ++;
        }
        if (m >= zeros && n >= ones) {
            memo[index][m][n] = Math.max(1 + backtrack2(strs, index - 1, m - zeros, n - ones, memo), backtrack2(strs, index - 1, m, n, memo));
        } else {
            memo[index][m][n] = backtrack2(strs, index - 1, m, n, memo);
        }
        return memo[index][m][n];
    }
    // 动态规划
    public static int findMaxForm3(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0))
            return 0;
        int[][][] dp = new int[strs.length][m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int zeros = 0, ones = 0;
            for (int index = 0; index < str.length(); index++) {
                if (str.charAt(index) == '0')
                    zeros ++;
                else
                    ones ++;
            }
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeros && k >= ones) {
                        if (i == 0)
                            dp[i][j][k] = 1;
                        else
                            dp[i][j][k] = Math.max(1 + dp[i-1][j-zeros][k-ones], dp[i-1][j][k]);
                    } else
                        dp[i][j][k] = i > 0 ? dp[i-1][j][k] : 0;
                }
            }
        }
        return dp[strs.length - 1][m][n];
    }
    // 动态规划, 空间优化
    public static int findMaxForm4(String[] strs, int m, int n) {
        if (strs.length == 0 || (m == 0 && n == 0))
            return 0;
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int zeros = 0, ones = 0;
            for (int index = 0; index < str.length(); index++) {
                if (str.charAt(index) == '0')
                    zeros ++;
                else
                    ones ++;
            }
            for (int j = m; j >= zeros; j--) {
                for (int k = n; k >= ones; k--) {
                        dp[j][k] = Math.max(1 + dp[j-zeros][k-ones], dp[j][k]);
                }
            }
        }
        return dp[m][n];
    }
}
