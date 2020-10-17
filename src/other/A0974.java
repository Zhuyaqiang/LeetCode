package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 和可被K整除的子数组
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * 示例：
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * 提示：
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class A0974 {
    public static void main(String[] args) {
        int[] A = {4,5,0,-2,-3,1};
        System.out.println(subarraysDivByK3(A, 5));
    }

    // 超时, 递归
    public static int res = 0;
    public static int subarraysDivByK(int[] A, int K) {
        for(int i = 0; i < A.length; i++) {
            backtrack(A, K, i, 0);
        }
        return res;
    }
    public static void backtrack(int[] A, int K, int start, int sum) {
        if (start == A.length)
            return;
        sum += A[start];
        if (sum % K == 0)
            res ++;
        backtrack(A, K, start + 1, sum);
    }

    // 前缀和 数组 超时
    public static int subarraysDivByK2(int[] A, int K) {
        int len = A.length;
        int[] sum = new int[len+1];
        int res = 0;
        sum[0] = 0;
        for (int i = 1; i < len+1; i++) {
            sum[i] = sum[i-1] + A[i-1];
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len + 1; j++) {
                if (((sum[j] - sum[i]) % K + K) % K == 0)
                    res ++;
            }
        }
        return res;
    }

    // 前缀和, 哈希表
    // (sum[j]-sum[i]) % K = 0, 换成同余定理, sum[j] % K == sum[i] % K
    // 即判断前缀和出现次数, 利用哈希表
    public static int subarraysDivByK3(int[] A, int K) {
        int sum = 0;
        int ans = 0;
        Map<Integer, Integer> record = new HashMap<>();
        record.put(0, 1); // 前缀和本身被K整除的情况, 当遇见第一个本身被K除的数字时, 结果加一(其本身), 遇见第二个本身被K除的数字时, 结果加二(本身及第一个数字和)
        for (int i : A) {
            sum += i;
            int module = ((sum % K) + K) % K;
            int count = record.getOrDefault(module, 0);
            ans += count;
            record.put(module, count + 1);
        }
        return ans;
    }
}
