package array.a0016;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int nums[] = {-1,0,1,1,55};
        System.out.println(threeSumClosest(nums, 3));
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
