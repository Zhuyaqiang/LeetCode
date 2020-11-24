package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class A0242 {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        int sLen = s.length(), tLen = t.length();
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        if (sLen != tLen)
            return false;
        for (int i = 0; i < sLen; i++) {
            char ch = charsS[i];
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < tLen; i++) {
            char ch = charsT[i];
            if (!map.containsKey(ch) || map.get(ch) == 0)
                return false;
            map.put(ch, map.get(ch) - 1);
        }
        return true;
    }
    // 数组
    public boolean isAnagram1(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen)
            return false;
        int[] count = new int[26];
        for (int i = 0; i < sLen; i++) {
            count[s.charAt(i) - 'a'] ++;
            count[t.charAt(i) - 'a'] --;
        }
        for (int i = 0; i < 26; i++)
            if (count[i] != 0)
                return false;
        return true;
    }
    // 排序
    public boolean isAnagram2(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen)
            return false;
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        for (int i = 0; i < sLen; i++)
            if (sChars[i] != tChars[i])
                return false;
        return true;
    }
}
