package DP;

/**
 * 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class A0091 {
    public static void main(String[] args) {
        int i = numDecodings("27");
        System.out.println(i);
    }
    public static int numDecodings(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        int[] dp = new int[len];
        if (s.charAt(0) == '0')
            return 0;
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            // 无法解析
            if (s.charAt(i) == '0' && (s.charAt(i-1) == '0' || s.charAt(i-1) > '2'))
                return 0;
            // 10或20
            else if (s.charAt(i) == '0' && (s.charAt(i-1) == '1' || s.charAt(i-1) == '2')) {
                if (i > 1)
                    dp[i] = dp[i-2];
                else
                    dp[i] = 1;
            }
            // 11-19 or 21-26
            else if ((s.charAt(i) >= '1' && s.charAt(i) <= '6' && s.charAt(i-1) == '2') || s.charAt(i-1) == '1') {
                if (i > 1)
                    dp[i] = dp[i-1] + dp[i-2];
                else
                    dp[i] = dp[i-1] + 1;
            }
            // 如27
            else
                dp[i] = dp[i-1];
        }
        return dp[len-1];
    }
}
