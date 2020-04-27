package array;

public class A0209 {
    // 暴力法
    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            int target = s - nums[i];
            int count = 1;
            if (target <= 0)
                return count;
            for (int j = i + 1; j < len; j++) {
                count++;
                target = target - nums[j];
                if (target <= 0) {
                    min = Math.min(min, count);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    // 双指针法(滑动窗口)
    public static int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int left = 0, right = 0, res = Integer.MAX_VALUE;
        int sum = 0, len = nums.length;
        while (right < len) {
            sum += nums[right];
            right++;
            while (sum >= s) {
                res = Math.min(res, right-left);
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
