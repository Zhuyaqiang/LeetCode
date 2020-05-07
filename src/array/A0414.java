package array;

/**
 * 第三大的数
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * 示例 1:
 * 输入: [3, 2, 1]
 * 输出: 1
 * 解释: 第三大的数是 1.
 * 示例 2:
 * 输入: [1, 2]
 * 输出: 2
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 * 输入: [2, 2, 3, 1]
 * 输出: 1
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 */
public class A0414 {
    public static void main(String[] args) {
//        int[] nums = {5, 2, 2};
//        int[] nums = {2, 2, 3, 1};
//        int[] nums = {2, 2, 3, 1};
//        int[] nums = {1,1,2};
        int[] nums = {-2147483648,-2147483648,-2147483648,-2147483648,1,1,1};
//        int[] nums = {1,-2147483648,2};
        System.out.println(thirdMax(nums));
    }
    public static int thirdMax(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        if (len == 2)
            return Math.max(nums[0], nums[1]);
        long max = Long.MIN_VALUE, secondMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = nums[i];
            } else if (nums[i] < max && nums[i] > secondMax) {
                thirdMax = secondMax;
                secondMax = nums[i];
            } else if (nums[i] < secondMax && nums[i] > thirdMax) {
                thirdMax = nums[i];
            }
        }
        if (thirdMax == Long.MIN_VALUE)
            return (int)max;
        return (int)thirdMax;
    }
}
