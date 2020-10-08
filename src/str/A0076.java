package str;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 示例：
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 * 提示：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class A0076 {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
    // 思路: 外层循环右指针+1, 如果满足条件进入内层循环, 处理后左指针-1至不满足条件继续外层循环
    public static String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen < tLen)
            return "";
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> curr = new HashMap<>();
        int resLen = Integer.MAX_VALUE, resStart = -1;
        for (int i = 0; i < tLen; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0, r = -1;
        while (r < sLen) {
            r ++;
            // 右指针的字符在t中出现过则加1
            if (r < sLen &&map.containsKey(s.charAt(r)))
                curr.put(s.charAt(r), curr.getOrDefault(s.charAt(r), 0) + 1);
            // s中两指针之中的字符满足题目要求
            while (check(map, curr) && l <= r) {
                if (r - l + 1 < resLen) {
                    resLen = r - l + 1;
                    resStart = l;
                }
                // 左指针加1继续上部的循环直到不满足要求
                if (curr.containsKey(s.charAt(l))) {
                    curr.put(s.charAt(l), curr.getOrDefault(s.charAt(l), 0) - 1);
                }
                l++;
            }
        }
        return resStart == -1 ? "" : s.substring(resStart, resStart + resLen);
    }
    public static boolean check(Map<Character, Integer> map, Map<Character, Integer> curr) {
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            Character key = entry.getKey();
            if (curr.getOrDefault(key, 0) < entry.getValue())
                return false;
        }
        return true;
    }
}
