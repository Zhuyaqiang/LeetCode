package DP;

import java.util.*;

/**
 * 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class A0139 {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
//        String s = "catsandog";
//        List<String> wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(wordBreak(s, wordDict));
    }

    // 暴力递归， 超时
    public static boolean wordBreak2(String s, List<String> wordDict) {
        return backtrack2(s, wordDict);
    }
    public static boolean backtrack2(String s, List<String> wordDict) {
        if (s.length() == 0)
            return true;
        for (int i = 1; i <=s.length(); i++) {
            if (wordDict.contains(s.substring(0, i))) {
                if (backtrack2(s.substring(i), wordDict))
                    return true;
            }
        }
        return false;
    }
    // 动态规划
    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //dp[i]表示第i个字符是否可拆分
        int[] dp = new int[len+1];
        for (int i = 1; i <= len; i++)
            dp[i] = -1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <i; j++) {
                if (dp[j] != -1 && wordDict.contains(s.substring(j, i))) {
                    dp[i] = j;
                    break;
                }
            }
        }
        for (int i : dp) {
            System.out.println(i + " ");
        }
        return dp[len] >= 0;
    }
}
