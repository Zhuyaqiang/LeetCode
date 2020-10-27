package other;

import java.util.Stack;

/**
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class A0032 {
    public static void main(String[] args) {
//        String s = ")()())";
        String s = "()(()";
        System.out.println(longestValidParentheses(s));
    }
    public static int longestValidParentheses(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        char[] strs = s.toCharArray();
        int[] dp = new int[len];
        dp[0] = 0;
        int count = 0;
        for (int i = 1; i < len; i++) {
            if (strs[i] == ')') {
                if (strs[i-1] == '(') {
                    if (i >= 2)
                        dp[i] = dp[i-2] + 2;
                    else
                        dp[i] = 2;
                    count = Math.max(count, dp[i]);
                } else if (strs[i-1] == ')') {
                    if (i - dp[i-1] - 1 >= 0 && strs[i-dp[i-1]-1] == '(') {
                        if (i-dp[i-1]-2 >= 0 ) { // ()(())
                            dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2;
                        } else {  // (())
                            dp[i] = dp[i-1] + 2;
                        }
                        count = Math.max(count, dp[i]);
                    }
                }
            }
        }
        return count;
    }
}
