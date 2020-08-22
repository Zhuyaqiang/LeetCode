package other;

/**
 * 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *  
 * 提示：
 * 输入的字符串长度不会超过 1000 。
 */
public class A0647 {
    // 暴力法, 枚举所有子串, 判断是否是回文串
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                if (s.charAt(i) != s.charAt(j-1))
                    continue;
                String str = s.substring(i, j);
                if (check(str))
                    res ++;
            }
        }
        return res;
    }
    public boolean check(String s) {
        if (s.length() == 1)
            return true;
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
    // 动态规划
    public int countSubstrings2(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                // 对于 s[j,i], 如果 s[i]和 s[j]相等, 则再以下情况是回文
                //           ①i == j, (i - j == 0)
                //           ②i == j + 1
                //           ③ s[j+1, i-1]是回文串
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                    res ++;
                }
            }
        }
        return res;
    }
    // 中心扩散
    public int countSubstrings3(String s) {
        int res = 0, len = s.length();
        for (int i = 0; i < len; i++) {
            res = mix(i, i, res, s, len);
            res = mix(i, i + 1, res, s, len);
        }
        return res;
    }
    public int mix(int l, int r, int res, String s, int len) {
        while(l >= 0 && r < len) {
            if (s.charAt(l) == s.charAt(r)) {
                res ++;
                l --;
                r ++;
            } else
                break;
        }
        return res;
    }
}
