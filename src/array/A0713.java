package array;

/**
 * 乘积小于K的子数组
 *
 给定一个正整数数组 nums。
 找出该数组内乘积小于 k 的连续的子数组的个数。
 示例 1:
 输入: nums = [10,5,2,6], k = 100
 输出: 8
 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 说明:
 0 < nums.length <= 50000
 0 < nums[i] < 1000
 0 <= k < 10^6
 */
public class A0713 {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(numSubarrayProductLessThanK2(nums, 1));
    }

    // 超时
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        if (len == 1)
            return nums[0] < k ? 1 : 0;
        int count = 0;
        for (int i = 0; i < len; i++) {
            int ans = 1;
            for (int j = i; j < len; j++) {
                ans *= nums[j];
                if (ans < k)
                    count ++;
                else
                    break;
            }
        }
        return count;
    }
    // 双指针
    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) return 0;
        int ans = 1, count = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            ans *= nums[right];
            while (ans >= k) ans /= nums[left++];
            count += right-left+1;
        }
        return count;
    }
}
