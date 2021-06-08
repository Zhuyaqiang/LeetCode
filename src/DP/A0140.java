package DP;

import java.util.*;

/**
 * 单词拆分2
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class A0140 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = new ArrayList<>(Arrays.asList("a", "aa"));
        List<String> strings = wordBreak(s, wordDict);
        System.out.println(strings);
    }

    public static List<String> rWordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        // dp[i]表示s的第i个字母结尾的前缀传
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        List<String> res = new ArrayList<>();
        if (dp[len]) {
            rRecursion(len, res, new LinkedList<>(), s, set);
        }
        return res;
    }
    public static void rRecursion(int len, List<String> res, Deque<String> path, String s, Set<String> set) {
        if (len == 0) {
            res.add(String.join(" ", path));
            return;
        }
        for (int i = len - 1; i >= 0; i--) {
            String str = s.substring(i, len);
            if (set.contains(str)) {
                path.addFirst(str);
                rRecursion(i, res, path, s, set);
                path.removeFirst();
            }
        }
    }



    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        // dp[i]表示以第i个字母为结尾的前缀串是否能用字典中的单词表示
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (set.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        List<String> res = new ArrayList<>();
        if (dp[len]) {
            Deque<String> path = new ArrayDeque<>();
            backtrack(dp, s, set, len, path, res);
        }
        return res;
    }

    private static void backtrack(boolean[] dp, String s, Set<String> set, int len, Deque<String> path, List<String> res) {
        if (len == 0) {
            res.add(String.join(" ", path));
            return;
        }
        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (set.contains(suffix)) {
                path.addFirst(suffix);
                backtrack(dp, s, set, i, path, res);
                path.removeFirst();
            }
        }
    }
}
