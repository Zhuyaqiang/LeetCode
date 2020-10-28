package other;

/**
 * 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 */
public class A0097 {
    public static void main(String[] args) {
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
//        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
//        System.out.println(isInterleave("", "", ""));
//        System.out.println(isInterleave("", "b", "b"));
//        System.out.println(isInterleave("aa", "ab", "abaa"));
        System.out.println(isInterleave("ab", "bc", "babc"));
    }
    public static boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3)
            return false;
        if (len1 == 0 && len2 == 0 && len3 == 0)
            return true;
        if (len1 == 0)
            return s2.equals(s3);
        if (len2 == 0)
            return s1.equals(s3);
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            if (s1.charAt(i-1) == s3.charAt(i-1))
                dp[i][0] = true;
            else
                break;
        }
        for (int j = 1; j <= len2; j++) {
            if (s2.charAt(j-1) == s3.charAt(j-1))
                dp[0][j] = true;
            else
                break;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                boolean res = false;
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1))
                    res |= dp[i-1][j];
                if (s2.charAt(j-1) == s3.charAt(i + j - 1))
                    res |= dp[i][j - 1];
                dp[i][j] = res;
            }
        }
        return dp[len1][len2];
    }
}
