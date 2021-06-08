package other;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到字符串中的所有字母异位词
 */
public class A0438 {
    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] count = new int[26];
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) {
            return res;
        }
        for (int i = 0; i < pLen; i++) {
            count[p.charAt(i) - 'a']++;
        }
        String countStr = Arrays.toString(count);
        int l = 0, r = 0;
        count = new int[26];
        do {
            if (l > r) {
                r = l;
                continue;
            }
            while (r < sLen && r - l + 1 <= pLen) {
                count[s.charAt(r) - 'a']++;
                r++;
            }
            if (Arrays.toString(count).equals(countStr)) {
                res.add(l);
            }
            count[s.charAt(l) - 'a']--;
            l++;
        } while (r < sLen && l < sLen);
        return res;
    }
}
