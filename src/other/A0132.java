package other;

import java.util.Arrays;

/**
 * 分割回文串2
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * 返回符合要求的 最少分割次数 。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * 输入：s = "ab"
 * 输出：1
 *  
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */

public class A0132 {
    public static void main(String[] args) {
        System.out.println(minCut2("aab"));
        System.out.println(minCut2("a"));
        System.out.println(minCut2("ab"));
    }
    // 动态规划, 时间复杂度O(n3), 空间复杂度O(n)
    public static int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= i; j++) {
                String str = s.substring(j, i);
                if (check(str, i - j)) {
                    if (j == 0) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return dp[len];
    }
    public static boolean check(String s,  int len) {
        int l = 0, r = len - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    public static int minCut2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        boolean[][] check = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            check[i][i] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                check[j][i] = s.charAt(i) == s.charAt(j) && (i == j + 1 || check[j + 1][i - 1]);
            }
        }
        int[] res = new int[len];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        for (int i = 0; i < len; i++) {
            if (check[0][i]) {
                res[i] = 0;
            } else {
                for (int j = 0; j < i; j++) {
                    if (check[j + 1][i]) {
                        res[i] = Math.min(res[i], res[j] + 1);
                    }
                }
            }
        }
        return res[len - 1];
    }
}
