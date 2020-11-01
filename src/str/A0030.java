package str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 串联所有单词的字串
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */
public class A0030 {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}));
    }

    //https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-6/
    // 通过维护两个哈系表, 一个是words数组中每个词的出现次数, 一个是在s中字串中每个word的出现次数, 如果在s中word出现次数大与数组中个数则不匹配
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordNum = words.length;
        if (wordNum == 0)
            return res;
        int wordLen = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int val = map.getOrDefault(word, 0);
            map.put(word, val + 1);
        }
        for (int i = 0; i <= s.length() - wordLen * wordNum; i++) {
            Map<String, Integer> curr = new HashMap<>();
            int count = 0;
            while (count < wordNum) {
                String word = s.substring(i + count * wordLen, i + (count + 1) * wordLen);
                if (map.containsKey(word)) {
                    int val = curr.getOrDefault(word, 0);
                    if (val + 1 > map.get(word))
                        break;
                    curr.put(word, val + 1);
                } else
                    break;
                count ++;
            }
            if (count == wordNum)
                res.add(i);
        }
        return res;
    }
}
