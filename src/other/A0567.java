package other;

import java.util.HashSet;
import java.util.Set;

/**
 *  字符串的排列
 *  给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 提示：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class A0567 {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
        System.out.println(checkInclusion("adc", "dcda"));
    }
    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            int index =  s1.charAt(i) - 'a';
            count[index]++;
        }
        int l = 0, r = 0;
        do {
            int index = s2.charAt(r) - 'a';
            if (count[index] > 0) {
                count[index]--;
                r++;
                if (r - l == len1) {
                    return true;
                }
            } else {
                l++;
                if (l > r) {
                    r = l;
                } else {
                    count[s2.charAt(l - 1) - 'a']++;
                }
            }
        } while (r < len2);
        return false;
    }
}
