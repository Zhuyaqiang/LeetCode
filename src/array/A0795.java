package array;

/**
 * 区间子数组个数
 * 给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。
 * 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。
 * 例如 :
 * 输入:
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * 输出: 3
 * 解释: 满足条件的子数组: [2], [2, 1], [3].
 * 注意:
 * L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
 * 数组 A 的长度范围在[1, 50000]。
 */
public class A0795 {
    public static void main(String[] args) {
        int[] A = {73,55,36,5,55,14,9,7,72,52};
        System.out.println(numSubarrayBoundedMax2(A, 32, 69));
    }
    // 递归 超时
    public static int res = 0;
    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= R)
                backtrack(A, L, R, A[i], i);
        }
        return res;
    }
    public static void backtrack(int[] A, int L, int R, int max, int start) {
        if (start == A.length)
            return;
        max = Math.max(max, A[start]);
        if (max > R)
            return;
        if (max >= L && max <= R) {
            res ++;
        }
        backtrack(A, L, R, max, start+1);
    }


    // 将数组中小于L的数字换成0, 大于等于L小于等于R的数字换成1, 大于R的数字换成2
    // 将原数组分割为几个最大的至少含有1个1且不含有2的数组
    // 再在这些数组中寻找至少含有1个1的子数组
    // 换成总思想即是先找出不含有2(小于等于R)的所有子数组, 再减去仅含有0(小于L)的子数组
    public static int numSubarrayBoundedMax2(int[] A, int L, int R) {
        return count(A, R) - count(A, L-1);
    }
    // 找出小于等于bound的子数组数量
    public static int count(int[] A, int bound) {
        int ans = 0, curr = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= bound) {
                curr ++;
            } else {
                curr = 0;
            }
            ans += curr;
        }
        return ans;
    }
}
