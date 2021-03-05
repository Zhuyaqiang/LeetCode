package array;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
public class A0238 {
    public static int[] rProductExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{};
        }
        int left = 1;
        int[] right = new int[len];
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = left * right[i];
            left *= nums[i];
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] ints = rProductExceptSelf(nums);
        System.out.println(Arrays.toString(ints));
    }
    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] L = new int[len];
        int[] R = new int[len];
        L[0] = R[len-1] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = L[i-1] * nums[i-1];
        }
        for (int i = len-2; i >=0; i--) {
            R[i] = R[i+1] * nums[i+1];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = L[i] * R[i];
        }
        return nums;
    }

    // 动态构建
    public static int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++)
            res[i] = res[i-1] * nums[i-1];
        int R = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * R;
            R = R * nums[i];
        }
        return res;
    }
}
