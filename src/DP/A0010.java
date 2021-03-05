package DP;

/**
 * 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class A0010 {
    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }

























    public static boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1]; // dp[i][j]代表s的前i个字符是否被p的前j个字符匹配
        dp[0][0] = true;
        // dp[0][1] = false;
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = false;
        }
        for (int i = 2; i <= pLen; i++) {
            dp[0][i] = p.charAt(i-1) == '*' && dp[0][i-2];
        }
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < pLen; j++) {
                if (p.charAt(j) == '*') {
                    dp[i+1][j+1] = dp[i+1][j+1-2] || // s: aa, b: aab* 匹配0个
                            check(s, p, i, j-1) && dp[i+1-1][j+1];  // s: aab, b: aab*  匹配一个或多个, 若s的b和p的b相等，则对比s的a和p的*
                } else {
                    dp[i+1][j+1] = dp[i][j] && check(s, p, i, j);
                }
            }
        }
        return dp[sLen][pLen];
    }
    // 判断s的第i个字符和p的第j个字符是否匹配
    public static boolean check(String s, String p, int i, int j) {
        return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
    }
}
