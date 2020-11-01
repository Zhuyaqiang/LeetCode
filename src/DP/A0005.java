package DP;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class A0005 {
    public static void main(String[] args) {
        String a = "cbbd";
        System.out.println(longestPalindrome3(a));
    }
    // 暴力法
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        char[] strChars = s.toCharArray();
        int max = 1, begin = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > max && check(strChars, i, j)) {
                    max = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, max);
    }
    public static boolean check(char[] array, int l, int r) {
        while (l < r) {
            if (array[l] != array[r])
                return false;
            l++;
            r--;
        }
        return true;
    }

    // 动态规划
    // dp[i][j]表示i到j是否是回文串
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        // 单个字符一定是回文
        for (int i = 0; i < len; i++)
            dp[i][i] = true;
        int max = 1, begin = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j])
                    dp[i][j] = false;
                else {
                    // 如果长度小于等于3且第一个和最后一个字符相等则是回文串
                    if (j - i + 1 < 4) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && (j - i + 1) > max) {
                    begin = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(begin, begin + max);
    }

    // 中心扩散
    public static String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        String res = s.substring(0,1);
        int maxLen = 1;
        for (int i = 0; i < len - 1; i++) {
            String strA = check2(s, i, i); // 以i为中心
            String strB = check2(s, i, i+1); // 以i和i+1的分界线为中心
            String maxStr = strA.length() > strB.length() ? strA : strB;
            if (maxStr.length() > maxLen) {
                res = maxStr;
                maxLen = maxStr.length();
            }
        }
        return res;
    }
    // 以l/r为中心判断回文串长度
    public static String check2(String s, int l, int r) {
        int len = s.length();
        int i = l, j = r;
        while (i >= 0 && j < len) {
            if (s.charAt(i) != s.charAt(j))
                break;
            else {
                i--;
                j++;
            }
        }
        // 跳出时 s[i] != s[j], 因此返回的范围应该是(i, j), 即取不到i和j
        return s.substring(i + 1, j);
    }
}
