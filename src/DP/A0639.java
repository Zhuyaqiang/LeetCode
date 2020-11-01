package DP;

/**
 * 解码方法2
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
 * 给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
 * 同时，由于结果值可能会相当的大，所以你应当对109 + 7取模。（翻译者标注：此处取模主要是为了防止溢出）
 * 示例 1 :
 * 输入: "*"
 * 输出: 9
 * 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * 示例 2 :
 * 输入: "1*"
 * 输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18）
 * 说明 :
 * 输入的字符串长度范围是 [1, 105]。
 * 输入的字符串只会包含字符 '*' 和 数字'0' - '9'。
 */
public class A0639 {
    public static void main(String[] args) {
//        System.out.println(numDecodings("22**0"));
//        System.out.println(numDecodings("2*")); // 15
//        System.out.println(numDecodings("3*")); // 9
//        System.out.println(numDecodings("*7")); // 10
        System.out.println(numDecodings("***")); // 10
//        System.out.println(numDecodings("**")); // 96
    }

    public static int numDecodings(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        int[] dp = new int[len];
        if (s.charAt(0) == '0')
            dp[0] = 0;
        if (s.charAt(0) == '*')
            dp[0] = 9;
        else
            dp[0] = 1;
        for (int i = 1; i < len; i++) {
            // 无法解析
            if (s.charAt(i) == '0' && (s.charAt(i - 1) == '0') || (s.charAt(i - 1) > '2' && s.charAt(i) != '*'))
                return 0;
                // 10或20
            else if (s.charAt(i) == '0' && (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2')) {
                if (i > 1)
                    dp[i] = dp[i - 2];
                else
                    dp[i] = 1;
            }
            // *0
            else if (s.charAt(i) == '0' && s.charAt(i - 1) == '*') {
                if (i > 1)
                    dp[i] = 2 * dp[i - 2];
                else
                    dp[i] = 2;
            }
            // 11-19, 21-26
            else if (s.charAt(i) >= '1' && s.charAt(i) <= '6' && s.charAt(i - 1) == '2' || s.charAt(i - 1) == '1' && s.charAt(i) >= '1' && s.charAt(i) <= '9') {
                if (i > 1)
                    dp[i] = dp[i - 1] + dp[i - 2];
                else
                    dp[i] = dp[i - 1] + 1;
            } else if (s.charAt(i) == '*') {
                if (s.charAt(i - 1) == '0') {
                    dp[i] = 9 * dp[i - 1];
                } else if (s.charAt(i - 1) == '1') {
                    if (i > 1)
                        dp[i] = dp[i - 1] * 9 + dp[i - 2] * 9;
                    else
                        dp[i] = dp[i - 1] * 9 + 9;
                } else if (s.charAt(i - 1) == '2') {
                    if (i > 1)
                        dp[i] = dp[i - 1] * 9 + dp[i - 2] * 6;
                    else
                        dp[i] = dp[i - 1] * 9 + 6;
                } else if (s.charAt(i - 1) == '*') {
                    if (i > 1) {
                        dp[i] = dp[i-1] * 9 + dp[i-2] * 15;
                    } else {
                        dp[i] = dp[i - 1] * 9 + 15;
                    }
                } else {
                    dp[i] = dp[i - 1] * 9;
                }
            } else if (s.charAt(i) != '*' && s.charAt(i - 1) == '*') {
                if (s.charAt(i) > '6')
                    dp[i] = dp[i - 1] + 1;
                else
                    dp[i] = dp[i - 1] + 2;
            } else
                dp[i] = dp[i - 1];
        }
        return dp[len - 1];
    }
}
