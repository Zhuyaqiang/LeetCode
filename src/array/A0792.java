package array;

import java.util.ArrayList;

/**
 * 匹配子序列的单词数
 * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
 * 示例:
 * 输入:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * 输出: 3
 * 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
 * 注意:
 * 所有在words和 S 里的单词都只由小写字母组成。
 * S 的长度在 [1, 50000]。
 * words 的长度在 [1, 5000]。
 * words[i]的长度在[1, 50]。
 */
public class A0792 {
    public static void main(String[] args) {
        String S = "abcde";
        String an = S;
        S = new String();
        System.out.println(an);
    }

    // 暴力法, 超时
    public static int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        for (String word : words) {
            if (check(word, S))
                ans++;
        }
        return ans;
    }

    public static boolean check(String word, String S) {
        // 判断word是否是S的子数组
        int wIndex, sIndex = 0, count = 0;
        for (wIndex = 0; wIndex < word.length(); wIndex++) {
            while (sIndex < S.length()) {
                if (S.charAt(sIndex++) == word.charAt(wIndex)) {
                    count++;
                    break;
                }
            }
        }
        return count == word.length();
    }

    // 使用26个字母桶放置words, 再遍历S, 每次更新桶中单词的当前索引
    public static int numMatchingSubseq2(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; i++)
            heads[i] = new ArrayList<>();
        for (String word : words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }

        for (char c: S.toCharArray()) {
            ArrayList<Node> old = heads[c-'a'];
            heads[c-'a'] = new ArrayList<>();
            for (Node node : old) {
                if (node.index == node.word.length())
                    continue;
                node.index ++;
                if (node.index == node.word.length())
                    ans ++;
                else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old.clear();
        }
        return ans;
    }

    static class Node {
        String word;
        int index;
        public Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
