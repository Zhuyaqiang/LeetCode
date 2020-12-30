package other;

import java.util.Arrays;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 *  
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class A0387 {
    public int firstUniqChar(String s) {
        int len = s.length();
        int[] count = new int[26];
        Arrays.fill(count, -1);
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            if (count[index] == -1)
                count[index] = i;
            else
                count[index] = Integer.MAX_VALUE;
        }
        Arrays.sort(count);
        for (int i : count) {
            if (i == Integer.MAX_VALUE)
                return -1;
            if (i != -1)
                return i;
        }
        return -1;
    }
}
