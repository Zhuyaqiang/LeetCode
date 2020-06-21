package other;

/**
 * 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示：
 * 0 <= num < 231
 */
public class M0046 {
    public static void main(String[] args) {
        int num = 12258;
        System.out.println(translateNum2(num));
    }

    // 递归
    public static char[] str;
    public static int res = 0;
    public static int translateNum(int num) {
        str = String.valueOf(num).toCharArray();
        backtrack(0);
        return res;
    }
    public static void backtrack(int start) {
        if (start == str.length) {
            res ++;
            return;
        }
        backtrack(start + 1);
        if (start < str.length-1 && ((str[start] == '1' && str[start+1] >= '0' && str[start+1] <= '9') || (str[start] == '2' && str[start+1] >= '0' && str[start+1] <= '5'))) {
            backtrack(start + 2);
        }
    }

    // 动态规划
    // dp[i]表示至第i-1个字符有多少种翻译方式
    // 若第i个字符和第i-1个字符满足10-25, 则dp[i] = dp[i-1] + dp[i-2]
    // 否则, dp[i] = dp[i-1]
    public static int translateNum2(int num) {
        String str = String.valueOf(num);
        int len = str.length();
        if (len == 1)
            return 1;
        int[] dp = new int[len];
        dp[0] = 1;
        int tempNum = Integer.parseInt(str.substring(0, 2));
        if (tempNum >= 10 && tempNum <= 25)
            dp[1] = 2;
        else
            dp[1] = 1;
        for (int i = 2; i < len; i++) {
            tempNum = Integer.parseInt(str.substring(i-1, i + 1));
            if (tempNum >= 10 && tempNum <= 25)
                dp[i] = dp[i-1] + dp[i-2];
            else
                dp[i] = dp[i-1];
        }
        return dp[len-1];
    }
}
