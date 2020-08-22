package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 最长数对链
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 示例 :
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 * 注意：
 * 给出数对的个数在 [1, 1000] 范围内。
 */
public class A0646 {
    public static void main(String[] args) {
        int[][] pairs = {{1,2}, {2,3}, {3,4}};
        int longestChain = findLongestChain(pairs);
        System.out.println(longestChain);
    }

    // 递归, 超时
    public static int res = 0;
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0])
                    return -1;
                else if (o1[0] > o2[0])
                    return 1;
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < pairs.length; i++)
            backtrack(pairs, i, 1, Arrays.asList(i));

        return res;
    }
    public static void backtrack(int[][] pairs, int index, int len, List<Integer> list) {
        if (index >= pairs.length-1) {
            res = Math.max(res, len);
            return;
        }
        int i;
        for (i = index; i < pairs.length; i++) {
            if (pairs[i][0] > pairs[index][1])
                break;
        }
        for (i = i; i < pairs.length; i++) {
            list.add(i);
            backtrack(pairs, i, len +1, list);
            list.remove(list.size()-1);
        }
    }
    public int findLongestChain2(int[][] pairs) {
        int len = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i ++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        return dp[len-1];
    }
}
