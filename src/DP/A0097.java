package DP;

/**
 * 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class A0097 {
    public static void main(String[] args) {
        String s1 = "aa", s2 = "ab", s3 = "abaa";
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(rIsInterleave(s1,s2,s3));
    }
    public static boolean rIsInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        // dp[i][j]表示 s1的[0, i)和 s2的[0, j)共 i + j 个字符组成是否能构成 s3的[0, i+j)
         boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
         for (int i = 0; i <= s1.length(); i++) {
             for (int j = 0; j <= s2.length(); j++) {
                 if (i == 0 && j == 0) {
                     dp[i][j] = true;
                 } else if (i == 0) {
                     dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j -1 );
                 } else if (j == 0) {
                     dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j -1 );
                 } else {
                     dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j -1) || dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j - 1);
                 }
             }
         }
         return dp[s1.length()][s2.length()];
    }
    // 递归暴力, 超时
    public static boolean isInterleave(String s1, String s2, String s3) {
        return backtrack(s1, 0, s2, 0, "", s3);
    }
    public static boolean backtrack(String s1, int i, String s2, int j, String res, String s3) {
        if (res.equals(s3) && i == s1.length() && j == s2.length())
            return true;
        if (i < s1.length() && j < s2.length() && s1.charAt(i) != s3.charAt(res.length()) && s2.charAt(j) != s3.charAt(res.length()))
            return false;
        boolean ans = false;
        if (i < s1.length())
            ans = ans | backtrack(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        if (j < s2.length())
            ans = ans | backtrack(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        return ans;
    }

    // 动态规划
    // 二维数组dp[i][j]表示s1[0,i)和s2[0,j)共i + j个字符组合后能否构成s3[0,i+j)
    // 若dp[i-1][j]为true, 则dp[i][j]的值就依赖s1的第i-1和s3的第i+j-1
    // 若dp[i][j-1]为true, 则dp[i][j]的值就依赖s2的第j-j和s3的第i+j-1
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0 ; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j - 1);
                else if (j == 0)
                    dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j - 1);
                else
                    dp[i][j] = (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j - 1)) || (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
