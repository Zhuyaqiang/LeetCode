package DP;

import java.util.*;

/**
 * 单词拆分2
 */
public class A0140 {
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = new ArrayList<>(Arrays.asList("a","aa"));
        List<String> strings = wordBreak(s, wordDict);
        System.out.println(strings);
    }
    public static List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        //dp[i]表示第i个字符是否可拆分
        List<Set<Integer>> dp = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        set.add(0);
        dp.add(set);
        for (int i = 0; i < len; i++)
            dp.add(new HashSet<>());
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (wordDict.contains(s.substring(j, i))) {
                    Set<Integer> set2 = dp.get(i);
                    set2.add(j);
                    dp.set(i, set2);
                }
            }
        }
        int count = 0;
        for (Set<Integer> integers : dp) {
            System.out.print(count + " ");
            count ++;
            System.out.println(integers);
        }
        boolean[] seen = new boolean[len+1];
        Set<Integer> set1 = dp.get(len);
        Iterator<Integer> iterator = set1.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            backtrack(dp, next, len, "", s, seen);
        }
        return ans;
    }
    static List<String> ans = new ArrayList<>();
    public static void backtrack(List<Set<Integer>> dp, int start, int end, String str, String s, boolean[] seen) {
        if (start == 0) {
            String res = s.substring(start, end) + " " + str;
            ans.add(res.substring(0, res.length()-1));
            return;
        }
        Set<Integer> set = dp.get(start);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (!seen[start]){
                backtrack(dp, next, start, s.substring(start, end) + " " + str, s, seen);
            }
        }
    }
}
