package other;

import java.util.LinkedList;

/**
 * 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 */
public class A0316 {
    // https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-kan-jiu-hui-jiu-chai-shou-ba-shou-jia-miqw/
    public static String removeDuplicateLetters(String s) {
        LinkedList<Character> deque = new LinkedList<>();
        int[] count = new int[26];
        boolean[] exists = new boolean[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (!exists[temp - 'a']) {
                while (!deque.isEmpty() && deque.getLast() > temp && count[deque.getLast() - 'a'] > 0) {
                    exists[deque.getLast() - 'a'] = false;
                    deque.removeLast();
                }
                deque.add(temp);
                exists[temp - 'a'] = true;
            }
            count[temp - 'a']--;
        }
        StringBuilder res = new StringBuilder();
        for (Character ch : deque) {
            res.append(ch);
        }
        return res.toString();
    }
}
