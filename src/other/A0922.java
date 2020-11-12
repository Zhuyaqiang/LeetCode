package other;

import java.util.Arrays;

/**
 * 按奇偶排序数组2
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class A0922 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        System.out.println(Arrays.toString(sortArrayByParityII(nums)));
    }

    public static int[] sortArrayByParityII(int[] A) {
        int len = A.length;
        int index = len - 1, zeroIndex = 0, oneIndex = 1;
        while (zeroIndex < len && A[zeroIndex] % 2 == 0)
            zeroIndex += 2;
        while (oneIndex < len && A[oneIndex] % 2 != 0)
            oneIndex += 2;
        while (oneIndex <= index && zeroIndex <= index - 1) {
            if (A[index] % 2 == 0) {
                swap(A, zeroIndex, index);
                zeroIndex += 2;
            } else {
                swap(A, oneIndex, index);
                oneIndex += 2;
            }
        }
        while (oneIndex <= index) {
            if (A[oneIndex] % 2 == 0)
                swap(A, oneIndex, index);
            oneIndex += 2;
        }
        while (zeroIndex <= index) {
            if (A[zeroIndex] % 2 != 0)
                swap(A, zeroIndex, index);
            zeroIndex += 2;
        }
        return A;
    }

    // 双指针
    public static int[] sortArrayByParityII2(int[] A) {
        int j = 1;
        for (int i = 0; i < A.length; i+=2) {
            if (A[i] % 2 == 1) {
                // 当偶数索引位数字为奇数时, 一定有奇数索引位数字为偶数, 所以不会越界
                while (A[j] % 2 == 1)
                    j += 2;
                swap(A, i, j);
            }
        }
        return A;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
