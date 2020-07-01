package other;

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
    public int findLength(int[] A, int[] B) {
        int aLen = A.length, bLen = B.length;
        int[][] dp = new int[aLen+1][bLen+1];
        int ans = 0;
        for (int i = 1; i <= aLen; i++) {
            for (int j = 1; j <= bLen; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }
        return ans;
    }
}
