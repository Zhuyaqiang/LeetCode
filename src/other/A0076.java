package other;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 *
 *
 * 提示：
 *
 *     1 <= s.length, t.length <= 105
 *     s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class A0076 {
    public static void main(String[] args) {
        System.out.println(minWindow("abc", "c"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
    }
    public static String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < tLen; i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int l = 0, r = 0, resL = 0, resR = sLen + 1;
        while (r < sLen) {
            char chR = s.charAt(r);
            if (map.containsKey(chR)) {
                count.put(chR, count.getOrDefault(chR, 0) + 1);
            }
            while (check(map, count) && l <= r) {
                if (r - l < resR - resL) {
                    resL = l;
                    resR = r;
                }
                if (count.containsKey(s.charAt(l))) {
                    count.put(s.charAt(l), count.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            }
            r++;
        }
        return resR == sLen + 1 ? "" : s.substring(resL, resR + 1);
    }
    public static boolean check(Map<Character, Integer> map, Map<Character, Integer> count) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();
            if (val > count.getOrDefault(key, 0)) {
                return false;
            }
        }
        return true;
    }
}
