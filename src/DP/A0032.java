package DP;

import java.util.Stack;

/**
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
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
        String s = ")()())";
//        String s = "()(()";
        System.out.println(rLongestValidParentheses(s));
    }
    public static int rLongestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    if (i - 1 == 0) {
                        dp[i] = 2;
                    } else {
                        dp[i] = dp[i - 2] + 2;
                    }
                    // s.charAt(i - 1) == ')'
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        if (i - dp[i - 1] - 1 == 0) {
                            dp[i] = dp[i - 1] + 2;
                        } else if (i - dp[i - 1] - 1 > 0) {
                            dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                        }
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
    // 栈
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int ans = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
    // 动态规划
    // 统计时机一定是s[i] = ')'时
    // 此时有两种情况, 第一种s[i-1] == '(', dp[i] = dp[i-2] + 2
    //              第二种s[i-1] == ')', 如'......))'. 如果s[i-1]对应一个完整括号, 即s[i-dp[i-1]-1] == '(', 则dp[i] = dp[i-1] +  dp[i-dp[i-1]-2] + 2
    public static int longestValidParentheses2(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    if (i >= 2)
                        dp[i] = dp[i - 2] + 2;
                    else
                        dp[i] = 2;
                    max = Math.max(dp[i], max);
                } else if (i - dp[i-1] - 1 >= 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    if (i - dp[i-1] - 1 == 0) {
                        dp[i] = dp[i-1] + 2;
                    } else {
                        dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
                    }
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
}
