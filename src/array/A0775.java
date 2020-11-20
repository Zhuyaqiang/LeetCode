package array;

/**
 * 全局倒置与局部倒置
 * 数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。全局倒置指的是 i,j 满足 0 <= i < j < N 并且 A[i] > A[j] ，局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。
 * 当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。
 * 示例 1:
 * 输入: A = [1,0,2]
 * 输出: true
 * 解释: 有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2:
 * 输入: A = [1,2,0]
 * 输出: false
 * 解释: 有 2 个全局倒置，和 1 个局部倒置。
 * 注意:
 * A 是 [0, 1, ..., A.length - 1] 的一种排列
 * A 的长度在 [1, 5000]之间
 * 这个问题的时间限制已经减少了。
 */
public class A0775 {
    public static void main(String[] args) {
        int[] A = {1,2,0};
        System.out.println(isIdealPermutation(A));
    }
    // 局部倒置也是全局倒置, 只需找出非局部倒置即可暴力法
    public static boolean isIdealPermutation(int[] A) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j < len; j++) {
                if (A[i] > A[j])
                    return false;
            }
        }
        return true;
    }
    // 找出非局部倒置, 只需从后往前更新最小的, 看是否比当前位置前两位小
    public static boolean isIdealPermutation2(int[] A) {
        int len = A.length;
        int min = len;
        for (int i = len-1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (min < A[i-2])
                return false;
        }
        return true;
    }
}
