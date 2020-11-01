package array;

import java.util.Arrays;

/**
 * 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class A0016 {
    public static void main(String[] args) {
        int nums[] = {-1,0,1,1,55};
        System.out.println(threeSumClosest2(nums, 3));
    }
    public static int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int len = nums.length;
        int l, r, res = Integer.MIN_VALUE, cloest = Integer.MAX_VALUE;
        if (nums[0] + nums[1] + nums[2] > target)
            return nums[0] + nums[1] + nums[2];
        if (nums[len-1] + nums[len-2] + nums[len-3] < target)
            return nums[len-1] + nums[len-2] + nums[len-3];
        for (int i = 0; i < len-2; i++) {
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            l = i + 1;
            r = len-1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < cloest) {
                    cloest = Math.abs(sum - target);
                    res = sum;
                }
                if (sum == target)
                    break;
                if (sum > target)
                    r--;
                else
                    l++;
            }
        }
        return res;
    }

    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum-target) < Math.abs(ans-target))
                    ans = sum;
                if (sum > target)
                    r--;
                else if (sum < target)
                    l++;
                else
                    return ans;
            }
        }
        return ans;
    }
}
