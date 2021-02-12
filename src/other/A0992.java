package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * K个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 返回 A 中好子数组的数目。
 * 示例 1：
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 */
public class A0992 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        System.out.println(subarraysWithKDistinct(nums, 2));
    }
    public static int subarraysWithKDistinct(int[] A, int K) {
        if (A == null) {
            return 0;
        }
        int len = A.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < len; j++) {
                set.add(A[j]);
                if (set.size() == K) {
                    res++;
                } else if (set.size() > K) {
                    break;
                }
            }
        }
        return res;
    }
}
