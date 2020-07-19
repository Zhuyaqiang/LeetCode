package DP;

/**
 * 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class A0044 {
    public boolean rIsMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= pLen; i++) {
            dp[0][i] = p.charAt(i-1) == '*' && dp[0][i-1];
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j-1) == '?' || (p.charAt(j-1) == s.charAt(i-1)))
                    dp[i][j] = dp[i-1][j-1];
                else {
                    if (p.charAt(j-1) == '*') {
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[sLen][pLen];
    }















    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        // dp[i][j] 表示s的第i个字符和p的第j个字符, 即s.charAt(i-1)和p.charAt(j-1)
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        // dp[i][0] = false
        // dp[0][i]初始化
        for (int i = 1; i < pLen + 1; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')
                    dp[i][j] = dp[i-1][j-1];
                else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j]/* '*'匹配成非空字符串, 如abcd和ab* */ || dp[i][j-1]/* '*'匹配成空字符串 如ab和ab* */;
                }
            }
        }
        return dp[sLen][pLen];
    }
}
