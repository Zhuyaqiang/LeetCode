package DP;

/**
 * 最长回文子序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * 示例 1:
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * 示例 2:
 * 输入:
 * "cbbd"
 * 输出:
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 */
public class A0516 {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("abcdef"));
    }
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len <= 1)
            return len;
        // dp[i][j]表示从[i,j]最长回文子序列
        // i和j字符相同: dp[i][j] = dp[i+1][j-1] + 2
        //        不同: dp[i][j] = max(dp[i+1][j], dp[i][j-1]
        int[][] dp = new int[len][len];
        // dp table从下往上从左往右
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (j == i + 1) {
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = 2;
                    else {
                        dp[i][j] = dp[i+1][j];
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        for (int[] ints : dp) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        return dp[0][len-1];
    }
}
