package str;

import java.util.HashMap;
import java.util.Map;

/**
 * 赎金信
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 * 注意：
 * 你可以假设两个字符串均只含有小写字母。
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ransom-note
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A0383 {
    public static void main(String[] args) {
        System.out.println(canConstruct2("a", "b"));
        System.out.println(canConstruct2("aa", "ab"));
        System.out.println(canConstruct2("aa", "aab"));
        System.out.println(canConstruct2("aab", "baa"));
    }
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() == 0 || ransomNote.equals(magazine))
            return true;
        if (magazine.length() == 0)
            return false;
        Map<Character, Integer> map = new HashMap<>();
//        char[] rChars = ransomNote.toCharArray();
//        char[] mChars = magazine.toCharArray();
        int rLen = ransomNote.length(), mLen = magazine.length();
        for (int i = 0; i < mLen; i++)
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        for (int j = 0; j < rLen; j++) {
            int count = map.getOrDefault(ransomNote.charAt(j), 0);
            if (count == 0)
                return false;
            else
                map.put(ransomNote.charAt(j), count - 1);
        }
        return true;
    }
    public static boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() == 0 || ransomNote.equals(magazine))
            return true;
        if (magazine.length() == 0)
            return false;
        int[] count = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            count[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int key = ransomNote.charAt(i) - 'a';
            if (count[key] == 0)
                return false;
            count[key] --;
        }
        return true;
    }
}
