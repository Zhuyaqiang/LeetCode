package other;

import java.util.Arrays;

/**
 * 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class A0031 {
    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        nextPermutation(nums);
//        System.out.println(Arrays.toString((nums)));
//        int[] nums1 = {1,1,5};
//        nextPermutation(nums1);
//        System.out.println(Arrays.toString((nums1)));
        int[] nums2 = {3,2,1};
        nextPermutation(nums2);
        System.out.println(Arrays.toString((nums2)));
//        int[] nums3 = {1,3,2};  // 213
//        nextPermutation(nums3);
//        System.out.println(Arrays.toString(nums3));
//        int[] nums4 = {2,3,1};
//        nextPermutation(nums4);
//        System.out.println(Arrays.toString(nums4));
    }
    public static void nextPermutation(int[] nums) {
        // 找到相对靠右的大数和大数左边最靠近的比大数小的第一个数, 交换, 之后调换大数之后的所有数
        int len = nums.length;
        int i = len - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i+1])
                break;
        }
        int j = len - 1;
        if (i >= 0) {
            for (; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }
        }
        int l = i + 1, r = len - 1;
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l ++;
            r --;
        }
    }
}
