package other;

/**
 * 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 * 提示：
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 */
public class A0389 {
    // 计数
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (count[ch - 'a'] == 0) {
                return ch;
            }
            count[ch - 'a']--;
        }
        return 'a';
    }

    // 统计
    public char findTheDifference2(String s, String t) {
        int totalS = 0, totalT = 0;
        for (int i = 0; i < s.length(); i++) {
            totalS += (int) s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            totalT += (int) t.charAt(i);
        }
        return (char) (totalT - totalS);
    }
}
