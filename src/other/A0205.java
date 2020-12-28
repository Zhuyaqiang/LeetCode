package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class A0205 {
    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
        System.out.println(isIsomorphic("foo", "bar"));
        System.out.println(isIsomorphic("paper", "title"));
        System.out.println(isIsomorphic("ab", "aa"));
        System.out.println(isIsomorphic("aba", "baa"));
    }
    public static boolean isIsomorphic(String s, String t) {
        Map<Integer, Integer> sMap = new HashMap<>();
        Map<Integer, Integer> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int sVal = s.charAt(i) - 'a';
            int tVal = t.charAt(i) - 'a';
            if (!sMap.containsKey(sVal) && !tMap.containsKey(tVal)) {
                sMap.put(sVal, tVal);
                tMap.put(tVal, sVal);
            } else if (sMap.containsKey(sVal) && tMap.containsKey(tVal)) {
                if (sMap.get(sVal) != tVal || tMap.get(tVal) != sVal)
                    return false;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
