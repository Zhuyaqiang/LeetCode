package str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长字串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class A0003 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("au"));
        System.out.println(lengthOfLongestSubstring2(" "));
        System.out.println(lengthOfLongestSubstring2(""));
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
        System.out.println(lengthOfLongestSubstring2("bbbbb"));
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("aab"));
        System.out.println(lengthOfLongestSubstring2("abba"));
        System.out.println(lengthOfLongestSubstring2("dvdf"));
    }
    // 暴力法
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0)
            return 0;
        int max = 1;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            int currLen = 1;
            set.clear();
            set.add(s.charAt(i));
            for (int j = i+1; j < len; j++) {
                char key = s.charAt(j);
                if (!set.contains(key)) {
                    currLen ++;
                    max = Math.max(currLen, max);
                    set.add(key);
                } else
                    break;
            }
            if (len - i <= max)
                break;
        }
        return max;
    }
    // 双指针, 滑动窗口
    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        if (len <= 1)
            return len;
        Set<Character> set = new HashSet<>();
        int rk = -1, ans = 0;
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                set.remove(s.charAt(i-1));
            }
            while (rk + 1 < len && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                rk ++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
