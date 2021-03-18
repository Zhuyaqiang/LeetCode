package DP;

/**
 * 不同的子序列
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * 示例 1：
 * 输入：S = "rabbbit", T = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 * 输入：S = "babgbag", T = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class A0115 {
    public int rNumDistinct(String s, String t) {
        int sLen = s.length(), tlen = t.length();
        int[][] dp = new int[sLen + 1][tlen + 1];
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tlen; j++) {
                if (s.charAt(i - 1) != s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][tlen];
    }
    public int numDistinct(String s, String t) {
        int slen = s.length(), tLen = t.length();
        // dp[i][j]表示s的前i个字符[0, i]可以由t的前j个字符[0, j]组成的最多个数
        int[][] dp = new int[slen + 1][tLen + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= slen; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= tLen; i++)
            dp[0][i] = 0;
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i-1) != t.charAt(j-1)) {  // 不相等的话，意味着s的第i个字符不参与构成子序列
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]; // 两种情况，s的第i个字符参与构成子序列，s的第i个字符不参与构成子序列
                }
            }
        }
        return dp[slen][tLen];
    }
}
