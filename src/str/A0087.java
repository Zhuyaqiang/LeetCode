package str;

import java.util.HashMap;
import java.util.Map;

/**
 * 扰乱字符串
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 */
public class A0087 {
    public static void main(String[] args) {
        System.out.println(isScramble2("great", "rgeat"));
    }
    // 递归
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.equals(s2))
            return true;
        int len = s1.length();
        int[] letters = new int[26];
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a'] ++;
            letters[s2.charAt(i) - 'a'] --;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0)
                return false;
        }
        for (int i = 1; i < len; i++) {
            // s1切割成两部分, 最后进行若干步切割交换
            if (isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            // s1切割成两部分且交换, 最后进行若干步切割交换
            if (isScramble(s1.substring(i), s2.substring(0, len-i)) && isScramble(s1.substring(0, i), s2.substring(len-i)))
                return true;
        }
        return false;
    }

    // 记忆化搜索
    public static boolean isScramble2(String s1, String s2) {
        return backtrack(s1, s2, new HashMap<>());
    }
    public static boolean backtrack(String s1, String s2, Map<String, Integer> map) {
        String key = s1 + "," + s2;
        int ret = map.getOrDefault(key, -1);
        if (ret == 0)
            return false;
        else if (ret == 1)
            return true;
        if (s1.length() != s2.length()) {
            map.put(key, 0);
            return false;
        }
        if (s1.equals(s2)) {
            map.put(key, 1);
            return true;
        }
        int len = s1.length();
        int[] letters = new int[26];
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a'] ++;
            letters[s2.charAt(i) - 'a'] --;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                map.put(key, 0);
                return false;
            }
        }
        for (int i = 1; i < len; i++) {
            if (backtrack(s1.substring(0,i), s2.substring(0,i), map) && backtrack(s1.substring(i), s2.substring(i), map)) {
                map.put(key, 1);
                return true;
            }
            if (backtrack(s1.substring(0, i), s2.substring(len-i), map) && backtrack(s1.substring(i), s2.substring(0, len-i), map)) {
                map.put(key, 1);
                return true;
            }
        }
        map.put(key, 0);
        return false;
    }
}
