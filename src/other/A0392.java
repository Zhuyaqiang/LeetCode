package other;

/**
 * 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */
public class A0392 {
    public static void main(String[] args) {
        System.out.println(isSubsequence("aaaaaa", "bbaaaa"));
    }
    // 双指针
    public static boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return false;
        int sIndex = 0, tIndex = 0;
        while (sIndex < sLen && tIndex < tLen) {
            while (tIndex < tLen && s.charAt(sIndex) != t.charAt(tIndex))
                tIndex ++;
            if (tIndex >= tLen)
                return false;
            sIndex ++;
            tIndex ++;
        }
        return sIndex == sLen;
    }
    // 动态规划
    public static boolean isSubsequence2(String s, String t) {
        t = " " + t;
        int len = t.length();
        int[][] dp = new int[26][len];
        for (int i = 0; i < 26; i++) {
            int p = -1;
            for (int j = len - 1; j >= 0; j--) {
                dp[i][j] = p;
                if (t.charAt(j) == i + 'a')
                    p = j;
            }
        }
        int i = 0;
        for (char ch :s.toCharArray()){
            i = dp[ch - 'a'][i];
            if (i == -1)
                return false;
        }
        return true;
    }
}
