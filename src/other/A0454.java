package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加2
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class A0454 {
    // 暴力, 超时
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int len = A.length;
        int res = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(D);
        Arrays.sort(C);
        for (int i = 0; i < len; i++) {
            if (A[i] + B[0] + C[0] + D[0] > 0)
                break;
            if (A[i] + B[len-1] + C[len-1] + D[len-1] < 0) {
                continue;
            }
            for (int j = 0; j < len; j++) {
                if (A[i] + B[j] + C[0] + D[0] > 0)
                    break;
                if (A[i] + B[j] + C[len-1] + D[len-1] < 0) {
                    continue;
                }
                for (int k = 0; k < len; k++) {
                    if (A[i] + B[j] + C[k] + D[0] > 0)
                        break;
                    if (A[i] + B[j] + C[k] + D[len-1] < 0) {
                        continue;
                    }
                    for (int l = 0; l < len; l++) {
                        if (A[i] + B[j] + C[k] + D[l] > 0)
                            break;
                        if (A[i] + B[j] + C[k] + D[l] == 0) {
                            res ++;
                        }
                    }
                }
            }
        }
        return res;
    }
    // 哈希表
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int key = a + b;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        int ans = 0;
        for (int c : C) {
            for (int d : D) {
                int key = - c - d;
                if (map.containsKey(key))
                    ans += map.get(key);
            }
        }
        return ans;
    }
}
