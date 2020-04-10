package array;

/**
 * 最大字序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class A0053 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(nums));
    }
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int ans[] = new int[len];
        ans[0] = nums[0];
        for (int i = 1; i < len; i++) {
            ans[i] = Math.max(nums[i], ans[i-1] + nums[i]);
        }
        int max = ans[0];
        for (int i = 1; i < len; i++)
            max = Math.max(ans[i], max);
        return max;
    }
    public static int maxSubArray2(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int currentMax = nums[0];
        for (int i = 1; i < len; i++) {
            currentMax = Math.max(currentMax + nums[i], nums[i]);
            max = Math.max(max, currentMax);
        }
        return max;
    }
}
