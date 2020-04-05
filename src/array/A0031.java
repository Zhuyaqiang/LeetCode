package array;

import java.util.Arrays;

public class A0031 {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        nextPermutation2(nums);
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
}
