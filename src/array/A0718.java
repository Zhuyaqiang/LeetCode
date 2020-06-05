package array;

import java.util.*;

/**
 * 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例 1:
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class A0718 {
    public static void main(String[] args) {
        int[] A = {1,2,3,2,1};
        int[] B = {3,2,1,4,7};
        System.out.println(rFindLength(A, B));
    }
    public static int rFindLength(int[] A, int[] B) {
        int aLen = A.length, bLen = B.length;
        int[][] dp = new int[aLen][bLen];
        int ans = 0;
        for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < bLen; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = A[i] == B[j] ? 1 : 0;
                } else {
                    dp[i][j] = A[i] == B[j] ? dp[i - 1][j - 1] + 1 : 0;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    // 超时
    public static int findLength(int[] A, int[] B) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (!map.containsKey(A[i]))
                map.put(A[i], new ArrayList<>());
            map.get(A[i]).add(i);
        }
        int res = 0;
        for (int i = 0; i < B.length; i++) {
            if (!map.containsKey(B[i]))
                continue;
            ArrayList<Integer> arrayList = map.get(B[i]);
            for (Integer integer : arrayList) {
                int aIndex = integer;
                int bIndex = i;
                int count = 0;
                while (bIndex < B.length && aIndex < A.length && B[bIndex] == A[aIndex]) {
                    count ++;
                    bIndex++;
                    aIndex++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
    // 动态规划
    // dp[i][j]为A[i]和B[j]的最长公共前缀
    // dp[0][0] = A[0] == B[0] ? 1 : 0
    // dp[i][j] = A[i] == B[j] ? dp[i-1][j-1] + 1 : 0
    public static int findLength2(int[] A, int[] B) {
        int aLen = A.length, bLen = B.length;
        int[][] dp = new int[aLen+1][bLen+1];
        int ans = 0;
        for (int i = 1; i < aLen+1; i++) {
            for (int j = 1; j < bLen+1; j++) {
                dp[i][j] = A[i-1] == B[j-1] ? dp[i-1][j-1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
