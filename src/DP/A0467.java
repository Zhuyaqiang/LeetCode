package DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入
 * 是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。 
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 * 示例 1:
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 * 示例 2:
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 * 示例 3:
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 */
public class A0467 {
    public static void main(String[] args) {
        System.out.println(rFindSubstringInWraproundString("a"));
    }
    public static int rFindSubstringInWraproundString(String p) {
        int len = p.length();
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && (p.charAt(i-1) + 1 == p.charAt(i) || p.charAt(i-1) == 'z' && p.charAt(i) == 'a'))
                k++;
            else
                k = 1;
            dp[p.charAt(i) - 'a'] = Math.max(dp[p.charAt(i) - 'a'], k);
        }
        int res = 0;
        for (int i : dp) {
            res += i;
        }
        return res;
    }


        // 超时
    public static int findSubstringInWraproundString(String p) {
        int len = p.length();
        int res = 0;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            if (!set.contains(p.substring(i, i + 1))) {
                set.add(p.substring(i, i + 1));
                res ++;
            }
            for (int j = i + 1; j < len; j++) {
                if (p.charAt(j) == p.charAt(j-1) + 1 || p.charAt(j) == p.charAt(j-1) - 25) {
                    if (!set.contains(p.substring(i, j + 1))) {
                        set.add(p.substring(i, j + 1));
                        res ++;
                    }
                }
                else break;
            }
        }
        System.out.println(set);
        return res;
    }
    // dp[i]保存的是以i + 'a'字母结尾的子字符串的最大长度, 因此不会有重复
    // 最后统计dp数组
    public static int findSubstringInWraproundString2(String p) {
        int len = p.length();
        if (len == 0)
            return 0;
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && (p.charAt(i) == p.charAt(i-1) + 1 || (p.charAt(i) == 'a' && p.charAt(i-1) == 'z')))
                k++;
            else
                k = 1;
            dp[p.charAt(i) - 'a'] = Math.max(k, dp[p.charAt(i) - 'a']);
        }
        int res = 0;
        for (int i : dp) {
            res += i;
        }
        return res;
    }
}
