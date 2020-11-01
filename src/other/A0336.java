package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 *
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 *
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 */
public class A0336 {
    // 暴力法, 超时
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String str = words[i] + words[j];
                if (check(str))
                    ans.add(Arrays.asList(i, j));
                str = words[j] + words[i];
                if (check(str))
                    ans.add(Arrays.asList(j, i));
            }
        }
        return ans;
    }
    public boolean check(String word) {
        int s = 0, e = word.length()-1;
        while (s < e) {
            if (word.charAt(s) != word.charAt(e))
                return false;
            s++;
            e--;
        }
        return true;
    }
}
