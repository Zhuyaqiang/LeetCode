package DP;

/**
 * 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */
public class A0392 {
    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
    }

    // 动态规划
    public static boolean rIsSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen > tLen)
            return false;
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = true;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i-1) != t.charAt(j-1))
                    dp[i][j] = dp[i][j-1];
                else
                    dp[i][j] = dp[i-1][j-1];
            }
        }
        return dp[sLen][tLen];
    }


    // 双指针
    public static boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (tLen < sLen)
            return false;
        if (sLen == 0)
            return true;
//        boolean[][] dp = new boolean[tLen + 1][sLen + 1];
//        dp[0][0] = true;
//        for (int i = 1; i < tLen; i++)
//            dp[i][0] = true;
//        for (int i = 1; i < sLen; i++)
//            dp[0][i] = false;
        int sIndex = 0, tIndex = 0;
        while (sIndex < sLen && tIndex < tLen) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                tIndex++;
            } else
                tIndex++;
            if (sIndex == sLen)
                return true;
        }
        return false;
    }
    // 进阶, 先处理长字符串
    public static boolean isSubsequence2(String s, String t) {
        t = " " + t; // " "作为匹配入口
        int len = t.length();
        int[][] dp = new int[26][len];
        // dp[i][j]表示t的第j个字母之后i+'a'字母第一次出现的位置, 没有的话值为-1
        for (int i = 0; i < 26; i++) {
            int p = -1;
            for (int j = len-1; j >= 0; j--) {
                dp[i][j] = p;
                if (t.charAt(j) == i + 'a')
                    p = j;
            }
        }
        // 匹配
        int i = 0;
        for (char ch : s.toCharArray()) {
            i = dp[ch - 'a'][i];
            if (i == -1)
                return false;
        }
        return true;
    }
}
