package array;

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
        int[] nums = {1,3,2};
        rNextPermutation2(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    public static void nextPermutation(int[] nums) {
        boolean flag = false;
        int len = nums.length;
        int i = len - 1;
        while (i >=0) {
            if (flag)
                break;
            for (int j = len-1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    nums[j] = nums[j] + nums[i];
                    nums[i] = nums[j] - nums[i];
                    nums[j] = nums[j] - nums[i];
                    flag = true;
                    Arrays.sort(nums, i+1, len);
                    break;
                }
            }
            i--;
        }
        if (!flag) {
            for (int j = 0; j < len / 2; j++) {
                nums[len-1-j] = nums[len-1-j] + nums[j];
                nums[j] = nums[len-1-j] - nums[j];
                nums[len-1-j] = nums[len-1-j] - nums[j];
            }
        }
    }

    public static void nextPermutation2(int[] nums) {
        int len = nums.length;
        int i = len-1;
        while(i > 0 && nums[i] <= nums[i-1])
            i--;
        if (i > 0) {
            int j = len-1;
            while (j >= 0 && nums[j] <= nums[i-1])
                j--;
            nums[i-1] = nums[i-1] + nums[j];
            nums[j] = nums[i-1] - nums[j];
            nums[i-1] = nums[i-1] - nums[j];
        }
        int start = i, end = len-1;
        while (start < end) {
            nums[start] = nums[start] + nums[end];
            nums[end] = nums[start] - nums[end];
            nums[start] = nums[start] - nums[end];
            start++;
            end--;
        }
    }

    public static void rNextPermutation2(int[] nums) {
        int len = nums.length;
        int i = len-1;
        while (i > 0 && nums[i] <= nums[i-1])
            i--;
        if (i > 0) {
            int j = len - 1;
            while(nums[j] <= nums[i-1]) j--;
            int temp = nums[j];
            nums[j] = nums[i-1];
            nums[i-1] = temp;
        }
        int start = i, end = len-1;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
